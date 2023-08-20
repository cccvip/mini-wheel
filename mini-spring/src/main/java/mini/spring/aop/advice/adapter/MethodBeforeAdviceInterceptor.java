package mini.spring.aop.advice.adapter;

import mini.spring.aop.advice.BeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/20
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private BeforeAdvice advice;

    public MethodBeforeAdviceInterceptor() {

    }

    public MethodBeforeAdviceInterceptor(BeforeAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        this.advice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }

    public BeforeAdvice getAdvice() {
        return advice;
    }

    public void setAdvice(BeforeAdvice advice) {
        this.advice = advice;
    }
}
