/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * PreparedStatementCreator.
 *
 * @author Carl, 2023-08-23 11:19
 */
public class PreparedStatementCreator {

    public PreparedStatement createPreparedStatement(Connection con, String sql, Object[] args) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        if (null != args) {
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
        }
        return preparedStatement;
    }

}
