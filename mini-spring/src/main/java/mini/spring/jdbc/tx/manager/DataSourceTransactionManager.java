/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.manager;


import mini.spring.jdbc.tx.TransactionException;
import mini.spring.jdbc.tx.status.SimpleTransactionStatus;

/**
 * DataSourceTransactionManager.
 *
 * @author Carl, 2023-08-24 16:36
 */
public class DataSourceTransactionManager extends AbstractPlatformTransactionManager {

    @Override
    protected void doCommit(SimpleTransactionStatus status) throws TransactionException {
        
    }

    @Override
    protected Object doGetTransaction() throws TransactionException {


        return null;
    }
}
