/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.mapping;


import java.util.Map;

/**
 * MappingStatement.
 *
 * @author Carl, 2023-09-14 9:53
 */
public class MappingStatement {

    //sql语句定义的id
    private String id;
    //参数类型
    private String parameterType;
    //返回类型
    private String resultType;
    //SQL语句
    private String sql;
    //参数
    private Map<Integer, String> parameter;
    //执行SQL类型
    private SqlCommandType sqlCommandType;

    public MappingStatement(String id, String parameterType, String resultType, String sql,
                            Map<Integer, String> parameter, SqlCommandType sqlCommandType) {
        this.id = id;
        this.parameterType = parameterType;
        this.resultType = resultType;
        this.sql = sql;
        this.parameter = parameter;
        this.sqlCommandType = sqlCommandType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<Integer, String> getParameter() {
        return parameter;
    }

    public void setParameter(Map<Integer, String> parameter) {
        this.parameter = parameter;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }
}
