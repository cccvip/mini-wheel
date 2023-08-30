/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.manager;


import mini.spring.jdbc.tx.TransactionException;
import mini.spring.jdbc.tx.status.SimpleTransactionStatus;
import mini.spring.jdbc.tx.status.TransactionStatus;
import mini.spring.jdbc.tx.sync.TransactionSynchronizationManager;

import java.sql.SQLException;
import java.util.List;

/**
 * PlatformTransactionManager.
 *
 * @author Carl, 2023-08-24 15:16
 */
public abstract class AbstractPlatformTransactionManager implements PlatformTransactionManager {

    private TransactionStatus transactionStatus;

    @Override
    public TransactionStatus getTransaction() throws TransactionException {
        //创建一个新的事务
        Object transaction = doGetTransaction();
        return prepareTransactionStatus(transaction, true);
    }

    protected final SimpleTransactionStatus prepareTransactionStatus(Object transaction, boolean newTransaction) {

        SimpleTransactionStatus status = new SimpleTransactionStatus(transaction, newTransaction);
        prepareSynchronization(status);
        return status;
    }

    protected void prepareSynchronization(SimpleTransactionStatus status) {
        TransactionSynchronizationManager.setActualTransactionActive(status.isNewTransaction());
    }


    @Override
    public void commit(TransactionStatus transactionStatus) throws TransactionException {
        processCommit((SimpleTransactionStatus) transactionStatus);
    }

    private void processCommit(SimpleTransactionStatus transactionStatus) throws TransactionException {
        try {
            if (transactionStatus.isNewTransaction()) {
                doCommit(transactionStatus);
            }
        } catch (Exception e) {
            //回滚
            doRollbackOnCommitException(transactionStatus, e);
        } finally {
            //资源关闭
            cleanupAfterCompletion(transactionStatus);
        }
    }

    private void doRollbackOnCommitException(SimpleTransactionStatus status, Throwable ex) throws TransactionException {
        try {
            if (status.isNewTransaction()) {
                doRollback(status);
            }
        } catch (RuntimeException | Error rbex) {
            throw rbex;
        }
    }

    private void cleanupAfterCompletion(SimpleTransactionStatus status) {
        status.setCompleted(true);
        doCleanupAfterCompletion(status.getTransaction());
    }

    @Override
    public void rollback(TransactionStatus t) throws TransactionException {
        SimpleTransactionStatus defStatus = (SimpleTransactionStatus) t;
        processRollback(defStatus, false);
    }

    private void processRollback(SimpleTransactionStatus transactionStatus, boolean b) {

        try {
            doRollback(transactionStatus);
        } catch (Exception e) {

        } finally {
            //资源关闭
            cleanupAfterCompletion(transactionStatus);
        }
    }

    public abstract void doCleanupAfterCompletion(Object transaction) ;

    protected abstract void doBegin(Object transaction) throws TransactionException, SQLException;

    protected abstract void doCommit(SimpleTransactionStatus status) throws TransactionException;

    protected abstract void doRollback(SimpleTransactionStatus status) throws TransactionException;

    protected abstract Object doGetTransaction() throws TransactionException;

}
