package mini.spring.ioc.factory.config;

/**
 * @description: 单例Bean的注册管理
 * @author：carl
 * @date: 2023/8/12
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void addSingleton(String beanName,Object bean);

}