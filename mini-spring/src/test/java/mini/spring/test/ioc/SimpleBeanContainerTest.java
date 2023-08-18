/*
 * All Rights Reserved.
 *
 */
package mini.spring.test.ioc;


import cn.hutool.core.io.IoUtil;
import mini.spring.beans.context.ClassPathXmlApplicationContext;
import mini.spring.beans.factory.bean.BeanDefinition;
import mini.spring.beans.factory.bean.PropertyValue;
import mini.spring.beans.factory.bean.PropertyValues;
import mini.spring.beans.factory.exception.BeanException;
import mini.spring.beans.factory.support.DefaultListableBeanFactory;
import mini.spring.beans.reader.XmlBeanDefinitionReader;
import mini.spring.beans.resources.Resources;
import mini.spring.beans.resources.impl.DefaultResourceLoader;
import mini.spring.test.entity.Car;
import mini.spring.test.entity.Person;
import mini.spring.test.service.AppService;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * SimpleBeanContainerTest.
 *
 * @author Carl, 2023-08-11 9:23
 */
public class SimpleBeanContainerTest {

    @Test
    public void testBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(AppService.class);
        beanFactory.registerBeanDefinition("appService", beanDefinition);

        AppService helloService;
        try {
            helloService = (AppService) beanFactory.getBean("appService");
        } catch (BeanException e) {
            e.printStackTrace();
            return;
        }
        assertEquals("pong", helloService.ping());
    }

    @Test
    public void testPopulateBeanWithPropertyValues() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "jack"));
        propertyValues.addPropertyValue(new PropertyValue("age", 12));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);
        beanFactory.registerBeanDefinition("appService", beanDefinition);

        Person person;
        try {
            person = (Person) beanFactory.getBean("appService");
        } catch (BeanException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(person.getName());
        assertEquals(person.getName(), "jack");
    }

    @Test
    public void testPopulateBeanWithReference() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "jack"));
        propertyValues.addPropertyValue(new PropertyValue("age", 12));
        propertyValues.addPropertyValue(new PropertyValue("car", new Car("bmw")));

        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);
        beanFactory.registerBeanDefinition("appService", beanDefinition);

        Person person;
        try {
            person = (Person) beanFactory.getBean("appService");
        } catch (BeanException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(person.getCar().toString());
        assertEquals(person.getName(), "jack");
    }


    @Test
    public void testResourceLoader() {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        //加载classpath下的资源
        Resources resource = resourceLoader.getResource("hello.txt");
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert inputStream != null;
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }


    @Test
    public void testBeanReader() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resources resource = resourceLoader.getResource("spring.xml");

        try {
            beanDefinitionReader.loadBeanDefinitions(resource);

            Person person = (Person) beanFactory.getBean("person");
            System.out.println(person);
            Car car = (Car) beanFactory.getBean("car");
            System.out.println(car);
        } catch (BeanException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testBeanPostProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resources resource = resourceLoader.getResource("spring.xml");

        try {
            beanDefinitionReader.loadBeanDefinitions(resource);

            CutomerBeanPostProcessor cutomerBeanPostProcessor = new CutomerBeanPostProcessor();

            beanFactory.addBeanPostProcessor(cutomerBeanPostProcessor);

            Person person = (Person) beanFactory.getBean("person");

            System.out.println(person);
        } catch (BeanException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testBeanPostFactoryProcessor() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resources resource = resourceLoader.getResource("spring.xml");

        try {
            beanDefinitionReader.loadBeanDefinitions(resource);

            CustomBeanFactoryPostProcessor cutomerBeanPostProcessor = new CustomBeanFactoryPostProcessor();

            cutomerBeanPostProcessor.postProcessBeanFactory(beanFactory);

            Person person = (Person) beanFactory.getBean("person");

            System.out.println(person);

        } catch (BeanException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testApplicationContext() {

        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resources resource = resourceLoader.getResource("spring.xml");

        try {
            ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(resource);

            Car car = (Car) classPathXmlApplicationContext.getBean("car");

            System.out.println(car);
        } catch (BeanException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInitMethod() {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resources resource = resourceLoader.getResource("spring.xml");
        try {
            ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(resource);
            classPathXmlApplicationContext.registerShutdownWork();
        } catch (BeanException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFactoryBean() {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resources resource = resourceLoader.getResource("spring.xml");
        try {
            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(resource);
            Car car = applicationContext.getBean("car", Car.class);
            System.out.println(car.toString());
        } catch (BeanException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testListener() {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resources resource = resourceLoader.getResource("spring.xml");
        try {
            ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(resource);
            classPathXmlApplicationContext.registerShutdownWork();
        } catch (BeanException e) {
            e.printStackTrace();
        }
    }


}
