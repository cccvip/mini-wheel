/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session.defaults;


import org.mini.mybaits.session.Configuration;
import org.mini.mybaits.session.SqlSession;

/**
 * DefaultSqlSession.
 *
 * @author Carl, 2023-08-30 14:45
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Configuration configuration() {
        return configuration;
    }

    @Override
    public <T> T getMapper(Class<T> t) {
        return configuration.getMapper(t, this);
    }

    @Override
    public <T> T selectOne(String name, Object[] args) {
        return (T) ("你被代理了！" + name);
    }
}
