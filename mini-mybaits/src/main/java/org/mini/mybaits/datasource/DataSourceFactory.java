/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.datasource;


import javax.sql.DataSource;
import java.util.Properties;

/**
 * DataSourceFactory.
 * 
 * @author Carl, 2023-09-14 14:06
 */
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();

}
