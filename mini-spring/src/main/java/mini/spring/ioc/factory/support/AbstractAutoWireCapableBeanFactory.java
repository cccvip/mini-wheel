package mini.spring.ioc.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import mini.spring.aop.bean.InstantiationAwareBeanPostProcessor;
import mini.spring.ioc.factory.aware.BeanFactoryAware;
import mini.spring.ioc.factory.bean.BeanDefinition;
import mini.spring.ioc.factory.bean.BeanReference;
import mini.spring.ioc.factory.bean.ObjectFactory;
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
        //创建实例化是否需要创建对应的代理对象,非真正的对象
        Object bean = resolveBeforeInstantiation(name, beanDefinition);
        if (bean != null) {
            return bean;
        }
        //真正的Bean
        return doCreateBean(name, beanDefinition);
    }

    private Object resolveBeforeInstantiation(String name, BeanDefinition beanDefinition) throws BeanException {
        Object bean = applyBeanPostProcessorsBeforeInstantiation(beanDefinition.getBeanClass(), name);
        if (bean != null) {
            bean = applyBeanPostProcessorAfterInitialization(name, bean);
        }
        return bean;
    }

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class beanClass, String beanName) throws BeanException {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    Object doCreateBean(String name, BeanDefinition beanDefinition) throws BeanException {
        Object object = instantiationStrategy.instantiate(beanDefinition);

        //属性未填充,也不影响,后续流程会继续填充数据
        //可以看到二级缓存保存的是一个半成品, bean初始化,未实例化 但是不能解决Aop暴露的Bean的问题
        // earlySingletonObjects.put(name, object);

        //我们需要暴露的Bean是一个代理的bean,而不是完全实例化后的bean
        if(beanDefinition.getSingleton()){
            Object finalObject = object;
            addSingletonFactory(name, new ObjectFactory<Object>() {
                @Override
                public Object getObject() throws BeanException {
                    return getEarlyBeanReference(name, beanDefinition, finalObject);
                }
            });
        }

        //如果当前类被Aop拦截 则暂时不执行后续逻辑,直接返回当前代理类
        boolean continueWithPropertyPopulation = applyBeanPostProcessorsAfterInstantiation(name, object);
        if (!continueWithPropertyPopulation) {
            return object;
        }
        //属性填充
        applyPropertyValues(name, object, beanDefinition);

        try {
            object = initializeBean(name, object, beanDefinition);
        } catch (Exception e) {
            throw new BeanException("initializeBean failed");
        }

        //init方法可以在属性注入之后处理
        //但是destroy必须得在Bean移除的时候,执行该方法
        //因此我们得找个地方,把bean保存
        wrapIfDisposeBean(name, object, beanDefinition);

        Object exposedObject = object;
        if (beanDefinition.getSingleton()) {
            //如果有代理对象，此处获取代理对象
            exposedObject = getSingleton(name);

            addSingleton(name, object);
        }
        return exposedObject;
    }

    protected Object getEarlyBeanReference(String beanName, BeanDefinition beanDefinition, Object bean) throws BeanException {
        Object exposedObject = bean;
        for (BeanPostProcessor bp : getBeanPostProcessors()) {
            if (bp instanceof InstantiationAwareBeanPostProcessor) {
                exposedObject = ((InstantiationAwareBeanPostProcessor) bp).getEarlyBeanReference(exposedObject, beanName);
                if (exposedObject == null) {
                    return exposedObject;
                }
            }
        }

        return exposedObject;
    }

    private void wrapIfDisposeBean(String beanName, Object object, BeanDefinition beanDefinition) {
        if (beanDefinition.getSingleton() && (object instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethod()))) {
            //未实现DisposableBean的object,如何转换为DisposableBean实现类
            //可考虑 适配器设计模式
            registerDisposeBean(beanName, new DisposableBeanAdapter(beanName, object, beanDefinition));
        }
    }

    /**
     * bean实例化后执行，如果返回false，不执行后续设置属性的逻辑
     *
     * @param beanName
     * @param bean
     * @return
     */
    private boolean applyBeanPostProcessorsAfterInstantiation(String beanName, Object bean) throws BeanException {
        boolean continueWithPropertyPopulation = true;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                if (!((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessAfterInstantiation(bean, beanName)) {
                    continueWithPropertyPopulation = false;
                    break;
                }
            }
        }
        return continueWithPropertyPopulation;
    }

    private void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeanException {
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getName());
                } else {
//                    System.out.println(beanName);
//                    System.out.println("=============");
                }
                System.out.println(bean);
                System.out.println(name);
                System.out.println(value);
                System.out.println("=============");
                //通过反射设置属性
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BeanException("Error setting property values for bean: " + beanName, ex);
        }
    }



    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {
        //BeanFactoryAware 实际意义不大,主要是为了BeanFactory入口
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }

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
