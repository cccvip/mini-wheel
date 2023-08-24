/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * StringRowMapper.
 * 
 * @author Carl, 2023-08-23 11:16
 */
public class StringRowMapper implements RowMapper<String> {

    @Override
    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString(1);
    }
}
