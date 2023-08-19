package mini.spring.aop.proxy;

import net.sf.cglib.proxy.MethodProxy;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @description: Aop拦截
 * @author：carl
 * @date: 2023/8/19
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    private final Object target;

    private final Method method;

    private final Object[] arguments;

    private MethodProxy methodProxy;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.methodProxy = methodProxy;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {

        if (null != methodProxy) {
            return methodProxy.invoke(target, arguments);
        }
        return method.invoke(target, arguments);

    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
