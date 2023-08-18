package mini.spring.ioc.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import mini.spring.ioc.factory.aware.BeanFactoryAware;
import mini.spring.ioc.factory.bean.BeanDefinition;
import mini.spring.ioc.factory.bean.BeanReference;
import mini.spring.ioc.factory.bean.PropertyValue;
import mini.spring.ioc.factory.config.AutowireCapableBeanFactory;
import mini.spring.ioc.factory.config.BeanPostProcessor;
import mini.spring.ioc.factory.config.DisposableBean;
import mini.spring.ioc.factory.config.InitializingBean;
import mini.spring.ioc.factory.exception.BeanException;
import mini.spring.ioc.strategy.InstantiationStrategy;
import mini.spring.ioc.strategy.impl.JdkInstantiationStrategy;

import java.lang.reflect.Method;


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

        //BeanFactoryAware 实际意义不大,主要是为了BeanFactory入口
        if (object instanceof BeanFactoryAware) {
            ((BeanFactoryAware) object).setBeanFactory(this);
        }

        try {
            object = initializeBean(name, object, beanDefinition);
        } catch (Exception e) {
            throw new BeanException("initializeBean failed");
        }

        //init方法可以在属性注入之后处理,但是destroy必须得在Bean移除的时候,执行该方法
        //因此我们得找个地方,把bean保存
        wrapIfDisposeBean(name, object, beanDefinition);

        //加入缓存中
        addSingleton(name, object);

        return object;
    }

    private void wrapIfDisposeBean(String beanName, Object object, BeanDefinition beanDefinition) {
        if (object instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethod())) {
            //未实现DisposableBean的object,如何转换为DisposableBean实现类
            //可考虑 适配器设计模式
            registerDisposeBean(beanName, new DisposableBeanAdapter(beanName, object, beanDefinition));
        }
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


    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {

        //前置
        Object wrappedBean = applyBeanPostProcessorBeforeInitialization(beanName, bean);

        invokeInitBean(beanName, wrappedBean, beanDefinition);

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
    protected void invokeInitBean(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        //执行属性初始化方法
        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterProperties();
        }

        //如果有指定默认的初始化函数,则通过反射执行该方法
        String initMethodName = beanDefinition.getInitMethod();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethodName);
            if (initMethod == null) {
                throw new BeanException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            initMethod.invoke(bean);
        }
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
