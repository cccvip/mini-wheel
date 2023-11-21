/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.type;


import org.mini.mybaits.alias.JdbcType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * StringTypeHandler.
 *
 * @author Carl, 2023-11-21 14:07
 */
public class StringTypeHandler extends BaseTypeHandler<String> {
    @Override
    protected void setGenericParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter);
    }
}
