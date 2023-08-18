package mini.spring.ioc.factory.aware;

import mini.spring.ioc.context.ApplicationContext;
import mini.spring.ioc.factory.exception.BeanException;

/**
 * @description: 全局applicationContext
 * @author：carl
 * @date: 2023/8/17
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeanException;
}
