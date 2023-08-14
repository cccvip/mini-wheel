package mini.spring.beans.factory.config;

import mini.spring.beans.factory.exception.BeanException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{


    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException;

    /**
     * 返回定义的所有bean的名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();

}
