/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.scripting.defaults;


import org.mini.mybaits.builder.SqlSourceBuilder;
import org.mini.mybaits.mapping.BoundSql;
import org.mini.mybaits.mapping.SqlSource;
import org.mini.mybaits.scripting.xmltags.DynamicContext;
import org.mini.mybaits.scripting.xmltags.SqlNode;
import org.mini.mybaits.session.Configuration;

import java.util.HashMap;

/**
 * RawSqlSource.
 * 
 * @author Carl, 2023-11-21 11:28
 */
public class RawSqlSource implements SqlSource {
    private final SqlSource sqlSource;

    public RawSqlSource(Configuration configuration, SqlNode rootSqlNode, Class<?> parameterType) {
        this(configuration, getSql(configuration, rootSqlNode), parameterType);
    }

    public RawSqlSource(Configuration configuration, String sql, Class<?> parameterType) {
        SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(configuration);
        Class<?> clazz = parameterType == null ? Object.class : parameterType;
        sqlSource = sqlSourceParser.parse(sql, clazz, new HashMap<>());
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return sqlSource.getBoundSql(parameterObject);
    }

    private static String getSql(Configuration configuration, SqlNode rootSqlNode) {
        DynamicContext context = new DynamicContext(configuration, null);
        rootSqlNode.apply(context);
        return context.getSql();
    }


}
