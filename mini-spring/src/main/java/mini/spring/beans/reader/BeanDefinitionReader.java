package mini.spring.beans.reader;

import mini.spring.beans.factory.config.BeanDefinitionRegistry;
import mini.spring.beans.factory.exception.BeanException;
import mini.spring.beans.resources.ResourceLoader;
import mini.spring.beans.resources.Resources;

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
