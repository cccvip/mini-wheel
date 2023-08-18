/*
 * All Rights Reserved.
 *
 */
package mini.spring.ioc.context;


import mini.spring.ioc.factory.aware.ApplicationContextAwareProcessor;
import mini.spring.ioc.factory.bean.ConfigurableListableBeanFactory;
import mini.spring.ioc.factory.config.BeanFactoryPostProcessor;
import mini.spring.ioc.factory.config.BeanPostProcessor;
import mini.spring.ioc.factory.exception.BeanException;
import mini.spring.ioc.listener.ContextFinishedContext;
import mini.spring.ioc.listener.event.ApplicationEvent;
import mini.spring.ioc.listener.event.ApplicationListener;
import mini.spring.ioc.listener.impl.AbstractApplicationEventMulticaster;
import mini.spring.ioc.listener.impl.SimpleApplicationEventMulticaster;
import mini.spring.ioc.resources.impl.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * AbstractApplicationContext.
 *
 * @author Carl, 2023-08-14 11:01
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private AbstractApplicationEventMulticaster abstractApplicationEventMulticaster;

    //创建BeanFactory
    //读取XML文件
    //BeanFactoryPostProcessor
    //前置处理 BeanPostProcessor before
    //bean initMethod();
    //后置处理 BeanPostProcessor after
    //bean实例化完成
    //注册
    @Override
    public void refresh() throws BeanException {
        //beanFactory
        refreshBeanFactory();

        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //添加全局上下文
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //在bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        //添加BeanPostProcessor
        invokeBeanPostProcessors(beanFactory);

        initMultiCaster(beanFactory);

        registerApplicationListener();

        //核心步骤
        //初始化单例Bean
        beanFactory.preInstantiateSingletons();

        //推送初始化完成的事件
        publishFinishContext();
    }

    private void publishFinishContext() throws BeanException {
        publishApplicationEvent(new ContextFinishedContext(this));
    }

    //创建监听器的bean
    private void initMultiCaster(ConfigurableListableBeanFactory beanFactory) {
        abstractApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, abstractApplicationEventMulticaster);
    }

    //开启事件监听
    private void registerApplicationListener() throws BeanException {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            abstractApplicationEventMulticaster.addListener(applicationListener);
        }
    }

    //执行BeanPostProcessor
    private void invokeBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        Map<String, BeanPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    //执行BeanFactoryPostProcessor
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor factoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            factoryPostProcessor.postProcessBeanFactory(beanFactory);
        }

    }

    //创建BeanFactory
    public abstract void refreshBeanFactory() throws BeanException;

    //获取BeanFactory
    public abstract ConfigurableListableBeanFactory getBeanFactory();


    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String beanName) throws BeanException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public void publishApplicationEvent(ApplicationEvent applicationEvent) throws BeanException {
        abstractApplicationEventMulticaster.multiEvent(applicationEvent);
    }

    @Override
    public void registerShutdownWork() {
        Thread shutdownHook = new Thread(() -> {
            try {
                doClose();
            } catch (BeanException e) {
                e.printStackTrace();
            }
        });
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }

    private void doClose() throws BeanException {
        getBeanFactory().destroyBeans();
    }

}
