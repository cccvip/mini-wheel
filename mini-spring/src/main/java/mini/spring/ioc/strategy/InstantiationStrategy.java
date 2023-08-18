package mini.spring.ioc.strategy;

import mini.spring.ioc.factory.bean.BeanDefinition;
import mini.spring.ioc.factory.exception.BeanException;

/**
 * @description: class初始化策略 jdk/cglib策略
 * @author：carl
 * @date: 2023/8/12
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeanException;
}
