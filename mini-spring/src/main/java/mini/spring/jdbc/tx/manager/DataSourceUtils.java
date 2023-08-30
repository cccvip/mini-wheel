package mini.spring.jdbc.tx.manager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @description:
 * @author：carl
 * @date: 2023/8/27
 */
public class DataSourceUtils {

    public static void doReleaseConnection(Connection con, DataSource dataSource) throws SQLException {
        if (con == null) {
            return;
        }
        doCloseConnection(con, dataSource);
    }

    public static void doCloseConnection(Connection con, DataSource dataSource) throws SQLException {
        con.close();

    }


}
