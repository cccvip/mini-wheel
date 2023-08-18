package mini.spring.ioc.factory.bean;

import mini.spring.ioc.factory.config.AutowireCapableBeanFactory;
import mini.spring.ioc.factory.config.ListableBeanFactory;
import mini.spring.ioc.factory.exception.BeanException;

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
