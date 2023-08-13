package mini.spring.beans.reader;

import mini.spring.beans.factory.config.BeanDefinitionRegistry;
import mini.spring.beans.resources.ResourceLoader;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/13
 */
public interface BeanDefinitionReader {

    ResourceLoader getResourceLoader();


    BeanDefinitionRegistry getBeanDefinitionRegistry();




}
