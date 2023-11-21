/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.executor.paramter;


import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ParameterHandler.
 * 参数预处理
 * @author Carl, 2023-11-21 13:40
 */
public interface ParameterHandler {
    Object getParameterObject();

    void setParameters(PreparedStatement ps) throws SQLException;
}
