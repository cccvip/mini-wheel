package mini.spring.beans.resources.impl;

import mini.spring.beans.resources.ResourceLoader;
import mini.spring.beans.resources.Resources;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/12
 */
public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resources getResource(String location) {
        return new ClassPathResource(location);
    }
}
