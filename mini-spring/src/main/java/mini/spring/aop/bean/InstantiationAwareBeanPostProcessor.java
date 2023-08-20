package mini.spring.aop.bean;

import mini.spring.ioc.factory.config.BeanPostProcessor;
import mini.spring.ioc.factory.exception.BeanException;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/20
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * bean实例化之前执行,用代理的Bean替换原生的Bean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object postProcessBeforeInstantiation(Class bean, String beanName) throws BeanException;

    Object getEarlyBeanReference(Object exposedObject, String beanName) throws BeanException;

    /**
     * bean实例化之后，设置属性之前执行
     *
     * @throws BeanException
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeanException;

}
