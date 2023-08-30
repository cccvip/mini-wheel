/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session;


import java.sql.Connection;

/**
 * SqlSession.
 *
 * @author Carl, 2023-08-30 13:41
 */
public interface SqlSession {

    <T> T getMapper(Class<T> t);

}
