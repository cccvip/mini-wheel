/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.manager;


/**
 * DataSourceTransactionObject.
 *
 * @author Carl, 2023-08-24 17:00
 */
public class DataSourceTransactionObject {

    private ConnectionHolder connectionHolder;

    public void setConnectionHolder(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    public ConnectionHolder getConnectionHolder() {
        return connectionHolder;
    }


}
