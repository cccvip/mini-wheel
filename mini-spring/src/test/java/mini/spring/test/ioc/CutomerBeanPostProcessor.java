/*
 * All Rights Reserved.
 *
 */
package mini.spring.test.ioc;


import mini.spring.ioc.factory.config.BeanPostProcessor;

/**
 * CutomerBeanPostProcessor.
 *
 * @author Carl, 2023-08-14 10:24
 */
public class CutomerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(String beanName, Object object) {
        System.out.println("CustomerBeanPostProcessor#postProcessBeforeInitialization");
        System.out.println(object);
        return object;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object object) {
        System.out.println("CustomerBeanPostProcessor#postProcessAfterInitialization");
        return object;
    }
}
