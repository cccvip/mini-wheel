/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.test;


import com.alibaba.fastjson2.JSON;
import org.junit.Test;
import org.mini.mybaits.dao.IUserDao;
import org.mini.mybaits.datasource.pooled.PooledDataSource;
import org.mini.mybaits.datasource.unpooled.UnpoolDataSource;
import org.mini.mybaits.io.Resources;
import org.mini.mybaits.po.User;
import org.mini.mybaits.session.SqlSession;
import org.mini.mybaits.session.SqlSessionFactory;
import org.mini.mybaits.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

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

        for (int i = 0; i < 50; i++) {
            User res = userDao.queryUserName(1);
            System.out.println(res.getName());
        }
    }


    @Test
    public void test_pooled() throws SQLException, InterruptedException {
        PooledDataSource pooledDataSource = new PooledDataSource();
        pooledDataSource.setDriver("com.mysql.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/leaf?useUnicode=true");
        pooledDataSource.setUsername("cc");
        pooledDataSource.setPassword("cd123456");
        // 持续获得链接
        int i=0;
        while (i<20){
            Connection connection = pooledDataSource.getConnection();
            System.out.println(connection);
            Thread.sleep(1000);
            connection.close();
            i++;
        }
    }

    @Test
    public void test_unPooled() throws SQLException {
        UnpoolDataSource pooledDataSource = new UnpoolDataSource();
        pooledDataSource.setDriver("com.mysql.jdbc.Driver");
        pooledDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/leaf?useUnicode=true");
        pooledDataSource.setUsername("cc");
        pooledDataSource.setPassword("cd123456");
        // 持续获得链接
        int i=0;
        while (i<20){
            Connection connection = pooledDataSource.getConnection();
            System.out.println(connection);
            connection.close();
            i++;
        }
    }




}
