/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.manager;


import cn.hutool.core.lang.Assert;
import mini.spring.jdbc.tx.TransactionException;
import mini.spring.jdbc.tx.status.SimpleTransactionStatus;
import mini.spring.jdbc.tx.sync.TransactionSynchronizationManager;

import javax.sql.DataSource;

/**
 * DataSourceTransactionManager.
 *
 * @author Carl, 2023-08-24 16:36
 */
public class DataSourceTransactionManager extends AbstractPlatformTransactionManager {

    private DataSource dataSource;

    @Override
    protected void doCommit(SimpleTransactionStatus status) throws TransactionException {

    }

    @Override
    protected Object doGetTransaction() throws TransactionException {
        DataSourceTransactionObject txObject = new DataSourceTransactionObject();

        ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.doGetResource(obtainDataSource());

        return null;
    }

    protected DataSource obtainDataSource() {
        DataSource dataSource = getDataSource();
        Assert.state(dataSource != null, "No DataSource set");
        return dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
