/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.transaction;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * Transactional. 事务
 *
 * @author Carl, 2023-09-14 13:17
 */
public interface Transaction {

    Connection getConnection() throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

    void close() throws SQLException;

}
