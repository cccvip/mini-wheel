/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.executor.result;


import org.mini.mybaits.mapping.BoundSql;
import org.mini.mybaits.mapping.MappingStatement;

import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * DefaultResultSetHandler.
 *
 * @author Carl, 2023-10-31 14:01
 */
public class DefaultResultSetHandler implements ResultSetHandler {
    private final BoundSql boundSql;
    private final MappingStatement mappingStatement;

    public DefaultResultSetHandler(BoundSql boundSql, MappingStatement mappedStatement) {
        this.boundSql = boundSql;
        this.mappingStatement = mappedStatement;
    }

    @Override
    public <E> List<E> handleResultSets(Statement stmt) throws SQLException {
        ResultSet resultSet = stmt.getResultSet();
        try {
            return resultSet2Obj(resultSet, mappingStatement.getResultType());
        } catch (Exception e) {
            throw new RuntimeException("query result is error");
        }
    }

    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 每次遍历行值
            while (resultSet.next()) {
                T obj = (T) clazz.newInstance();
                for (int i = 1; i <= columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    String columnName = metaData.getColumnName(i);
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method method = null;
                    if (value instanceof Timestamp) {
                        method = clazz.getMethod(setMethod, Date.class);
                    } else if (value instanceof String) {
                        obj = (T) value;
                    } else {
                        method = clazz.getMethod(setMethod, value.getClass());
                    }
                    if (null != method) {
                        method.invoke(obj, value);
                    }
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
