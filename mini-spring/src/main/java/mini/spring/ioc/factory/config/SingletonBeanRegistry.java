package mini.spring.ioc.factory.config;

import mini.spring.ioc.factory.exception.BeanException;

/**
 * @description: 单例Bean的注册管理
 * @author：carl
 * @date: 2023/8/12
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName) throws BeanException;

    void addSingleton(String beanName,Object bean);

}
