package mini.spring.beans.factory.config;


import mini.spring.beans.factory.exception.BeanException;

/**
 * BeanFactory.
 *
 * @author Carl, 2023-08-11 8:58
 */
public interface BeanFactory {

    Object getBean(String key) throws BeanException;

}
