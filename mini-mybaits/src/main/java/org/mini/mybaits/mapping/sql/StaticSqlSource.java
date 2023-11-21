/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.mapping.sql;


import org.mini.mybaits.mapping.BoundSql;
import org.mini.mybaits.mapping.ParameterMapping;
import org.mini.mybaits.mapping.SqlSource;
import org.mini.mybaits.session.Configuration;

import java.util.List;

/**
 * StaticSqlSource.
 * 静态SQL
 * @author Carl, 2023-11-06 15:56
 */
public class StaticSqlSource implements SqlSource {

    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Configuration configuration;

    public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings, Configuration configuration) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.configuration = configuration;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(configuration, sql, parameterMappings, parameterObject);
    }
}
