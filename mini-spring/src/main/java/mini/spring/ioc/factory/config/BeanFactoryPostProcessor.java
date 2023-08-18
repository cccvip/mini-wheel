/*
 * All Rights Reserved.
 *
 */
package mini.spring.ioc.factory.config;


import mini.spring.ioc.factory.bean.ConfigurableListableBeanFactory;
import mini.spring.ioc.factory.exception.BeanException;

/**
 * BeanPostProcessor.
 *
 * @author Carl, 2023-08-14 9:36
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有BeanDefintion加载完成后，但在bean实例化之前，提供修改BeanDefinition属性值的机制
     **/
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException;

}
