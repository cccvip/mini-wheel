package mini.spring.ioc.factory.aware;

import mini.spring.ioc.factory.config.BeanFactory;
import mini.spring.ioc.factory.exception.BeanException;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/17
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory)throws BeanException;

}
