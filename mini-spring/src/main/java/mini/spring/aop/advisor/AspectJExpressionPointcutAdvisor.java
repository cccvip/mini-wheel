package mini.spring.aop.advisor;

import mini.spring.aop.aspectj.AspectJExpressionPointcut;
import mini.spring.aop.aspectj.PointCut;
import org.aopalliance.aop.Advice;

/**
 * @description: 使用组合模式
 * @author：carl
 * @date: 2023/8/20
 */
public class AspectJExpressionPointcutAdvisor implements PointAdvisor {

    AspectJExpressionPointcut pointcut;

    Advice advice;

    String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public PointCut getPointCut() {
        if (pointcut == null) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }


}
