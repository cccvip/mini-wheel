/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.registry;


import cn.hutool.core.lang.ClassScanner;
import org.mini.mybaits.bind.MapperProxyFactory;
import org.mini.mybaits.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * MapperRegistry.
 * Mapper接口注册表
 *
 * @author Carl, 2023-08-30 14:47
 */
public class MapperRegistry {

    private Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>)knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not known to the MapperRegistry.");
        }
        try {
            return mapperProxyFactory.newInstance(sqlSession);
        } catch (Exception e) {
            throw new RuntimeException("Error getting mapper instance. Cause: " + e, e);
        }
    }

    public <T> void addMapper(Class<T> type) {
        /* Mapper 必须是接口才会注册 */
        if (type.isInterface()) {
            if (hasMapper(type)) {
                // 如果重复添加了，报错
                throw new RuntimeException("Type " + type + " is already known to the MapperRegistry.");
            }
            // 注册映射器代理工厂
            knownMappers.put(type, new MapperProxyFactory<>(type));
        }
    }

    public void addMappers(String packageName) {
        Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
        for (Class<?> mapperClass : mapperSet) {
            addMapper(mapperClass);
        }
    }

    private boolean hasMapper(Class type) {
        return knownMappers.containsKey(type);
    }

}
