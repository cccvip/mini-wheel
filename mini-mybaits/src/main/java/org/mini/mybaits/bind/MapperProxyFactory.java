/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.bind;


import org.mini.mybaits.session.SqlSession;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MapperProxyFactory.
 *
 * @author Carl, 2023-08-30 10:15
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    private Map<Method, MapperMethod> methodCache = new ConcurrentHashMap<>();

    public T newInstance(SqlSession sqlSession) {
        MapperProxy<T> proxy = new MapperProxy<>(mapperInterface, sqlSession, methodCache);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),
                new Class[]{mapperInterface},
                proxy);
    }

}
