package mini.spring.ioc.reader;

import mini.spring.ioc.factory.config.BeanDefinitionRegistry;
import mini.spring.ioc.factory.exception.BeanException;
import mini.spring.ioc.resources.ResourceLoader;
import mini.spring.ioc.resources.Resources;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/13
 */
public interface BeanDefinitionReader {

    ResourceLoader getResourceLoader();

    BeanDefinitionRegistry getBeanDefinitionRegistry();

    void loadBeanDefinitions(Resources resources) throws BeanException;

}
