package mini.spring.beans.factory.bean;

import mini.spring.beans.factory.config.BeanPostProcessor;
import mini.spring.beans.factory.config.HierarchicalBeanFactory;
import mini.spring.beans.factory.config.SingletonBeanRegistry;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    /**
     * addBeanPostProcessor.
     * bean初始化的前后逻辑处理
     * @param beanPostProcessor
     * @return void
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
