/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc;


import com.sun.istack.internal.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * RowMapper.
 * 映射实体类
 *
 * @author Carl, 2023-08-23 11:13
 */
@FunctionalInterface
public interface RowMapper<T> {

    @Nullable
    T mapRow(ResultSet rs, int rowNum) throws SQLException;

}
