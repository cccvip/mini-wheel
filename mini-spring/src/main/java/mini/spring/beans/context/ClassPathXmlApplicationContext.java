/*
 * All Rights Reserved.
 *
 */
package mini.spring.beans.context;


import mini.spring.beans.factory.exception.BeanException;
import mini.spring.beans.resources.Resources;

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
