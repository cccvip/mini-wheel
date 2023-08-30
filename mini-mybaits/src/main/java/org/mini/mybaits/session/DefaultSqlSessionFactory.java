/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session;


import org.mini.mybaits.factory.SqlSessionFactory;
import org.mini.mybaits.registry.MapperRegistry;

/**
 * DefaultSqlSessionFactory.
 * 
 * @author Carl, 2023-08-30 15:08
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }

}
