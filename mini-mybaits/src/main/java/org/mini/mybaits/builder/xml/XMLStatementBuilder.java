/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.builder.xml;


import org.dom4j.Element;
import org.mini.mybaits.builder.BaseBuilder;
import org.mini.mybaits.builder.MapperAssistant;
import org.mini.mybaits.mapping.SqlCommandType;
import org.mini.mybaits.mapping.SqlSource;
import org.mini.mybaits.scripting.LanguageDriver;
import org.mini.mybaits.session.Configuration;

import java.util.Locale;

/**
 * XMLStatementBuilder.
 *
 * @author Carl, 2023-11-21 10:11
 */
public class XMLStatementBuilder extends BaseBuilder {

    private MapperAssistant builderAssistant;
    private Element element;

    public XMLStatementBuilder(Configuration configuration, Element element, MapperAssistant builderAssistant) {
        super(configuration);
        this.element = element;
        this.builderAssistant = builderAssistant;
    }

    //解析语句(select|insert|update|delete)
    // <select>
    //  id="selectPerson"
    //  parameterType="int"
    //  parameterMap="deprecated"
    //  resultType="hashmap"
    //  resultMap="personResultMap"
    //  flushCache="false"
    //  useCache="true"
    //  timeout="10000"
    //  fetchSize="256"
    //  statementType="PREPARED"
    //  resultSetType="FORWARD_ONLY">
    //  SELECT * FROM PERSON WHERE ID = #{id}
    // </select>
    public void parseStatementNode() {
        String id = element.attributeValue("id");
        String parameterType = element.attributeValue("parameterType");
        Class<?> parameterTypeClass = resolveAlias(parameterType);
        //返回的result是Map
        String resultMap = element.attributeValue("resultMap");
        String resultType = element.attributeValue("resultType");
        Class<?> resultTypeClass = resolveAlias(resultType);
        String nodeName = element.getName();
        SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));

        // 获取默认语言驱动器
        Class<?> langClass = configuration.getLanguageRegistry().getDefaultDriverClass();
        LanguageDriver langDriver = configuration.getLanguageRegistry().getDriver(langClass);

        SqlSource sqlSource = langDriver.createSqlSource(configuration, element, parameterTypeClass);

        // 调用助手类 便于统一处理参数的包装
        builderAssistant.addMappingStatement(id, sqlSource, sqlCommandType, parameterTypeClass,
                resultMap,
                resultTypeClass,
                langDriver);
    }
}
