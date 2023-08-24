/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx;


import java.sql.Connection;

/**
 * TransactionStatus.
 * 事务状态
 *
 * @author Carl, 2023-08-24 9:26
 */
public class TransactionStatus {
    final Connection connection;

    public TransactionStatus(Connection connection) {
        this.connection = connection;
    }
}
