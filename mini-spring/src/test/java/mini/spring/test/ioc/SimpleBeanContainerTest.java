/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package mini.spring.test.ioc;


import mini.spring.beans.factory.bean.BeanDefinition;
import mini.spring.beans.factory.bean.PropertyValue;
import mini.spring.beans.factory.bean.PropertyValues;
import mini.spring.beans.factory.config.BeanFactory;
import mini.spring.beans.factory.exception.BeanException;
import mini.spring.beans.factory.support.DefaultListableBeanFactory;
import mini.spring.test.entity.Person;
import mini.spring.test.service.AppService;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * SimpleBeanContainerTest.
 *
 * @author Carl, 2023-08-11 9:23
 * @version CrisisGo v1.0
 */
public class SimpleBeanContainerTest {
    //    @Test
//    public void testGetBean() {
//        BeanFactory beanFactory = new BeanFactory();
//        beanFactory.registerBean("hello", new AppService());
//        AppService appService = (AppService) beanFactory.getBean("hello");
//        String pong = appService.ping();
//        assertTrue("pong".equals(pong));
//    }
//
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


}
