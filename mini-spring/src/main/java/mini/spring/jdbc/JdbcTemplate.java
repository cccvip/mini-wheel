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

    public JdbcTemplate(DataSourceConfig dataSourceConfig) {
        HikariConfig config = new HikariConfig();
        config.setAutoCommit(false);
        config.setDriverClassName(dataSourceConfig.getDriverClassName());
        config.setJdbcUrl(dataSourceConfig.getUrl());
        config.setMaximumPoolSize(dataSourceConfig.getMaxPoolSize());
        config.setMinimumIdle(dataSourceConfig.getMinPoolSize());
        config.setConnectionTimeout(dataSourceConfig.getTimeout());
        this.dataSource = new HikariDataSource(config);
    }

    public <T> T execute(Connection conn, String sql, RowMapper<T> rowMapper, Object... args)  {
        try {
            PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator();
            PreparedStatement preparedStatement = preparedStatementCreator.createPreparedStatement(conn, sql, args);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            T result = rowMapper.mapRow(resultSet, 1);
            return result;
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }

    public <T> T execute(String sql, RowMapper<T> rowMapper, Object... args) throws Exception {
        Connection connection = TransactionalUtils.getCurrentConnection();
        if (connection == null) {
            connection = this.dataSource.getConnection();
        }
        return execute(connection, sql, rowMapper, args);
    }

    public void executeUpdate(String sql) throws Exception {
        try (Connection newConn = dataSource.getConnection()) {
            newConn.setAutoCommit(false);
            Statement statement = newConn.createStatement();
            int result = statement.executeUpdate(sql);
            System.out.println(result);
            newConn.commit();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }


}
