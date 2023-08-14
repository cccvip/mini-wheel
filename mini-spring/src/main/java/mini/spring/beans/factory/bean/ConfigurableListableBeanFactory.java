package mini.spring.beans.factory.bean;

import mini.spring.beans.factory.config.AutowireCapableBeanFactory;
import mini.spring.beans.factory.config.BeanPostProcessor;
import mini.spring.beans.factory.config.ListableBeanFactory;
import mini.spring.beans.factory.exception.BeanException;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    /**
     * 根据name查找BeanDefinition
     * @return
     */
    BeanDefinition getBeanDefinition(String name);


    /**
     * 提前实例化所有单例实例
     */
    void preInstantiateSingletons() throws BeanException;

}
