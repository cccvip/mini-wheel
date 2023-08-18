package mini.spring.ioc.factory.bean;

import mini.spring.ioc.factory.config.BeanPostProcessor;
import mini.spring.ioc.factory.config.HierarchicalBeanFactory;
import mini.spring.ioc.factory.config.SingletonBeanRegistry;
import mini.spring.ioc.factory.exception.BeanException;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * addBeanPostProcessor.
     * bean初始化的前后逻辑处理
     * @param beanPostProcessor
     * @return void
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * destroyBean
     */
    void destroyBeans() throws BeanException;
}
