/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx;


import javax.sql.DataSource;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataSourceTransactionManager.
 *
 * @author Carl, 2023-08-24 9:28
 */
public class DataSourceTransactionManager implements TransactionManager, InvocationHandler {

    public static final ThreadLocal<TransactionStatus> transactionStatus = new ThreadLocal<>();

    private DataSource dataSource;

    public DataSourceTransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TransactionStatus transaction = transactionStatus.get();
        if (transaction == null) {
            try (Connection connection = dataSource.getConnection()) {
                final boolean autoCommit = connection.getAutoCommit();
                if (autoCommit) {
                    connection.setAutoCommit(false);
                }
                try {
                    transactionStatus.set(new TransactionStatus(connection));
                    Object r = method.invoke(proxy, args);
                    connection.commit();
                    return r;
                } catch (InvocationTargetException e) {
                    TransactionException te = new TransactionException(e.getCause());
                    try {
                        connection.rollback();
                    } catch (SQLException sqle) {
                        te.addSuppressed(sqle);
                    }
                    throw te;
                } finally {
                    transactionStatus.remove();
                    if (autoCommit) {
                        connection.setAutoCommit(true);
                    }
                }
            }
        } else {
            return method.invoke(proxy, args);
        }
    }
}
