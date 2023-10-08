/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.datasource.pooled;


import java.util.ArrayList;
import java.util.List;

/**
 * PooledState.
 * 连接池状态
 * @author Carl, 2023-09-22 14:19
 */
public class PooledState {

    private PooledDataSource pooledDataSource;

    // 空闲链接
    protected final List<PooledConnection> idleConnections = new ArrayList<>();
    // 活跃链接
    protected final List<PooledConnection> activeConnections = new ArrayList<>();

    public PooledState(PooledDataSource pooledDataSource) {
        this.pooledDataSource = pooledDataSource;
    }

    public PooledDataSource getPooledDataSource() {
        return pooledDataSource;
    }

    public void setPooledDataSource(PooledDataSource pooledDataSource) {
        this.pooledDataSource = pooledDataSource;
    }

    public List<PooledConnection> getIdleConnections() {
        return idleConnections;
    }

    public List<PooledConnection> getActiveConnections() {
        return activeConnections;
    }
}
