/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session.defaults;


import org.mini.mybaits.executor.Executor;
import org.mini.mybaits.mapping.Environment;
import org.mini.mybaits.mapping.MappingStatement;
import org.mini.mybaits.session.Configuration;
import org.mini.mybaits.session.SqlSession;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * DefaultSqlSession.
 *
 * @author Carl, 2023-08-30 14:45
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
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
    public <T> T selectOne(String id, Object parameter) {

        MappingStatement ms = configuration.getMappedStatement(id);

        List<T> list = executor.query(ms, parameter, null, ms.getSqlSource().getBoundSql(parameter));

        return list.get(0);
    }


}
