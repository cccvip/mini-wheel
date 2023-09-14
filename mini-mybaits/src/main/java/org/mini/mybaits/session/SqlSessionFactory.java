package org.mini.mybaits.session;

/**
 * DefaultSqlSessionFactory.
 *
 * @author Carl, 2023-08-30 15:08
 */
public interface SqlSessionFactory {

   SqlSession openSession();

}
