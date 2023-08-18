/*
 * All Rights Reserved.
 *
 */
package mini.spring.aop;


import java.lang.reflect.Method;

/**
 * AspectJExpressionPointcut.
 * 
 * @author Carl, 2023-08-18 14:47
 */
public class AspectJExpressionPointcut implements PointCut,ClassFilter,MethodMatcher{


    @Override
    public Boolean matches(Class<?> clazz) {
        return null;
    }

    @Override
    public Boolean matches(Method method, Class<?> targetClass) {
        return null;
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
