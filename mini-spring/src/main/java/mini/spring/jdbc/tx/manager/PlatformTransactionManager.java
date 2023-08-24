/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.manager;


import mini.spring.jdbc.tx.TransactionException;
import mini.spring.jdbc.tx.TransactionManager;
import mini.spring.jdbc.tx.status.TransactionStatus;

/**
 * PlatformTransactionManager.
 *
 * @author Carl, 2023-08-24 15:16
 */
public interface PlatformTransactionManager extends TransactionManager {

    TransactionStatus getTransaction() throws TransactionException;

    void commit(TransactionStatus transactionStatus) throws TransactionException;

    void rollback(TransactionStatus  transactionStatus) throws TransactionException;
}
