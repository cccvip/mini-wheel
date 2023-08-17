package mini.spring.beans.factory.aware;

import mini.spring.beans.factory.config.BeanFactory;
import mini.spring.beans.factory.exception.BeanException;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/17
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory)throws BeanException;

}
