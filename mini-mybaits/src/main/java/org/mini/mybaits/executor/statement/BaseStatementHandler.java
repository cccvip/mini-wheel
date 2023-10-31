/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.executor.statement;


import org.mini.mybaits.executor.Executor;
import org.mini.mybaits.executor.result.ResultSetHandler;
import org.mini.mybaits.mapping.BoundSql;
import org.mini.mybaits.mapping.MappingStatement;
import org.mini.mybaits.session.Configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * BaseStatementHandler.
 *
 * @author Carl, 2023-10-31 13:51
 */
public abstract class BaseStatementHandler implements StatementHandler {

    protected Configuration configuration;

    protected Executor executor;

    protected MappingStatement mappingStatement;

    protected BoundSql boundSql;

    protected final Object parameterObject;

    protected final ResultSetHandler resultSetHandler;

    public BaseStatementHandler(Executor executor, MappingStatement mappingStatement, Object parameterObject, BoundSql boundSql) {
        this.configuration = mappingStatement.getConfiguration();
        this.executor = executor;
        this.mappingStatement = mappingStatement;
        this.boundSql = boundSql;

        this.parameterObject = parameterObject;
        this.resultSetHandler = configuration.newResultSetHandler(executor, mappingStatement, boundSql);
    }

    /**
     * prepare.
     * jdbc替换参数
     *
     * @param connection
     * @return java.sql.Statement
     */
    @Override
    public Statement prepare(Connection connection) throws SQLException {
        Statement statement = null;
        try {
            statement = instantiateStatement(connection);
            statement.setQueryTimeout(350);
            statement.setFetchSize(10000);
            return statement;
        } catch (Exception e) {
            throw new RuntimeException("Error preparing statement.  Cause: " + e, e);
        }
    }

    protected abstract Statement instantiateStatement(Connection connection) throws SQLException;

}
