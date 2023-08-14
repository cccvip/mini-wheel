/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package mini.spring.beans.context;


import mini.spring.beans.factory.exception.BeanException;
import mini.spring.beans.factory.support.DefaultListableBeanFactory;
import mini.spring.beans.reader.XmlBeanDefinitionReader;
import mini.spring.beans.resources.Resources;

/**
 * AbstractXmlApplicationContext.
 *
 * @author Carl, 2023-08-14 14:10
 * @version CrisisGo v1.0
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {

    @Override
    public void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeanException {

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        Resources resource = getResource();
        if (resource != null) {
            beanDefinitionReader.loadBeanDefinitions(resource);
        }
    }

    public abstract Resources getResource();

}
