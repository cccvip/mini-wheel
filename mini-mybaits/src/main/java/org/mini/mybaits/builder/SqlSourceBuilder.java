/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.builder;


import org.mini.mybaits.mapping.ParameterMapping;
import org.mini.mybaits.mapping.SqlSource;
import org.mini.mybaits.mapping.sql.StaticSqlSource;
import org.mini.mybaits.parsing.GenericTokenParser;
import org.mini.mybaits.parsing.TokenHandler;
import org.mini.mybaits.reflection.MetaClass;
import org.mini.mybaits.reflection.MetaObject;
import org.mini.mybaits.session.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SqlSourceBuilder.
 *
 * @author Carl, 2023-11-06 16:15
 */
public class SqlSourceBuilder extends BaseBuilder {

    public SqlSourceBuilder(Configuration configuration) {
        super(configuration);
    }

    //解析SQL
    public SqlSource parse(String sql, Class<?> parameterType, Map<String, Object> additionalParameters) {
        ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler(configuration, parameterType, additionalParameters);
        GenericTokenParser parser = new GenericTokenParser("#{", "}", handler);
        String s = parser.parse(sql);
        return new StaticSqlSource(s, handler.getParameterMappings(), configuration);
    }


    private static class ParameterMappingTokenHandler extends BaseBuilder implements TokenHandler {
        private List<ParameterMapping> parameterMappings = new ArrayList<>();
        private Class<?> parameterType;
        private MetaObject metaParameters;

        public ParameterMappingTokenHandler(Configuration configuration, Class<?> parameterType, Map<String, Object> additionalParameters) {
            super(configuration);
            this.parameterType = parameterType;
            this.metaParameters = configuration.newMetaObject(additionalParameters);
        }

        @Override
        public String handleToken(String content) {
            parameterMappings.add(buildParameterMapping(content));
            return "?";
        }

        private ParameterMapping buildParameterMapping(String content) {
            Map<String, String> propertiesMap = new ParameterExpression(content);
            String property = propertiesMap.get("property");
            Class<?> propertyType;
            if (typeHandlerRegistry.hasTypeHandler(parameterType)) {
                propertyType = parameterType;
            } else if (property != null) {
                MetaClass metaClass = MetaClass.forClass(parameterType);
                if (metaClass.hasGetter(property)) {
                    propertyType = metaClass.getGetterType(property);
                } else {
                    propertyType = Object.class;
                }
            }else {
                propertyType = Object.class;
            }
            ParameterMapping.Builder builder = new ParameterMapping.Builder(configuration, property, propertyType);
            return builder.build();
        }

        public List<ParameterMapping> getParameterMappings() {
            return parameterMappings;
        }

    }


}
