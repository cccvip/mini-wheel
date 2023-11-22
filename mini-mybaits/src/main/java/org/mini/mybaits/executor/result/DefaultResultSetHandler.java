/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.executor.result;


import org.mini.mybaits.alias.TypeHandlerRegistry;
import org.mini.mybaits.mapping.BoundSql;
import org.mini.mybaits.mapping.MappingStatement;
import org.mini.mybaits.mapping.ResultMap;
import org.mini.mybaits.mapping.ResultMapping;
import org.mini.mybaits.reflection.MetaClass;
import org.mini.mybaits.reflection.MetaObject;
import org.mini.mybaits.reflection.factory.ObjectFactory;
import org.mini.mybaits.session.Configuration;
import org.mini.mybaits.session.ResultHandler;
import org.mini.mybaits.session.defaults.DefaultResultContext;
import org.mini.mybaits.session.defaults.DefaultResultHandler;
import org.mini.mybaits.type.TypeHandler;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * DefaultResultSetHandler.
 *
 * @author Carl, 2023-10-31 14:01
 */
public class DefaultResultSetHandler implements ResultSetHandler {
    private final BoundSql boundSql;
    private final MappingStatement mappingStatement;
    private final ResultHandler resultHandler;
    private final Configuration configuration;
    private final ObjectFactory objectFactory;
    private final TypeHandlerRegistry typeHandlerRegistry;

    public DefaultResultSetHandler(BoundSql boundSql, MappingStatement mappedStatement, ResultHandler resultHandler) {
        this.configuration = mappedStatement.getConfiguration();
        this.boundSql = boundSql;
        this.mappingStatement = mappedStatement;
        this.resultHandler = resultHandler;
        this.objectFactory = configuration.getObjectFactory();
        this.typeHandlerRegistry = configuration.getTypeHandlerRegistry();
    }

    @Override
    public <E> List<E> handleResultSets(Statement stmt) throws SQLException {
        List<Object> multipleResults = new ArrayList<>();
        ResultSetWrapper rsw = new ResultSetWrapper(stmt.getResultSet(), mappingStatement.getConfiguration());
        int resultSetCount = 0;
        List<ResultMap> resultMaps = mappingStatement.getResultMaps();
        while (rsw != null && resultMaps.size() > resultSetCount) {
            ResultMap resultMap = resultMaps.get(resultSetCount);

            handleResultSet(rsw, resultMap, multipleResults, null);
            rsw = getNextResultSet(stmt);
            resultSetCount++;
        }

        return null;
    }

    private ResultSetWrapper getNextResultSet(Statement stmt) {
        return null;
    }

    private void handleResultSet(ResultSetWrapper rsw, ResultMap resultMap, List<Object> multipleResults, ResultMapping parentMapping) throws SQLException {
        if (resultHandler == null) {
            // 1. 新创建结果处理器
            DefaultResultHandler defaultResultHandler = new DefaultResultHandler(objectFactory);
            // 2. 封装数据
            handleRowValuesForSimpleResultMap(rsw, resultMap, defaultResultHandler, null);
            // 3. 保存结果
            multipleResults.add(defaultResultHandler.getResultList());
        }
    }

    private void handleRowValuesForSimpleResultMap(ResultSetWrapper rsw, ResultMap resultMap, ResultHandler resultHandler, ResultMapping parentMapping) throws SQLException {
        DefaultResultContext resultContext = new DefaultResultContext();
        while (rsw.getResultSet().next()) {
            Object rowValue = getRowValue(rsw, resultMap);
            callResultHandler(resultHandler, resultContext, rowValue);
        }
    }

    private void callResultHandler(ResultHandler resultHandler, DefaultResultContext resultContext, Object rowValue) {
        resultContext.nextResultObject(rowValue);
        resultHandler.handleResult(resultContext);
    }

    /**
     * 获取一行的值
     */
    private Object getRowValue(ResultSetWrapper rsw, ResultMap resultMap) throws SQLException {
        // 根据返回类型，实例化对象
        Object resultObject = createResultObject(rsw, resultMap, null);
        if (resultObject != null && !typeHandlerRegistry.hasTypeHandler(resultMap.getType())) {
            final MetaObject metaObject = configuration.newMetaObject(resultObject);
            applyAutomaticMappings(rsw, resultMap, metaObject, null);
        }
        return resultObject;
    }

    private Object createResultObject(ResultSetWrapper rsw, ResultMap resultMap, String columnPrefix) throws SQLException {
        final List<Class<?>> constructorArgTypes = new ArrayList<>();
        final List<Object> constructorArgs = new ArrayList<>();
        return createResultObject(rsw, resultMap, constructorArgTypes, constructorArgs, columnPrefix);
    }

    /**
     * 创建结果
     */
    private Object createResultObject(ResultSetWrapper rsw, ResultMap resultMap, List<Class<?>> constructorArgTypes, List<Object> constructorArgs, String columnPrefix) throws SQLException {
        final Class<?> resultType = resultMap.getType();
        final MetaClass metaType = MetaClass.forClass(resultType);
        if (resultType.isInterface() || metaType.hasDefaultConstructor()) {
            // 普通的Bean对象类型
            return objectFactory.create(resultType);
        }
        throw new RuntimeException("Do not know how to create an instance of " + resultType);
    }

    private boolean applyAutomaticMappings(ResultSetWrapper rsw, ResultMap resultMap, MetaObject metaObject, String columnPrefix) throws SQLException{

        final List<String> unmappedColumnNames = rsw.getUnmappedColumnNames(resultMap, columnPrefix);

        boolean foundValues = false;

        for (String columnName : unmappedColumnNames) {
            String propertyName = columnName;
            if (columnPrefix != null && !columnPrefix.isEmpty()) {
                // When columnPrefix is specified,ignore columns without the prefix.
                if (columnName.toUpperCase(Locale.ENGLISH).startsWith(columnPrefix)) {
                    propertyName = columnName.substring(columnPrefix.length());
                } else {
                    continue;
                }
            }
            final String property = metaObject.findProperty(propertyName, false);
            if (property != null && metaObject.hasSetter(property)) {
                final Class<?> propertyType = metaObject.getSetterType(property);
                if (typeHandlerRegistry.hasTypeHandler(propertyType)) {
                    final TypeHandler<?> typeHandler = rsw.getTypeHandler(propertyType, columnName);
                    // 使用 TypeHandler 取得结果
                    final Object value = typeHandler.getResult(rsw.getResultSet(), columnName);
                    if (value != null) {
                        foundValues = true;
                    }
                    if (value != null || !propertyType.isPrimitive()) {
                        // 通过反射工具类设置属性值
                        metaObject.setValue(property, value);
                    }
                }
            }
        }
        return foundValues;
    }

}
