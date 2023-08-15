package mini.spring.beans.factory.support;

import mini.spring.beans.factory.config.DisposableBean;
import mini.spring.beans.factory.config.SingletonBeanRegistry;
import mini.spring.beans.factory.exception.BeanException;

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

    protected Map<String, Object> map = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return map.get(beanName);
    }

    //放入单例缓存中
    public void addSingleton(String name, Object object) {
        map.put(name, object);
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
    }


}
