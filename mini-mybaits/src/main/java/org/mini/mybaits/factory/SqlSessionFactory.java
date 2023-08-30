/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.factory;


import org.mini.mybaits.session.SqlSession;

/**
 * SqlSessionFactory.
 *
 * @author Carl, 2023-08-30 14:57
 * @version CrisisGo v1.0
 */
public interface SqlSessionFactory {

    SqlSession openSession();

}
