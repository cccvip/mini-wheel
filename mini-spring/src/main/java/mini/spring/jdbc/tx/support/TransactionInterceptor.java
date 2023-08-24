/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.support;


import mini.spring.aop.support.AopUtils;
import mini.spring.jdbc.tx.TransactionManager;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.io.Serializable;

/**
 * TransactionInterceptor.
 *
 * @author Carl, 2023-08-24 16:17
 */
public class TransactionInterceptor extends TransactionAspectSupport implements MethodInterceptor, Serializable {

    public TransactionInterceptor() {

    }

    public TransactionInterceptor(TransactionManager transactionManager) {
        super(transactionManager);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);

        return invokeWithinTransaction(invocation.getMethod(), targetClass, invocation::proceed);
    }

}
