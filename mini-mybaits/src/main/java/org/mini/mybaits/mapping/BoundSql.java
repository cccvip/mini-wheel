/*
 * All Rights Reserved.
 */
package org.mini.mybaits.mapping;


import java.util.Map;

/**
 * BoundSql.
 * 
 * @author Carl, 2023-10-31 13:27
 */
public class BoundSql {

    private String sql;
    private Map<Integer, String> parameterMappings;
    private String parameterType;
    private String resultType;

    public BoundSql(String sql, Map<Integer, String> parameterMappings, String parameterType, String resultType) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.parameterType = parameterType;
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<Integer, String> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(Map<Integer, String> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
