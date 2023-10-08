/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.datasource.pooled;


import org.mini.mybaits.datasource.unpooled.UnpoolDataSourceFactory;

import javax.sql.DataSource;

/**
 * PooledDataSourceFactory.
 *
 * @author Carl, 2023-10-08 11:25
 */
public class PooledDataSourceFactory extends UnpoolDataSourceFactory {

    @Override
    public DataSource getDataSource() {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver(properties.getProperty("driver"));
        pooledDataSource.setUrl(properties.getProperty("url"));
        pooledDataSource.setUsername(properties.getProperty("username"));
        pooledDataSource.setPassword(properties.getProperty("password"));
        return pooledDataSource;
    }
}
