package mini.spring.ioc.factory.config;

import mini.spring.ioc.factory.exception.BeanException;

public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * applyBeanPostProcessorBeforeInitialization.
     *
     * @param beanName
     * @param object
     * @return java.lang.Object
     * @throw
     */
    Object applyBeanPostProcessorBeforeInitialization(String beanName, Object object) throws BeanException;;

    /**
     * applyBeanPostProcessorAfterInitialization.
     *
     * @param beanName
     * @param object
     * @return java.lang.Object
     * @throw 
     */
    Object applyBeanPostProcessorAfterInitialization(String beanName, Object object) throws BeanException;;

}
