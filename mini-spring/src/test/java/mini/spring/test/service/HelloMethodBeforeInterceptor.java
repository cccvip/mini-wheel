package mini.spring.test.service;

import mini.spring.aop.advice.BeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/19
 */
public class HelloMethodBeforeInterceptor implements BeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before method");
    }
}
