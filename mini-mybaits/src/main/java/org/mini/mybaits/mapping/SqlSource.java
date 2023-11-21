/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.mapping;


/**
 * SqlSource.
 * SQL解析
 * @author Carl, 2023-11-06 15:55
 */
public interface SqlSource {
    BoundSql getBoundSql(Object parameterObject);
}
