/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.factory;


import org.mini.mybaits.mapper.MapperProxy;

import java.lang.reflect.Proxy;
import java.util.Map;

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

    public T newInstance(Map<String, String> sqlSession) {
        MapperProxy<T> proxy = new MapperProxy<>(mapperInterface, sqlSession);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),
                new Class[]{mapperInterface},
                proxy);
    }

}
