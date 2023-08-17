package mini.spring.beans.factory.aware;

import mini.spring.beans.context.ApplicationContext;
import mini.spring.beans.factory.exception.BeanException;

/**
 * @description: 全局applicationContext
 * @author：carl
 * @date: 2023/8/17
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeanException;
}
