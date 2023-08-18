/*
 * All Rights Reserved.
 *
 */
package mini.spring.aop;


/**
 * ClassFilter.
 * 
 * @author Carl, 2023-08-18 14:37
 */
@FunctionalInterface
public interface ClassFilter {

    /**
     * 类过滤器Filter
     */
    Boolean matches(Class<?> clazz);

}
