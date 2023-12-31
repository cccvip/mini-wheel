/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.scripting.xmltags;


import org.dom4j.Element;
import org.mini.mybaits.executor.paramter.ParameterHandler;
import org.mini.mybaits.mapping.BoundSql;
import org.mini.mybaits.mapping.MappingStatement;
import org.mini.mybaits.mapping.SqlSource;
import org.mini.mybaits.scripting.LanguageDriver;
import org.mini.mybaits.scripting.defaults.DefaultParameterHandler;
import org.mini.mybaits.session.Configuration;

/**
 * XmlLanguageDriver.
 *
 * @author Carl, 2023-11-20 13:52
 */
public class XmlLanguageDriver implements LanguageDriver {
    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }

    @Override
    public ParameterHandler createParameterHandler(MappingStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
    }
}
