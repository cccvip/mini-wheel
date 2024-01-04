/*
 * All Rights Reserved.
 *
 */
package design.prototype.impl;


import design.prototype.datasource.DataSourceConnection;

/**
 * MySqlDataSourceConnection.
 *
 * @author Carl, 2024-01-04 15:39
 */
public class MySqlDataSourceConnection implements DataSourceConnection {

    private String url;
    private String username;
    private String password;

    public MySqlDataSourceConnection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void connect() {
        System.out.println("Connecting to MySQL database at " + url + " with username " + username);
    }

    @Override
    public DataSourceConnection clone() throws CloneNotSupportedException {
        return (MySqlDataSourceConnection) super.clone();
    }
}
