/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.test;


import org.junit.Test;
import org.mini.mybaits.dao.IUserDao;
import org.mini.mybaits.io.Resources;
import org.mini.mybaits.po.User;
import org.mini.mybaits.session.SqlSession;
import org.mini.mybaits.session.SqlSessionFactory;
import org.mini.mybaits.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.Reader;

/**
 * ProxyTest.
 *
 * @author Carl, 2023-08-30 10:32
 */
public class ProxyTest {

    @Test
    public void test_SqlSessionFactory() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis-config-datasource.xml");
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        // 3. 测试验证
        User res = userDao.queryUserName(1);
        System.out.println(res.getName());
    }


}
