/*
 * All Rights Reserved.
 *
 */
package mini.spring.ioc.reader;


import mini.spring.ioc.factory.config.BeanDefinitionRegistry;
import mini.spring.ioc.factory.exception.BeanException;
import mini.spring.ioc.resources.Resources;

/**
 * AnnotationBeanDefinitionReader.
 * 
 * @author Carl, 2023-08-30 9:19
 */
public class AnnotationBeanDefinitionReader extends AbstractBeanDefinitionReader{

    protected AnnotationBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void loadBeanDefinitions(Resources resources) throws BeanException {

    }


}
