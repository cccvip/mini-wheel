/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.mapping;


import org.mini.mybaits.scripting.LanguageDriver;
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
    private Configuration configuration;
    private SqlSource sqlSource;
    Class<?> resultType;
    private LanguageDriver lang;

    MappingStatement() {}

    //使用构造器模式,初始化实体类
    public static class Builder {
        private MappingStatement mappingStatement = new MappingStatement();

        public Builder(Configuration configuration, String id, SqlCommandType sqlCommandType, SqlSource sqlSource, Class<?> resultType) {
            mappingStatement.configuration = configuration;
            mappingStatement.id = id;
            mappingStatement.sqlCommandType = sqlCommandType;
            mappingStatement.sqlSource = sqlSource;
            mappingStatement.resultType = resultType;
            mappingStatement.lang = configuration.getDefaultScriptingLanguageInstance();
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

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public void setSqlSource(SqlSource sqlSource) {
        this.sqlSource = sqlSource;
    }

    public Class<?> getResultType() {
        return resultType;
    }

    public void setResultType(Class<?> resultType) {
        this.resultType = resultType;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public LanguageDriver getLang() {
        return lang;
    }

    public void setLang(LanguageDriver lang) {
        this.lang = lang;
    }
}
