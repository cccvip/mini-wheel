/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.bind;


import org.mini.mybaits.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * MapperProxy.
 *
 * @author Carl, 2023-08-30 10:14
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final Class<T> mapperInterface;
    private final SqlSession sqlSession;
    private Map<Method, MapperMethod> methodCache;

    public MapperProxy(Class<T> mapperInterface, SqlSession sqlSession, Map<Method, MapperMethod> methodCache) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
        this.methodCache = methodCache;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            final MapperMethod mapperMethod = cachedMapperMethod(method);
            return mapperMethod.execute(sqlSession, args);
        }
    }

    /**
     * 去缓存中找MapperMethod
     */
    private MapperMethod cachedMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null) {
            //找不到才去new
            mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.configuration());
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }

}
