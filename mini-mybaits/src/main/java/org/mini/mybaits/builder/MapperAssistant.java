/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.builder;


import org.mini.mybaits.mapping.MappingStatement;
import org.mini.mybaits.mapping.ResultMap;
import org.mini.mybaits.mapping.SqlCommandType;
import org.mini.mybaits.mapping.SqlSource;
import org.mini.mybaits.scripting.LanguageDriver;
import org.mini.mybaits.session.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * MapperAssistant.
 * 解析Resource
 *
 * @author Carl, 2023-11-22 10:02
 */
public class MapperAssistant extends BaseBuilder {

    private String currentNamespace;
    private String resource;

    public MapperAssistant(Configuration configuration, String resource) {
        super(configuration);
        this.resource = resource;
    }

    public String getCurrentNamespace() {
        return currentNamespace;
    }

    public void setCurrentNamespace(String currentNamespace) {
        this.currentNamespace = currentNamespace;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    //添加workspace前缀
    private String addCurrentWorkSpace(String base) {
        if (base.contains(".")) {
            return base;
        }
        return currentNamespace + "." + base;
    }

    public MappingStatement addMappingStatement(String id, SqlSource sqlSource,
                                                SqlCommandType sqlCommandType,
                                                Class<?> parameterType,
                                                String resultMap,
                                                Class<?> resultType,
                                                LanguageDriver lang) {
        id = addCurrentWorkSpace(id);

        MappingStatement.Builder statementBuilder = new MappingStatement.Builder(configuration, id, sqlCommandType, sqlSource, resultType);
        //添加
        setStatementResultMap(resultMap, resultType, statementBuilder);

        MappingStatement statement = statementBuilder.build();
        // 映射语句信息，建造完存放到配置项中
        configuration.addMappedStatement(statement);

        return statement;
    }

    private void setStatementResultMap(String resultMap, Class<?> resultType, MappingStatement.Builder builder) {
        // 因为暂时还没有在 Mapper XML 中配置 Map 返回结果，所以这里返回的是 null
//        resultMap = addCurrentWorkSpace(resultMap);
        List<ResultMap> resultMaps = new ArrayList<>();
        if (resultType != null) {
            ResultMap.Builder inlineResultMapBuilder = new ResultMap.Builder(
                    configuration,
                    builder.id() + "-Inline",
                    resultType,
                    new ArrayList<>());
            resultMaps.add(inlineResultMapBuilder.build());
        }
        builder.resultMaps(resultMaps);
    }

}
