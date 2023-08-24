/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import mini.spring.jdbc.tx.TransactionalUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JdbcTemplate.
 *
 * @author Carl, 2023-08-23 11:12
 */
public class JdbcTemplate {

    private final DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcTemplate(HikariConfig config) {
        this.dataSource = new HikariDataSource(config);
    }

    public <T> T execute(Connection conn, String sql, RowMapper<T> rowMapper, Object... args) {
        try {
            PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator();
            PreparedStatement preparedStatement = preparedStatementCreator.createPreparedStatement(conn, sql, args);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            T result = rowMapper.mapRow(resultSet, 1);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public <T> T executeQuery(String sql, RowMapper<T> rowMapper, Object... args) throws Exception {
        return execute(this.dataSource.getConnection(), sql, rowMapper, args);
    }

    public void executeUpdateWithTx(String sql) throws Exception {
        Connection connection = this.dataSource.getConnection();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            connection.rollback();
        }
    }


}
