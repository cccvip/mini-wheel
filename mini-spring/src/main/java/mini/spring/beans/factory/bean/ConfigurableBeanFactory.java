package mini.spring.beans.factory.bean;

import mini.spring.beans.factory.config.BeanPostProcessor;
import mini.spring.beans.factory.config.HierarchicalBeanFactory;
import mini.spring.beans.factory.config.SingletonBeanRegistry;
import mini.spring.beans.factory.exception.BeanException;

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
