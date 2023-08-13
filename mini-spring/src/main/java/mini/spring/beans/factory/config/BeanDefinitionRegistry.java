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

    /**
     * 通过name获取BeanDefinition
     * @param beanName
     * @return
     */
    BeanDefinition getBeanByName(String beanName);

    /**
     * 当前Bean是否已经注册完成
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

}
