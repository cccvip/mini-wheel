package mini.spring.ioc.factory.bean;


import mini.spring.ioc.factory.exception.BeanException;

/**
 * @author derekyi
 * @date 2021/1/30
 */
public interface ObjectFactory<T> {

	T getObject() throws BeanException;
}