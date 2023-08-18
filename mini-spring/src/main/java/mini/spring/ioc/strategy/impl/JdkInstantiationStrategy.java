package mini.spring.ioc.strategy.impl;

import mini.spring.ioc.factory.bean.BeanDefinition;
import mini.spring.ioc.factory.exception.BeanException;
import mini.spring.ioc.strategy.InstantiationStrategy;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public class JdkInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeanException {
        Class clazz = beanDefinition.getBeanClass();
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new BeanException(e.getLocalizedMessage());
        }
    }
}
