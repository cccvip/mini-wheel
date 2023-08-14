package mini.spring.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import mini.spring.beans.factory.bean.BeanDefinition;
import mini.spring.beans.factory.bean.BeanReference;
import mini.spring.beans.factory.bean.PropertyValue;
import mini.spring.beans.factory.config.AutowireCapableBeanFactory;
import mini.spring.beans.factory.config.BeanPostProcessor;
import mini.spring.beans.factory.exception.BeanException;
import mini.spring.beans.strategy.InstantiationStrategy;
import mini.spring.beans.strategy.impl.JdkInstantiationStrategy;

/**
 * @description: 创建Bean 扫描Bean
 * @author：carl
 * @date: 2023/8/12
 */
public abstract class AbstractAutoWireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    InstantiationStrategy instantiationStrategy = new JdkInstantiationStrategy();

    @Override
    public Object createBean(String name, BeanDefinition beanDefinition) throws BeanException {
        return doCreateBean(name, beanDefinition);
    }

    Object doCreateBean(String name, BeanDefinition beanDefinition) throws BeanException {
        Object object = instantiationStrategy.instantiate(beanDefinition);
        //属性填充
        applyPropertyValues(name, object, beanDefinition);

        object = initializeBean(name, object);

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


    private Object initializeBean(String beanName, Object bean) throws BeanException {

        //前置
        Object wrappedBean = applyBeanPostProcessorBeforeInitialization(beanName, bean);

        invokeInitBean(beanName, wrappedBean);

        //后置
        wrappedBean = applyBeanPostProcessorAfterInitialization(beanName, bean);

        return wrappedBean;
    }

    /**
     * 初始化方法
     *
     * @param beanName
     * @param bean
     * @return void
     */
    protected void invokeInitBean(String beanName, Object bean) {

    }

    @Override
    public Object applyBeanPostProcessorBeforeInitialization(String beanName, Object object) throws BeanException {
        Object result = object;
        for (BeanPostProcessor processor : beanPostProcessors) {
            Object current = processor.postProcessBeforeInitialization(beanName, result);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorAfterInitialization(String beanName, Object object) throws BeanException {
        Object result = object;
        for (BeanPostProcessor processor : beanPostProcessors) {
            Object current = processor.postProcessAfterInitialization(beanName, result);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
