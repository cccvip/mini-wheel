/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.type;


import org.mini.mybaits.alias.JdbcType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * BaseTypeHandler.
 *
 * @author Carl, 2023-11-21 14:08
 */
public abstract class BaseTypeHandler<T> implements TypeHandler<T> {

    @Override
    public void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException {
        if (null == parameter) {
            return;
        }
        setGenericParameter(ps, i, parameter, jdbcType);
    }

    @Override
    public T getResult(ResultSet rs, String columnName) throws SQLException {
        return getGenericResult(rs,columnName);
    }

    protected abstract void setGenericParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;

    protected abstract T getGenericResult(ResultSet rs, String columnName) throws SQLException;
}
