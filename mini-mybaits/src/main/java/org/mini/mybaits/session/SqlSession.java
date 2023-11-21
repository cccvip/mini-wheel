/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session;


/**
 * SqlSession.
 *
 * @author Carl, 2023-08-30 13:41
 */
public interface SqlSession {

    <T> T getMapper(Class<T> t);

    Configuration configuration();

    <T> T selectOne(String name, Object param);
}
