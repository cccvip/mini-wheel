package mini.spring.aop.support;

import mini.spring.aop.aspectj.MethodMatcher;
import org.aopalliance.intercept.MethodInterceptor;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/19
 */
public class AopAdvised {

    //拦截的目标
    Object target;

    //拦截器
    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    //是否使用cglib proxy
    private boolean cglibProxy;

    public boolean isCglibProxy() {
        return cglibProxy;
    }

    public void setCglibProxy(boolean cglibProxy) {
        this.cglibProxy = cglibProxy;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
