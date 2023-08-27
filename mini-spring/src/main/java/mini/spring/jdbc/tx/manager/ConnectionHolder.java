/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.manager;


import java.sql.Connection;

/**
 * ConnectionHolder.
 * 
 * @author Carl, 2023-08-24 17:01
 */
public class ConnectionHolder {

    private Connection currentConnection;

    public Connection getCurrentConnection() {
        return currentConnection;
    }

    public void setCurrentConnection(Connection currentConnection) {
        this.currentConnection = currentConnection;
    }


    public void clear() {
    }


}
