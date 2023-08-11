package mini.spring.beans.factory;


import java.util.HashMap;
import java.util.Map;

/**
 * BeanFactory.
 *
 * @author Carl, 2023-08-11 8:58
 */
public class BeanFactory {
    private Map<String, Object> beanFactory = new HashMap<String, Object>();

    public void registerBean(String key, Object object) {
        beanFactory.put(key,object);
    }

    public Object getBean(String key){
        return beanFactory.get(key);
    }

}
