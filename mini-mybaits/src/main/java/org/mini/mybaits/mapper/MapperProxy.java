/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.mapper;


import org.mini.mybaits.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * MapperProxy.
 *
 * @author Carl, 2023-08-30 10:14
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private final Class<T> mapperInterface;
    private final SqlSession sqlSession;

    public MapperProxy(Class mapperInterface, SqlSession sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("你被代理了！");
        return "";
    }

}
