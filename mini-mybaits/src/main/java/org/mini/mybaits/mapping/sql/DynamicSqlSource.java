/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.mapping.sql;


import org.mini.mybaits.builder.SqlSourceBuilder;
import org.mini.mybaits.mapping.BoundSql;
import org.mini.mybaits.mapping.SqlSource;
import org.mini.mybaits.scripting.xmltags.DynamicContext;
import org.mini.mybaits.scripting.xmltags.SqlNode;
import org.mini.mybaits.session.Configuration;

import java.util.HashMap;

/**
 * DynamicSqlSource.
 * 
 * @version CrisisGo v1.0
 * @author Carl, 2023-11-06 16:12
 */
public class DynamicSqlSource implements SqlSource {

    private final SqlSource sqlSource;

    public DynamicSqlSource(Configuration configuration, SqlNode rootSqlNode, Class<?> parameterType) {
        this(configuration, getSql(configuration, rootSqlNode), parameterType);
    }

    public DynamicSqlSource(Configuration configuration, String sql, Class<?> parameterType) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> clazz = parameterType == null ? Object.class : parameterType;
        sqlSource = sqlSourceParser.parse(sql, clazz, new HashMap<>());
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return null;
    }

    private static String getSql(Configuration configuration, SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(configuration, null);
        rootSqlNode.apply(context);
        return context.getSql();
    }

}
