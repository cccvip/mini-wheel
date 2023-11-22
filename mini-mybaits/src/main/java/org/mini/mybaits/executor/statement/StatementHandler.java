/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.executor.statement;


import org.mini.mybaits.session.ResultSessionHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * StatementHandler.
 * 
 * @author Carl, 2023-10-31 13:49
 */
public interface StatementHandler {
    /** 准备语句 */
    Statement prepare(Connection connection) throws SQLException;

    /** 参数化 */
    void parameterize(Statement statement) throws SQLException;

    /** 执行查询 */
    <E> List<E> query(Statement statement, ResultSessionHandler resultHandler) throws SQLException;
}
