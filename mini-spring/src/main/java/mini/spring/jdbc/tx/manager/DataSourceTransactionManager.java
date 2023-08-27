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
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataSourceTransactionManager.
 *
 * @author Carl, 2023-08-24 16:36
 */
public class DataSourceTransactionManager extends AbstractPlatformTransactionManager {

    private DataSource dataSource;

    @Override
    protected void doCommit(SimpleTransactionStatus status) throws TransactionException {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
        Connection con = txObject.getConnectionHolder().getCurrentConnection();
        try {
            con.commit();
        } catch (SQLException ex) {
            throw new TransactionException("Could not commit JDBC transaction", ex);
        }
    }

    @Override
    public void doCleanupAfterCompletion(Object transaction) {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) transaction;

        // Remove the connection holder from the thread, if exposed.
        TransactionSynchronizationManager.unbindResource(obtainDataSource());

        // Reset connection.
        Connection con = txObject.getConnectionHolder().getCurrentConnection();

        try {
            DataSourceUtils.doCloseConnection(con, this.dataSource);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        txObject.getConnectionHolder().clear();
    }

    @Override
    protected void doBegin(Object transaction) throws TransactionException {
        Connection con = null;
        try {
            DataSourceTransactionObject txObject = (DataSourceTransactionObject) transaction;
            con = txObject.getConnectionHolder().getCurrentConnection();
            if (con.getAutoCommit()) {
                con.setAutoCommit(false);
            }
            TransactionSynchronizationManager.bindResource(obtainDataSource(), txObject.getConnectionHolder());

        } catch (Exception e) {
            try {
                DataSourceUtils.doCloseConnection(con, obtainDataSource());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    protected void doRollback(SimpleTransactionStatus status) throws TransactionException {
        DataSourceTransactionObject txObject = (DataSourceTransactionObject) status.getTransaction();
        Connection con = txObject.getConnectionHolder().getCurrentConnection();
        try {
            con.rollback();
        } catch (SQLException ex) {
            throw new TransactionException("Could not commit JDBC transaction", ex);
        }
    }

    @Override
    protected Object doGetTransaction() throws TransactionException {
        DataSourceTransactionObject txObject = new DataSourceTransactionObject();

        ConnectionHolder conHolder = (ConnectionHolder) TransactionSynchronizationManager.doGetResource(obtainDataSource());
        txObject.setConnectionHolder(conHolder);
        return txObject;
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
