/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.mapping;


/**
 * SqlSource.
 * SQL解析
 * @version CrisisGo v1.0
 * @author Carl, 2023-11-06 15:55
 */
public interface SqlSource {
    BoundSql getBoundSql(Object parameterObject);
}
