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
 * LongTypeHandler.
 * @author Carl, 2023-11-21 14:06
 */
public class LongTypeHandler extends BaseTypeHandler<Long> {

    @Override
    protected void setGenericParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, parameter);
    }

    @Override
    protected Long getGenericResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getLong(columnName);
    }
}
