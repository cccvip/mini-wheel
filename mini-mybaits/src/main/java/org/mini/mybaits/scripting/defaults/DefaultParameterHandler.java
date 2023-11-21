/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.scripting.defaults;


import cn.hutool.core.collection.CollectionUtil;
import org.mini.mybaits.alias.JdbcType;
import org.mini.mybaits.alias.TypeHandlerRegistry;
import org.mini.mybaits.executor.paramter.ParameterHandler;
import org.mini.mybaits.mapping.BoundSql;
import org.mini.mybaits.mapping.MappingStatement;
import org.mini.mybaits.mapping.ParameterMapping;
import org.mini.mybaits.reflection.MetaObject;
import org.mini.mybaits.session.Configuration;
import org.mini.mybaits.type.TypeHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * DefaultParameterHandler.
 *
 * @author Carl, 2023-11-21 13:45
 */
public class DefaultParameterHandler implements ParameterHandler {

    private final MappingStatement mappedStatement;
    private final Object parameterObject;
    private final BoundSql boundSql;
    private final Configuration configuration;
    private final TypeHandlerRegistry typeHandlerRegistry;

    public DefaultParameterHandler(MappingStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        this.mappedStatement = mappedStatement;
        this.parameterObject = parameterObject;
        this.boundSql = boundSql;
        this.configuration = mappedStatement.getConfiguration();
        //typeHandleRegistry
        this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
    }

    @Override
    public Object getParameterObject() {
        return parameterObject;
    }

    @Override
    public void setParameters(PreparedStatement ps) throws SQLException {
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (CollectionUtil.isEmpty(parameterMappings)) {
            return;
        }
        for (int i = 0; i < parameterMappings.size(); i++) {
            ParameterMapping parameterMapping = parameterMappings.get(i);
            String propertyName = parameterMapping.getProperty();
            Object value;
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                value = parameterObject;
            } else {
                // 通过 MetaObject.getValue 反射取得值设进去
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                value = metaObject.getValue(propertyName);
            }
            JdbcType jdbcType = parameterMapping.getJdbcType();
            //设置参数类型
            TypeHandler typeHandler = parameterMapping.getTypeHandler();
            typeHandler.setParameter(ps, i + 1, value, jdbcType);
        }
    }
}
