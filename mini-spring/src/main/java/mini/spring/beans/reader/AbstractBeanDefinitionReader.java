package mini.spring.beans.reader;

import mini.spring.beans.factory.config.BeanDefinitionRegistry;
import mini.spring.beans.resources.ResourceLoader;
import mini.spring.beans.resources.impl.DefaultResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader ;
    }

    @Override
    public BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return registry;
    }
}
