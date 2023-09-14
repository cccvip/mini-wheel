/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session;


import org.mini.mybaits.mapping.MappingStatement;
import org.mini.mybaits.registry.MapperRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Configuration.
 *
 * @author Carl, 2023-09-14 9:52
 */
public class Configuration {
    //注册表
    private MapperRegistry mapperRegistry = new MapperRegistry();
    //XML SQL解析
    private Map<String, MappingStatement> mappingStatement = new HashMap<>();

    public void addMappedStatement(MappingStatement mappedStatement) {
        mappingStatement.put(mappedStatement.getId(), mappedStatement);
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
