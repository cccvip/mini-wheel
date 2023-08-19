package mini.spring.aop.proxy;

import mini.spring.aop.support.AopAdvised;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/19
 */
public class CglibAopProxy implements AopProxy {
    AopAdvised aopAdvised;

    public CglibAopProxy(AopAdvised aopAdvised) {
        this.aopAdvised = aopAdvised;
    }


    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(aopAdvised.getTarget().getClass());
        enhancer.setInterfaces(aopAdvised.getTarget().getClass().getInterfaces());
        enhancer.setCallback(new DynamicAdvisedInterceptor(aopAdvised));
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {
        AopAdvised aopAdvised;

        public DynamicAdvisedInterceptor(AopAdvised aopAdvised) {
            this.aopAdvised = aopAdvised;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            ReflectiveMethodInvocation methodInvocation = new ReflectiveMethodInvocation(aopAdvised.getTarget(), method, objects, methodProxy);
            if (aopAdvised.getMethodMatcher().matches(method, aopAdvised.getTarget().getClass())) {
                //代理方法
                return aopAdvised.getMethodInterceptor().invoke(methodInvocation);
            }
            return methodInvocation.proceed();
        }

    }


}
