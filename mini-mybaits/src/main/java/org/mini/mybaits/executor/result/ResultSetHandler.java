/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.executor.result;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * ResultHandler.
 *
 * @author Carl, 2023-10-31 13:55
 */
public interface ResultSetHandler {

    <E> List<E> handleResultSets(Statement stmt) throws SQLException;

}
