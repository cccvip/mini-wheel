/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.datasource.unpooled;


import org.mini.mybaits.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * UnpooledDataSource.
 * 无池化
 *
 * @author Carl, 2023-09-14 14:52
 */
public class UnpoolDataSourceFactory implements DataSourceFactory {

    public Properties properties;

    @Override
    public void setProperties(Properties props) {
        this.properties = props;
    }

    @Override
    public DataSource getDataSource() {
        UnpoolDataSource unpoolDataSource = new UnpoolDataSource();
        unpoolDataSource.setDriver(properties.getProperty("driver"));
        unpoolDataSource.setUrl(properties.getProperty("url"));
        unpoolDataSource.setUsername(properties.getProperty("username"));
        unpoolDataSource.setPassword(properties.getProperty("password"));
        return unpoolDataSource;
    }
}
