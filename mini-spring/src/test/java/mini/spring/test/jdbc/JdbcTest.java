/*
 * All Rights Reserved.
 *
 */
package mini.spring.test.jdbc;


import com.zaxxer.hikari.HikariConfig;
import mini.spring.jdbc.JdbcTemplate;
import mini.spring.jdbc.StringRowMapper;
import org.junit.Before;
import org.junit.Test;

/**
 * JdbcTest.
 *
 * @author Carl, 2023-08-23 14:08
 */
public class JdbcTest {
    private HikariConfig dataSourceConfig;

    @Before
    public void dataSource() {
        dataSourceConfig = new HikariConfig();
        dataSourceConfig.setJdbcUrl("jdbc:mysql://192.168.100.100:3306/leaf?characterEncoding=utf8&serverTimezone=GMT");
        dataSourceConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("cc");
        dataSourceConfig.setPassword("cd123456");
        dataSourceConfig.setMaximumPoolSize(5);
        dataSourceConfig.setMinimumIdle(1);
    }

    @Test
    public void testJdbc() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceConfig);
        try {
            String querySql = "select name from student where id=?";
            String name = jdbcTemplate.executeQuery(querySql, new StringRowMapper(), 1);
            System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJdbcWithTx() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceConfig);
        try {
            String querySql = "update student set name='cc2' where id=1";
            jdbcTemplate.executeUpdateWithTx(querySql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJdbcWithTxError() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSourceConfig);
        try {
            String querySql = "update student set name='cc2' where id=1";
            int i=1/0;
            jdbcTemplate.executeUpdateWithTx(querySql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
