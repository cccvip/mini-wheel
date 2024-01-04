/*
 * All Rights Reserved.
 *
 */
package design.prototype.datasource;


/**
 * DataSourceConnection.
 *
 * @author Carl, 2024-01-04 15:37
 */
public interface DataSourceConnection extends Cloneable {

    void connect();

    DataSourceConnection clone() throws CloneNotSupportedException;
}
