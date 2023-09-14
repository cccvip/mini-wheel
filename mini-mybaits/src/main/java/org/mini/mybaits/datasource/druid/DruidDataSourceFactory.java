/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.datasource.druid;


import com.alibaba.druid.pool.DruidDataSource;
import org.mini.mybaits.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * DruidDataSource.
 *
 * @author Carl, 2023-09-14 14:07
 */
public class DruidDataSourceFactory implements DataSourceFactory {

    Properties props;

    @Override
    public void setProperties(Properties props) {
        this.props = props;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(props.getProperty("driver"));
        dataSource.setUrl(props.getProperty("url"));
        dataSource.setUsername(props.getProperty("username"));
        dataSource.setPassword(props.getProperty("password"));
        return dataSource;
    }
}
