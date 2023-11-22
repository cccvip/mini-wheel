/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.executor.statement;


import org.mini.mybaits.executor.Executor;
import org.mini.mybaits.mapping.BoundSql;
import org.mini.mybaits.mapping.MappingStatement;
import org.mini.mybaits.session.ResultSessionHandler;
import sun.plugin2.main.server.ResultHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * SimpleStatementHandler.
 * 简单Statement过滤器
 *
 * @author Carl, 2023-10-31 14:25
 */
public class SimpleStatementHandler extends BaseStatementHandler {

    public SimpleStatementHandler(Executor executor, MappingStatement mappingStatement, Object parameterObject,
                                  BoundSql boundSql, ResultSessionHandler resultSessionHandler) {
        super(executor, mappingStatement, parameterObject, boundSql, resultSessionHandler);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        return connection.createStatement();
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {

    }

    @Override
    public <E> List<E> query(Statement statement, ResultSessionHandler resultHandler) throws SQLException {
        String sql = boundSql.getSql();
        statement.execute(sql);
        return resultSetHandler.handleResultSets(statement);
    }
}
