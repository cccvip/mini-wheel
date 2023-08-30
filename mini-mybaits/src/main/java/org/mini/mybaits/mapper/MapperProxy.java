/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.mapper;


import com.alibaba.fastjson2.JSON;

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
    private final Map<String, String> sqlSession;

    public MapperProxy(Class mapperInterface, Map<String, String> sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("你被代理了！");
        return "";
    }

}
