/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session;


import org.mini.mybaits.registry.MapperRegistry;

/**
 * DefaultSqlSession.
 *
 * @author Carl, 2023-08-30 14:45
 */
public class DefaultSqlSession implements SqlSession {

    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T getMapper(Class<T> t) {
        return mapperRegistry.getMapper(t, this);
    }
}
