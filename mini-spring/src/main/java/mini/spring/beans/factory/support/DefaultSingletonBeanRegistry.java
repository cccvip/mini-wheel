package mini.spring.beans.factory.support;

import mini.spring.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected Map<String, Object> map = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return map.get(beanName);
    }

    //放入单例缓存中
    public void addSingleton(String name, Object object) {
        map.put(name, object);
    }


}
