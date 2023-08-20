package mini.spring.test.service;

import mini.spring.aop.advice.BeforeAdvice;

import java.lang.reflect.Method;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/19
 */
public class AMethodBeforeInterceptor implements BeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before method");
    }
}
