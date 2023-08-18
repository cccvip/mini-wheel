/*
 * All Rights Reserved.
 *
 */
package mini.spring.ioc.context;


import mini.spring.ioc.factory.exception.BeanException;
import mini.spring.ioc.factory.support.DefaultListableBeanFactory;

/**
 * AbstractRefreshableApplicationContext.
 *
 * @author Carl, 2023-08-14 14:01
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;


    public void refreshBeanFactory() throws BeanException {
        //创建Bean工厂
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    public abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeanException;

    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

}
