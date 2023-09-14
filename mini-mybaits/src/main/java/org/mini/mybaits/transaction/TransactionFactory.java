/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.transaction;


import javax.sql.DataSource;
import java.sql.Connection;

/**
 * TransactionFactory.
 *
 * @author Carl, 2023-09-14 13:18
 */
public interface TransactionFactory {

    /**
     * newTransaction.
     * 开启事务
     *
     * @param connection
     * @return org.mini.mybaits.transaction.Transaction
     */
    Transaction newTransaction(Connection connection);

    /**
     * newTransaction.
     * 开启事务
     *
     * @param dataSource
     * @param level
     * @param autoCommit
     * @return org.mini.mybaits.transaction.Transaction
     */
    Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit);

}
