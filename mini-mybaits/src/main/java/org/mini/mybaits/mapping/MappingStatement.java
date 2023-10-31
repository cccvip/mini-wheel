/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.mapping;


import org.mini.mybaits.session.Configuration;

/**
 * MappingStatement.
 *
 * @author Carl, 2023-09-14 9:53
 */
public class MappingStatement {

    //sql语句定义的id
    private String id;
    //SQL操作类型
    private SqlCommandType sqlCommandType;
    //绑定SQL
    private BoundSql boundSql;
    private Configuration configuration;

    MappingStatement() {

    }

    //使用构造器模式,初始化实体类
    public static class Builder {

        private MappingStatement mappingStatement = new MappingStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, BoundSql boundSql) {
            mappingStatement.configuration = configuration;
            mappingStatement.id = id;
            mappingStatement.sqlCommandType = sqlCommandType;
            mappingStatement.boundSql = boundSql;
        }

        public MappingStatement build() {
            return mappingStatement;
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }

    public BoundSql getBoundSql() {
        return boundSql;
    }

    public void setBoundSql(BoundSql boundSql) {
        this.boundSql = boundSql;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
