package mini.spring.aop.advice;

import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/20
 */
public interface BeforeAdvice extends Advice {
    void before(Method method, Object[] args, Object target) throws Throwable;
}
