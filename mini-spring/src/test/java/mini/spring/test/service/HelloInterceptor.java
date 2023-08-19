package mini.spring.test.service;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/19
 */
public class HelloInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Do something before the earth explodes");
        Object result = invocation.proceed();
        System.out.println("Do something after the earth explodes");
        return result;
    }
}
