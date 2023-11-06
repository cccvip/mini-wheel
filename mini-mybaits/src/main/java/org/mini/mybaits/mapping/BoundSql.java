/*
 * All Rights Reserved.
 */
package org.mini.mybaits.mapping;


import org.mini.mybaits.reflection.MetaObject;
import org.mini.mybaits.session.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BoundSql.
 * 
 * @author Carl, 2023-10-31 13:27
 */
public class BoundSql {

    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Object parameterObject;
    private Map<String, Object> additionalParameters;
    private MetaObject metaParameters;

    public BoundSql(Configuration configuration, String sql, List<ParameterMapping> parameterMappings, Object parameterObject) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.parameterObject = parameterObject;
        this.additionalParameters = new HashMap<>();
        this.metaParameters = configuration.newMetaObject(additionalParameters);
    }




}
