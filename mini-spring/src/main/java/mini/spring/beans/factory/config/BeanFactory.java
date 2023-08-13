package mini.spring.beans.factory.config;


import mini.spring.beans.factory.exception.BeanException;

/**
 * BeanFactory.
 *
 * @author Carl, 2023-08-11 8:58
 */
public interface BeanFactory {
    /**
     *
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object getBean(String beanName) throws BeanException;

    /**
     * 根据类型获取Bean
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeanException;
}
