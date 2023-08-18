package mini.spring.ioc.factory.config;

import mini.spring.ioc.factory.exception.BeanException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{


    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException;


    String[] getBeanDefinitionNames();

}
