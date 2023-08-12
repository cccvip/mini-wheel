package mini.spring.beans.strategy;

import mini.spring.beans.factory.bean.BeanDefinition;
import mini.spring.beans.factory.exception.BeanException;

/**
 * @description: class初始化策略 jdk/cglib策略
 * @author：carl
 * @date: 2023/8/12
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeanException;
}
