package mini.spring.beans.factory.config;

import mini.spring.beans.factory.exception.BeanException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{


    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException;


    String[] getBeanDefinitionNames();

}
