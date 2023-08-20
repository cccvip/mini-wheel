package mini.spring.ioc.factory.support;

import mini.spring.ioc.factory.bean.ObjectFactory;
import mini.spring.ioc.factory.config.DisposableBean;
import mini.spring.ioc.factory.config.SingletonBeanRegistry;
import mini.spring.ioc.factory.exception.BeanException;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    //1 保存需要执行destroy的bean
    //2 思考一下何时该执行destroy java中存在一个钩子函数设计,可通过钩子函数执行destroy Bean
    private final Map<String, DisposableBean> disposableBeanMap = new HashMap<>();

    protected Map<String, Object> singletonObjects = new HashMap<>();

    //解决循环依赖问题,引入第二级缓存
    protected Map<String, Object> earlySingletonObjects = new HashMap<>();

    //三级缓存
    protected Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) throws BeanException {
        //如果一级缓存拿不到,从二级缓存拿数据
        //二级缓存拿不到,则从三级缓存工厂类拿到代理对象,将代理Bean放到二级缓存中
        Object bean = singletonObjects.get(beanName);
        if (bean == null) {
            bean = earlySingletonObjects.get(beanName);
            if (bean == null) {
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    bean = singletonFactory.getObject();
                    earlySingletonObjects.put(beanName, bean);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return bean;
    }

    //放入单例缓存中
    public void addSingleton(String name, Object object) {
        //单例缓存
        singletonObjects.put(name, object);
        //移除其他两级缓存
        earlySingletonObjects.remove(name);
        singletonFactories.remove(name);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        singletonFactories.put(beanName, singletonFactory);
    }

    protected void registerDisposeBean(String beanName, DisposableBean disposableBean) {
        disposableBeanMap.put(beanName, disposableBean);
    }

    public void destroyBeans() throws BeanException {
        for (DisposableBean disposableBean : disposableBeanMap.values()) {
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeanException(e.getLocalizedMessage());
            }
        }
        System.out.println("destroy bean finished");
    }

}
