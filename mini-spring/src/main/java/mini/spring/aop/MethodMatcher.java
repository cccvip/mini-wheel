/*
 * All Rights Reserved.
 *
 */
package mini.spring.aop;


import java.lang.reflect.Method;

/**
 * MethodMatcher.
 *
 * @author Carl, 2023-08-18 14:39
 */
@FunctionalInterface
public interface MethodMatcher {

    /**
     * @param method
     * @param targetClass
     */
    Boolean matches(Method method, Class<?> targetClass);

}
