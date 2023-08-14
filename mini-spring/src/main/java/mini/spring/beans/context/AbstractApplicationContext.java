/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package mini.spring.beans.context;


import mini.spring.beans.factory.bean.ConfigurableListableBeanFactory;
import mini.spring.beans.factory.config.BeanFactoryPostProcessor;
import mini.spring.beans.factory.config.BeanPostProcessor;
import mini.spring.beans.factory.exception.BeanException;
import mini.spring.beans.resources.impl.DefaultResourceLoader;

import java.util.Map;

/**
 * AbstractApplicationContext.
 *
 * @author Carl, 2023-08-14 11:01
 * @version CrisisGo v1.0
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

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

        //
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //在bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        //添加BeanPostProcessor
        invokeBeanPostProcessors(beanFactory);

        //初始化单例Bean
        beanFactory.preInstantiateSingletons();
    }

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


}
