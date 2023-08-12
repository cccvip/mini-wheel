package mini.spring.beans.factory.support;

import mini.spring.beans.factory.bean.BeanDefinition;
import mini.spring.beans.factory.config.BeanFactory;
import mini.spring.beans.factory.exception.BeanException;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String key) throws BeanException {
        //从缓存里取数据
        Object bean = getSingleton(key);
        if (null != bean) {
            return bean;
        }
        //创建Bean
        //1 定义BeanDefinition
        BeanDefinition beanDefinition = getBeanDefinition(key);
        //2 放入cache中
        return createBean(key, beanDefinition);
    }

    public abstract BeanDefinition getBeanDefinition(String name);

    public abstract Object createBean(String name, BeanDefinition beanDefinition) throws BeanException;
}
