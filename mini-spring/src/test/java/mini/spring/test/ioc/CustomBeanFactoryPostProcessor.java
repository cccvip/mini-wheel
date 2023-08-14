/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package mini.spring.test.ioc;


import mini.spring.beans.factory.bean.BeanDefinition;
import mini.spring.beans.factory.bean.ConfigurableListableBeanFactory;
import mini.spring.beans.factory.bean.PropertyValue;
import mini.spring.beans.factory.bean.PropertyValues;
import mini.spring.beans.factory.config.BeanFactoryPostProcessor;
import mini.spring.beans.factory.exception.BeanException;

/**
 * CustomBeanFactoryPostProcessor.
 * 
 * @author Carl, 2023-08-14 10:37
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        BeanDefinition personBeanDefiniton = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = personBeanDefiniton.getPropertyValues();
        //将person的name属性改为ivy
        propertyValues.addPropertyValue(new PropertyValue("name", "carl"));
    }

}
