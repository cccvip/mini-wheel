package mini.spring.ioc.factory.config;

import mini.spring.ioc.factory.exception.BeanException;

/**
 * @description: Bean的初始化 init-method
 * @author：carl
 * @date: 2023/8/15
 */
public interface InitializingBean {

    void afterProperties() throws BeanException;

}
