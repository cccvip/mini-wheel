package mini.spring.ioc.factory.config;

import mini.spring.ioc.factory.bean.BeanDefinition;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册表
      * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    /**
     * 当前Bean是否已经注册完成
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 返回定义的所有bean的名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();

}
