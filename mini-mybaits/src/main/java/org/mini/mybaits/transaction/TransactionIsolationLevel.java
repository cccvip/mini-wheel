/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.transaction;


import java.sql.Connection;

/**
 * TransactionIsolationLevel.
 * 事务隔离级别
 *
 * @author Carl, 2023-09-14 13:20
 */
public enum TransactionIsolationLevel {

    NONE(Connection.TRANSACTION_NONE),
    //读已提交
    READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
    //读未提交
    READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),
    //可重复读
    REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
    //序列化
    SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);

    private final int level;

    TransactionIsolationLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
