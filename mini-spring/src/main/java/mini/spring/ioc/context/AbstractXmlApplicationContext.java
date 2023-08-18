/*
 * All Rights Reserved.
 *
 */
package mini.spring.ioc.context;


import mini.spring.ioc.factory.exception.BeanException;
import mini.spring.ioc.factory.support.DefaultListableBeanFactory;
import mini.spring.ioc.reader.XmlBeanDefinitionReader;
import mini.spring.ioc.resources.Resources;

/**
 * AbstractXmlApplicationContext.
 *
 * @author Carl, 2023-08-14 14:10
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
