package mini.spring.beans.factory.config;

import mini.spring.beans.factory.exception.BeanException;

/**
 * @description: 销毁Bean执行
 * @author：carl
 * @date: 2023/8/15
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
