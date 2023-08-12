package mini.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import mini.spring.beans.factory.bean.BeanDefinition;
import mini.spring.beans.factory.bean.BeanReference;
import mini.spring.beans.factory.bean.PropertyValue;
import mini.spring.beans.factory.exception.BeanException;
import mini.spring.beans.strategy.InstantiationStrategy;
import mini.spring.beans.strategy.impl.JdkInstantiationStrategy;

/**
 * @description: 创建Bean 扫描Bean
 * @author：carl
 * @date: 2023/8/12
 */
public abstract class AbstractAutoWireCapableBeanFactory extends AbstractBeanFactory {

    InstantiationStrategy instantiationStrategy = new JdkInstantiationStrategy();

    @Override
    public Object createBean(String name, BeanDefinition beanDefinition) throws BeanException {
        return doCreateBean(name, beanDefinition);
    }

    //使用策略模式
    Object doCreateBean(String name, BeanDefinition beanDefinition) throws BeanException {
        Object object = instantiationStrategy.instantiate(beanDefinition);
        //属性填充
        applyPropertyValues(name, object, beanDefinition);
        //加入缓存中
        addSingleton(name, object);
        return object;
    }

    private void applyPropertyValues(String name, Object object, BeanDefinition beanDefinition) throws BeanException {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
            String field = propertyValue.getName();
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                // beanA依赖beanB，先实例化beanB
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }
            BeanUtil.setFieldValue(object, field, value);
        }
    }

}
