/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.scripting.xmltags;


import org.dom4j.Element;
import org.mini.mybaits.builder.BaseBuilder;
import org.mini.mybaits.mapping.SqlSource;
import org.mini.mybaits.scripting.defaults.RawSqlSource;
import org.mini.mybaits.session.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * XMLScriptBuilder.
 * 
 * @author Carl, 2023-11-21 11:21
 */
public class XMLScriptBuilder extends BaseBuilder {

    private Element element;
    private boolean isDynamic;
    private Class<?> parameterType;

    public XMLScriptBuilder(Configuration configuration, Element element, Class<?> parameterType) {
        super(configuration);
        this.element = element;
        this.parameterType = parameterType;
    }
    public SqlSource parseScriptNode() {
        List<SqlNode> contents = parseDynamicTags(element);
        MixedSqlNode rootSqlNode = new MixedSqlNode(contents);
        return new RawSqlSource(configuration, rootSqlNode, parameterType);
    }

    List<SqlNode> parseDynamicTags(Element element) {
        List<SqlNode> contents = new ArrayList<>();
        String data = element.getText();
        contents.add(new StaticTextSqlNode(data));
        return contents;
    }



}
