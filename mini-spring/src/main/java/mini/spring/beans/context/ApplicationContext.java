/*
 * All Rights Reserved.
 *
 */
package mini.spring.beans.context;


import mini.spring.beans.factory.config.HierarchicalBeanFactory;
import mini.spring.beans.factory.config.ListableBeanFactory;
import mini.spring.beans.resources.ResourceLoader;

/**
 * ApplicationContext. 应用上下文
 * 
 * @author Carl, 2023-08-14 10:54
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader {


}