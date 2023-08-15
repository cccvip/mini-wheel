package mini.spring.beans.factory.config;

import mini.spring.beans.factory.exception.BeanException;

/**
 * @description: Bean的初始化 init-method
 * @author：carl
 * @date: 2023/8/15
 */
public interface InitializingBean {

    void afterProperties() throws BeanException;

}
