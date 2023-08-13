package mini.spring.beans.factory.bean;

import mini.spring.beans.factory.config.AutowireCapableBeanFactory;
import mini.spring.beans.factory.config.ListableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory{

    /**
     * 根据name查找BeanDefinition
     * @return
     */
    BeanDefinition getBeanDefinition(String name);
}
