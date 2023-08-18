/*
 * All Rights Reserved.
 *
 */
package mini.spring.ioc.context;


import mini.spring.ioc.factory.exception.BeanException;
import mini.spring.ioc.resources.Resources;

/**
 * ClassPathXmlApplicationContext.
 *
 * @author Carl, 2023-08-14 14:19
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private final Resources resources;

    public ClassPathXmlApplicationContext(Resources resources) throws BeanException {
        this.resources = resources;
        refresh();
    }

    @Override
    public Resources getResource() {
        return resources;
    }

}
