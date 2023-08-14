package mini.spring.beans.factory.config;

import mini.spring.beans.factory.exception.BeanException;

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
