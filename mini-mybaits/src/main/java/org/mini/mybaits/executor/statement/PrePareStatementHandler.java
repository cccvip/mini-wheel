/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.executor.statement;


import org.mini.mybaits.executor.Executor;
import org.mini.mybaits.mapping.BoundSql;
import org.mini.mybaits.mapping.MappingStatement;
import sun.plugin2.main.server.ResultHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * PrePareStatementHandler.
 *
 * @author Carl, 2023-10-31 14:28
 */
public class PrePareStatementHandler extends BaseStatementHandler {

    public PrePareStatementHandler(Executor executor, MappingStatement mappingStatement, Object parameterObject, BoundSql boundSql) {
        super(executor, mappingStatement, parameterObject, boundSql);
    }

    @Override
    protected Statement instantiateStatement(Connection connection) throws SQLException {
        String sql = boundSql.getSql();
        return connection.prepareStatement(sql);
    }

    @Override
    public void parameterize(Statement statement) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.setLong(1, Long.parseLong(((Object[]) parameterObject)[0].toString()));
    }

    @Override
    public <E> List<E> query(Statement statement, ResultHandler resultHandler) throws SQLException {
        PreparedStatement ps = (PreparedStatement) statement;
        ps.execute();
        return resultSetHandler.handleResultSets(ps);
    }

}
