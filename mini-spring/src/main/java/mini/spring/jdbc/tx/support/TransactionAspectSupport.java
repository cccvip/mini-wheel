/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.support;


import cn.hutool.core.thread.threadlocal.NamedThreadLocal;
import com.sun.istack.internal.Nullable;
import mini.spring.ioc.factory.aware.BeanFactoryAware;
import mini.spring.ioc.factory.config.BeanFactory;
import mini.spring.ioc.factory.config.InitializingBean;
import mini.spring.ioc.factory.exception.BeanException;
import mini.spring.jdbc.tx.DataSourceTransactionManager;
import mini.spring.jdbc.tx.InvocationCallback;
import mini.spring.jdbc.tx.TransactionException;
import mini.spring.jdbc.tx.TransactionManager;
import mini.spring.jdbc.tx.status.TransactionInfo;
import mini.spring.jdbc.tx.status.TransactionStatus;
import mini.spring.jdbc.tx.manager.PlatformTransactionManager;

import java.lang.reflect.Method;

/**
 * TransactionAspectSupport.
 *
 * @author Carl, 2023-08-24 15:04
 */
public abstract class TransactionAspectSupport implements BeanFactoryAware, InitializingBean {

    public TransactionAspectSupport() {

    }

    public TransactionAspectSupport(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    private BeanFactory beanFactory;

    private TransactionManager transactionManager;

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    private static final ThreadLocal<TransactionInfo> transactionInfoHolder = new NamedThreadLocal<>("Current aspect-driven transaction");

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeanException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterProperties() throws BeanException {
        if (null == getTransactionManager() && this.beanFactory == null) {
            throw new BeanException(
                    "Set the 'transactionManager' property or make sure to run within a BeanFactory " +
                            "containing a TransactionManager bean!");
        }
    }

    @Nullable
    protected TransactionManager determineTransactionManager() {
        return new DataSourceTransactionManager();
    }

    //相比Spring的实现,移除了大部分的代码,只保留核心代码部分
    protected Object invokeWithinTransaction(Method method, @Nullable Class<?> targetClass,
                                             final InvocationCallback invocation) throws Throwable {
        TransactionManager ptm = determineTransactionManager();
        //执行方法
        final String joinpointIdentification = methodIdentification(method, targetClass);
        //开启事务
        TransactionInfo txInfo = createTransactionIfNecessary((PlatformTransactionManager) ptm, joinpointIdentification);
        Object retVal;
        try {
            retVal = invocation.proceedWithInvocation();
        } catch (Throwable ex) {
            completeTransactionAfterThrowing(txInfo, ex);
            throw ex;
        } finally {
            cleanupTransactionInfo(txInfo);
        }
        commitTransactionAfterReturning(txInfo);
        return retVal;
    }

    private String methodIdentification(Method method, @Nullable Class<?> clazz) {
        return (clazz != null ? clazz : method.getDeclaringClass()).getName() + '.' + method.getName();
    }

    //创建TransactionInfo数据
    protected TransactionInfo createTransactionIfNecessary(@Nullable PlatformTransactionManager tm,
                                                           final String joinpointIdentification) throws TransactionException {
        TransactionStatus status = null;
        if (tm != null) {
            status = tm.getTransaction();
        }
        return prepareTransactionInfo(tm, joinpointIdentification, status);
    }

    protected TransactionInfo prepareTransactionInfo(@Nullable PlatformTransactionManager tm, String joinpointIdentification,
                                                     @Nullable TransactionStatus status) {

        TransactionInfo txInfo = new TransactionInfo(tm, joinpointIdentification);
        txInfo.setTransactionStatus(status);

        txInfo.setOldTransactionInfo(transactionInfoHolder.get());
        //绑定到线程
        bindToThread(txInfo);
        return txInfo;
    }

    protected void completeTransactionAfterThrowing(@Nullable TransactionInfo txInfo, Throwable ex) {
        if (txInfo != null && txInfo.getTransactionStatus() != null) {
            try {
                txInfo.getTransactionManager().rollback(txInfo.getTransactionStatus());
            } catch (RuntimeException | Error ex2) {
                throw ex2;
            } catch (TransactionException e) {
                e.printStackTrace();
            }
        }
    }

    private void cleanupTransactionInfo(@Nullable TransactionInfo txInfo) {
        if (txInfo != null) {
            transactionInfoHolder.set(txInfo.getOldTransactionInfo());
        }
    }

    private void bindToThread(TransactionInfo txInfo) {
        transactionInfoHolder.set(txInfo);
    }


    protected void commitTransactionAfterReturning(@Nullable TransactionInfo txInfo) throws TransactionException {
        if (txInfo != null && txInfo.getTransactionStatus() != null) {
            txInfo.getTransactionManager().commit(txInfo.getTransactionStatus());
        }
    }

}
