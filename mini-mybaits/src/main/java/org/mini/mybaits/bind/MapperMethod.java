/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.bind;


import org.mini.mybaits.mapping.MappingStatement;
import org.mini.mybaits.mapping.SqlCommandType;
import org.mini.mybaits.session.Configuration;
import org.mini.mybaits.session.SqlSession;

import java.lang.reflect.Method;

/**
 * MapperMethod.
 * 
 * @version CrisisGo v1.0
 * @author Carl, 2023-09-14 11:09
 */
public class MapperMethod {

    private SqlCommand command;

    public MapperMethod(Class<?> mapperInterface, Method method, Configuration configuration) {
        this.command = new SqlCommand(configuration, mapperInterface, method);
    }

    public Object execute(SqlSession sqlSession, Object[] args) {
        SqlCommandType commandType = command.type;
        Object result = null;
        switch (commandType) {
            case INSERT:
                break;
            case DELETE:
                break;
            case UPDATE:
                break;
            case SELECT:
                result = sqlSession.selectOne(command.getName(), args);
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command.getName());
        }
        return result;
    }

    public static class SqlCommand {

        private final String name;
        private final SqlCommandType type;

        public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
            String statementName = mapperInterface.getName() + "." + method.getName();
            MappingStatement ms = configuration.getMappedStatement(statementName);
            name = ms.getId();
            type = ms.getSqlCommandType();
        }

        public String getName() {
            return name;
        }

        public SqlCommandType getType() {
            return type;
        }
    }

}
