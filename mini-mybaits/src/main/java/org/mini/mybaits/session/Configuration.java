/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session;


import org.mini.mybaits.alias.TypeAliasRegistry;
import org.mini.mybaits.datasource.druid.DruidDataSourceFactory;
import org.mini.mybaits.datasource.pooled.PooledDataSourceFactory;
import org.mini.mybaits.datasource.unpooled.UnpoolDataSourceFactory;
import org.mini.mybaits.mapping.Environment;
import org.mini.mybaits.mapping.MappingStatement;
import org.mini.mybaits.registry.MapperRegistry;
import org.mini.mybaits.transaction.jdbc.JdbcTransactionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration.
 *
 * @author Carl, 2023-09-14 9:52
 */
public class Configuration {

    //环境因素
    private Environment environment;

    //注册表
    private MapperRegistry mapperRegistry = new MapperRegistry();
    //XML SQL解析
    private Map<String, MappingStatement> mappingStatement = new HashMap<>();

    private TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();

    //数据源
    public Configuration() {
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("DRUID", DruidDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpoolDataSourceFactory.class);
    }

    public void addMappedStatement(MappingStatement mappedStatement) {
        mappingStatement.put(mappedStatement.getId(), mappedStatement);
    }


    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public void setTypeAliasRegistry(TypeAliasRegistry typeAliasRegistry) {
        this.typeAliasRegistry = typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public <T> void addMapper(Class<T> classForName) {
        mapperRegistry.addMapper(classForName);
    }

    public <T> T getMapper(Class<T> t, SqlSession sqlSession) {
        return mapperRegistry.getMapper(t, sqlSession);
    }

    public MappingStatement getMappedStatement(String id) {
        return mappingStatement.get(id);
    }

}
