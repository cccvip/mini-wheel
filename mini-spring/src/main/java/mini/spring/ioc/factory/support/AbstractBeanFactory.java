package mini.spring.ioc.factory.support;

import mini.spring.ioc.factory.bean.BeanDefinition;
import mini.spring.ioc.factory.bean.ConfigurableBeanFactory;
import mini.spring.ioc.factory.bean.FactoryBean;
import mini.spring.ioc.factory.config.BeanPostProcessor;
import mini.spring.ioc.factory.exception.BeanException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory {

    protected List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    //缓存
    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    @Override
    public Object getBean(String key) throws BeanException {
        //从缓存里取数据
        Object bean = getSingleton(key);

        if (null != bean) {
            return getObjectForBeanInstance(bean, key);
        }

        //1 定义BeanDefinition
        BeanDefinition beanDefinition = getBeanDefinition(key);

        bean = createBean(key, beanDefinition);

        //2 放入cache中
        return getObjectForBeanInstance(bean, key);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeanException {
        return ((T) getBean(name));
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName) throws BeanException {
        Object object = beanInstance;
        if (beanInstance instanceof FactoryBean) {
            FactoryBean factoryBean = (FactoryBean) beanInstance;
            try {
                if (factoryBean.isSingleton()) {
                    object = this.factoryBeanObjectCache.get(beanName);
                    if (object == null) {
                        object = factoryBean.getObject();
                        this.factoryBeanObjectCache.put(beanName, object);
                    }
                } else {
                    //prototype作用域bean，不实现
                }

            } catch (Exception ex) {
                throw new BeanException("FactoryBean threw exception on object[" + beanName + "] creation");
            }
        }
        return object;
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessors.add(beanPostProcessor);
    }

    public abstract BeanDefinition getBeanDefinition(String name);

    public abstract Object createBean(String name, BeanDefinition beanDefinition) throws BeanException;
}
