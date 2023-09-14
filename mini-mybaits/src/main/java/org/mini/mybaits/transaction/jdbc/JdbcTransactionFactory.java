package org.mini.mybaits.transaction.jdbc;

import org.mini.mybaits.transaction.Transaction;
import org.mini.mybaits.transaction.TransactionFactory;
import org.mini.mybaits.transaction.TransactionIsolationLevel;

import javax.sql.DataSource;
import java.sql.Connection;

public class JdbcTransactionFactory implements TransactionFactory {

    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }


    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }

}
