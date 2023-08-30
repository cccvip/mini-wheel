/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.test;


import org.junit.Test;
import org.mini.mybaits.dao.IUserDao;
import org.mini.mybaits.factory.MapperProxyFactory;
import org.mini.mybaits.factory.SqlSessionFactory;
import org.mini.mybaits.registry.MapperRegistry;
import org.mini.mybaits.session.DefaultSqlSessionFactory;
import org.mini.mybaits.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * ProxyTest.
 *
 * @author Carl, 2023-08-30 10:32
 */
public class ProxyTest {

    @Test
    public void proxyTest() {
        //注册中心
        MapperRegistry mapperRegistry = new MapperRegistry();
        mapperRegistry.addMappers("org.mini.mybaits.dao");
        //SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //代理接口
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        userDao.queryUserName();
    }

}
