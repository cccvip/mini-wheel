/*
 * All Rights Reserved.
 *
 */
package mini.spring.ioc.factory.config;


import mini.spring.ioc.factory.exception.BeanException;

/**
 * BeanPostProcessor.
 *
 * @author Carl, 2023-08-14 9:36
 */
public interface BeanPostProcessor {

    /**
     * postProcessBeforeInitialization.
     * 在bean初始化之前
     *
     * @param
     * @return java.lang.Object
     * @throw
     */
    Object postProcessBeforeInitialization(String beanName, Object object) throws BeanException;

    /**
     * postProcessAfterInitialization.
     * 在bean初始化之后
     *
     * @param
     * @return java.lang.Object
     * @throw
     */
    Object postProcessAfterInitialization(String beanName, Object object) throws BeanException;

}
