/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.datasource.pooled;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mini.mybaits.datasource.unpooled.UnpoolDataSource;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

/**
 * PooledDataSource.
 * 数据库资源连接池
 * 1 池化技术维持的是活跃链接,不会主动将其关闭
 *
 * @author Carl, 2023-09-14 14:59
 */
public class PooledDataSource implements DataSource {

    private Logger logger = LogManager.getLogger(PooledDataSource.class);

    private final PooledState state = new PooledState(this);

    private UnpoolDataSource dataSource;

    public PooledDataSource() {
        this.dataSource = new UnpoolDataSource();
    }

    //最大连接数
    private int poolMaximumActiveConnections = 10;

    //数据源
    private long pooledMaxCheckTimeOut = 20000;

    //数据池资源等待时间
    protected int poolTimeToWait = 20000;

    protected int poolMaximumIdleConnections = 5;

    //取空闲链接
    private PooledConnection getFreeConnection(String username, String password) throws SQLException {
        //统计当前加载失败次数
        int localBadConnectionCount = 0;
        PooledConnection connection = null;
        while (connection == null) {
            synchronized (state) {
                //空闲链接为0
                if (!state.idleConnections.isEmpty()) {
                    connection = state.idleConnections.remove(0);
                    logger.info("get connection hashcode {}", connection.getRealConnectionHashCode());
                } else {
                    // 活跃连接数不足,需要生成连接
                    if (state.activeConnections.size() < poolMaximumActiveConnections) {
                        connection = new PooledConnection(dataSource.getConnection(), this);
                        logger.info("Created connection {}", connection.getRealConnectionHashCode());
                    } else {
                        // 取得活跃链接列表的第一个，也就是最老的一个连接
                        PooledConnection oldestActiveConnection = state.activeConnections.get(0);
                        long lifeTimeStamp = oldestActiveConnection.lifeTimeStamp();

                        if (lifeTimeStamp > pooledMaxCheckTimeOut) {
                            state.activeConnections.remove(oldestActiveConnection);
                            //如果当前设置的事务提交是非自动提交的,还需要考虑讲其操作回滚
                            if (!oldestActiveConnection.getRealConnection().getAutoCommit()) {
                                oldestActiveConnection.getRealConnection().rollback();
                            }
                            // 删掉最老的链接，然后重新实例化一个新的链接
                            connection = new PooledConnection(oldestActiveConnection.getRealConnection(), this);
                            oldestActiveConnection.invalidate();
                            logger.info("Claimed overdue connection " + connection.getRealConnectionHashCode() + ".");
                        } else {
                            try {
                                logger.info("Waiting as long as " + poolTimeToWait + " milliseconds for connection.");
                                state.wait(poolTimeToWait);
                            } catch (InterruptedException e) {
                                logger.error("{}", e.getLocalizedMessage());
                                break;
                            }
                        }
                    }
                }

                if (connection != null) {
                    if (connection.isValid()) {
                        if (!connection.getRealConnection().getAutoCommit()) {
                            connection.getRealConnection().rollback();
                        }
                        connection.setCreateTimeStamp(System.currentTimeMillis());
                        state.activeConnections.add(connection);
                    } else {
                        logger.info("A bad connection (" + connection.getRealConnectionHashCode() + ") was returned from the pool, getting another connection.");
                        // 如果没拿到，统计信息：失败链接 +1
                        connection = null;
                        localBadConnectionCount++;

                        // 失败次数较多，抛异常
                        if (localBadConnectionCount > (poolMaximumIdleConnections + 3)) {
                            logger.debug("PooledDataSource: Could not get a good connection to the database.");
                            throw new SQLException("PooledDataSource: Could not get a good connection to the database.");
                        }
                    }
                }
            }
        }
        if (connection == null) {
            logger.debug("PooledDataSource: Unknown severe error condition.  The connection pool returned a null connection.");
            throw new SQLException("PooledDataSource: Unknown severe error condition.  The connection pool returned a null connection.");

        }
        return connection;
    }

    //回收
    protected void pushConnection(PooledConnection connection) throws SQLException {
        synchronized (state) {
            state.activeConnections.remove(connection);
            // 判断链接是否有效
            if (connection.isValid()) {
                // 如果空闲链接小于设定数量，也就是太少时
                if (state.idleConnections.size() < poolMaximumIdleConnections) {
                    // 它首先检查数据库连接是否处于自动提交模式，如果不是，则调用rollback()方法执行回滚操作。
                    // 在MyBatis中，如果没有开启自动提交模式，则需要手动提交或回滚事务。因此，这段代码可能是在确保操作完成后，如果没有开启自动提交模式，则执行回滚操作。
                    // 总的来说，这段代码用于保证数据库的一致性，确保操作完成后，如果未开启自动提交模式，则执行回滚操作。
                    if (!connection.getRealConnection().getAutoCommit()) {
                        connection.getRealConnection().rollback();
                    }
                    // 实例化一个新的DB连接，加入到idle列表
                    PooledConnection newConnection = new PooledConnection(connection.getRealConnection(), this);
                    state.idleConnections.add(newConnection);
                    connection.invalidate();
                    logger.info("Returned connection " + newConnection.getRealConnectionHashCode() + " to pool.");
                    // 通知其他线程可以来抢DB连接了
                    state.notifyAll();
                }
                // 否则，空闲链接还比较充足
                else {
                    if (!connection.getRealConnection().getAutoCommit()) {
                        connection.getRealConnection().rollback();
                    }
                    // 将connection关闭
                    connection.getRealConnection().close();
                    logger.info("Closed connection " + connection.getRealConnectionHashCode() + ".");
                    connection.invalidate();
                }
            } else {
                logger.info("A bad connection (" + connection.getRealConnectionHashCode() + ") attempted to return to the pool, discarding connection.");
            }
        }
    }

    //关闭所有连接
    private void closeAllConnection() {
        synchronized (state) {
            // 关闭活跃链接
            for (int i = state.activeConnections.size(); i > 0; i--) {
                try {
                    PooledConnection conn = state.activeConnections.remove(i - 1);
                    conn.invalidate();

                    Connection realConn = conn.getRealConnection();
                    if (!realConn.getAutoCommit()) {
                        realConn.rollback();
                    }
                    realConn.close();
                } catch (Exception ignore) {

                }
            }
            // 关闭空闲链接
            for (int i = state.idleConnections.size(); i > 0; i--) {
                try {
                    PooledConnection conn = state.idleConnections.remove(i - 1);
                    conn.invalidate();

                    Connection realConn = conn.getRealConnection();
                    if (!realConn.getAutoCommit()) {
                        realConn.rollback();
                    }
                } catch (Exception ignore) {

                }
            }
            logger.info("PooledDataSource forcefully closed/removed all connections.");
        }

    }


    @Override
    public Connection getConnection() throws SQLException {
        return getFreeConnection(dataSource.getUsername(), dataSource.getPassword()).getProxyConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return getFreeConnection(username, password).getProxyConnection();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException(getClass().getName() + " is not a wrapper.");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return DriverManager.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        DriverManager.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        DriverManager.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return DriverManager.getLoginTimeout();
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return java.util.logging.Logger.getLogger(java.util.logging.Logger.GLOBAL_LOGGER_NAME);
    }


    public void setDriver(String driver) {
        dataSource.setDriver(driver);
        closeAllConnection();
    }

    public void setUrl(String url) {
        dataSource.setUrl(url);
        closeAllConnection();
    }

    public void setUsername(String username) {
        dataSource.setUsername(username);
        closeAllConnection();
    }

    public void setPassword(String password) {
        dataSource.setPassword(password);
        closeAllConnection();
    }

}
