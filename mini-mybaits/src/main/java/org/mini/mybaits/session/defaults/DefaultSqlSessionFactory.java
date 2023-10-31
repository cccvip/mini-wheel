/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session.defaults;


import org.mini.mybaits.executor.Executor;
import org.mini.mybaits.mapping.Environment;
import org.mini.mybaits.session.Configuration;
import org.mini.mybaits.session.SqlSession;
import org.mini.mybaits.session.SqlSessionFactory;
import org.mini.mybaits.transaction.Transaction;
import org.mini.mybaits.transaction.TransactionFactory;
import org.mini.mybaits.transaction.TransactionIsolationLevel;

import java.sql.SQLException;

/**
 * DefaultSqlSessionFactory.
 *
 * @author Carl, 2023-08-30 15:08
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {

        Transaction tx = null;
        try {
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            // 创建DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }

    }

}
