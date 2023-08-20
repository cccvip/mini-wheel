package mini.spring.aop.advice.adapter;

import mini.spring.aop.advice.AfterAdvice;
import mini.spring.aop.advice.BeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/20
 */
public class MethodAfterAdviceInterceptor implements MethodInterceptor {

    private AfterAdvice afterAdvice;

    public MethodAfterAdviceInterceptor(AfterAdvice advice) {
        this.afterAdvice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object bean = methodInvocation.proceed();
        bean = this.afterAdvice.afterAdvice(bean);
        return bean;
    }
}
