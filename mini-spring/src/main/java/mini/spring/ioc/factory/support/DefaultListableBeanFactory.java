package mini.spring.ioc.factory.support;

import mini.spring.ioc.factory.bean.BeanDefinition;
import mini.spring.ioc.factory.bean.ConfigurableListableBeanFactory;
import mini.spring.ioc.factory.config.BeanDefinitionRegistry;
import mini.spring.ioc.factory.exception.BeanException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public class DefaultListableBeanFactory extends AbstractAutoWireCapableBeanFactory
        implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return beanDefinitionMap.get(name);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeanException {
        Map<String, T> result = new HashMap<>();
        for (String key : beanDefinitionMap.keySet()) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(key);
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                T bean = (T) getBean(key);
                result.put(key, bean);
            }
        }
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> beanNames = beanDefinitionMap.keySet();
        return beanNames.toArray(new String[beanNames.size()]);
    }

    @Override
    public void preInstantiateSingletons() throws BeanException {
        for (String key : beanDefinitionMap.keySet()) {
            getBean(key);
        }
    }
}
