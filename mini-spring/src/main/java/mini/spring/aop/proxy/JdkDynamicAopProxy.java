package mini.spring.aop.proxy;

import mini.spring.aop.support.AopAdvised;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/19
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    AopAdvised aopAdvised;

    public JdkDynamicAopProxy(AopAdvised aopAdvised) {
        this.aopAdvised = aopAdvised;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), aopAdvised.getTarget().getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //代理方法
        if (aopAdvised.getMethodMatcher().matches(method, aopAdvised.getTarget().getClass())) {
            MethodInterceptor methodInterceptor = aopAdvised.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(aopAdvised.getTarget(), method, args));
        }
        return method.invoke(aopAdvised.getTarget(), args);
    }


}
