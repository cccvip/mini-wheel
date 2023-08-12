package mini.spring.beans.factory.config;

import mini.spring.beans.factory.bean.BeanDefinition;

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

}
