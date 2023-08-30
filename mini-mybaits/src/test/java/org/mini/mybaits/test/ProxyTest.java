/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.test;


import org.junit.Test;
import org.mini.mybaits.dao.IUserDao;
import org.mini.mybaits.factory.MapperProxyFactory;

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
        Map<String,String> sqlSession =new HashMap<>();
        //MapperProxy
        MapperProxyFactory<IUserDao> mapperProxyFactory = new MapperProxyFactory<>(IUserDao.class);
        IUserDao iUserDao = mapperProxyFactory.newInstance(sqlSession);
        iUserDao.queryUserName();
    }

}
