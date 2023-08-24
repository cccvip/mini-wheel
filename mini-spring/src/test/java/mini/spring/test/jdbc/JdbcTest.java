/*
 * All Rights Reserved.
 *
 */
package mini.spring.test.jdbc;


import mini.spring.jdbc.DataSourceConfig;
import mini.spring.jdbc.JdbcTemplate;
import mini.spring.jdbc.StringRowMapper;
import org.junit.Test;

/**
 * JdbcTest.
 *
 * @author Carl, 2023-08-23 14:08
 */

public class JdbcTest {

    //0
    //1
    //Paul
    @Test
    public void testJdbc() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:sqlite:test.db");
        dataSourceConfig.setDriverClassName("org.sqlite.JDBC");
        dataSourceConfig.setMaxPoolSize(5);
        dataSourceConfig.setMinPoolSize(1);
        dataSourceConfig.setTimeout(3000L);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceConfig);
        String sql = "CREATE TABLE COMPANY " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " AGE            INT     NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)";
        try {
            jdbcTemplate.executeUpdate(sql);

            String insertSql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                    "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
            jdbcTemplate.executeUpdate(insertSql);

            String querySql = "select NAME from COMPANY where ID=1";
            String name = jdbcTemplate.execute(querySql, new StringRowMapper(), new Object[]{});
            System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
