/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.type;


import org.mini.mybaits.alias.JdbcType;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * TypeHandler.
 * 类型解析器
 * @author Carl, 2023-11-21 14:04
 */
public interface TypeHandler<T> {
    void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;
}
