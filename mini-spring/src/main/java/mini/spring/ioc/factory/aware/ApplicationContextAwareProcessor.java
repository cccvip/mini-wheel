package mini.spring.ioc.factory.aware;

import mini.spring.ioc.context.ApplicationContext;
import mini.spring.ioc.factory.config.BeanPostProcessor;
import mini.spring.ioc.factory.exception.BeanException;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/17
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) throws BeanException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) throws BeanException {
        return bean;
    }
}
