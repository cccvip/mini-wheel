package mini.spring.jdbc.tx.advisor;

import mini.spring.aop.advisor.AspectJExpressionPointcutAdvisor;
import mini.spring.aop.aspectj.PointCut;
import mini.spring.jdbc.tx.support.TransactionInterceptor;
import org.aopalliance.aop.Advice;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/27
 */
public class TransactionAttributeSourceAdvisor extends AspectJExpressionPointcutAdvisor {

    private TransactionInterceptor transactionInterceptor;

    //POINT 切入点

    public TransactionAttributeSourceAdvisor(TransactionInterceptor transactionInterceptor) {
        this.transactionInterceptor = transactionInterceptor;
    }

    public TransactionInterceptor getTransactionInterceptor() {
        return transactionInterceptor;
    }

    public void setTransactionInterceptor(TransactionInterceptor transactionInterceptor) {
        this.transactionInterceptor = transactionInterceptor;
    }

    @Override
    public Advice getAdvice() {
        return this.transactionInterceptor;
    }

    @Override
    public PointCut getPointCut() {
        return super.getPointCut();
    }
}
