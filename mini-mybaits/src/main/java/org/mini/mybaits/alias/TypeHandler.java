/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.alias;


import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * TypeHandler.
 * 
 * @version CrisisGo v1.0
 * @author Carl, 2023-11-06 16:17
 */
public interface TypeHandler<T> {
    /**
     * 设置参数
     */
    void setParameter(PreparedStatement ps, int i, T parameter, JdbcType jdbcType) throws SQLException;
}
