/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.builder.xml;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.mini.mybaits.builder.BaseBuilder;
import org.mini.mybaits.builder.MapperAssistant;
import org.mini.mybaits.io.Resources;
import org.mini.mybaits.session.Configuration;

import java.io.InputStream;
import java.util.List;

/**
 * XMLMapperBuilder.
 *
 * @author Carl, 2023-11-21 10:05
 */
public class XMLMapperBuilder extends BaseBuilder {

    private Element element;
    private String resource;
    private MapperAssistant builderAssistant;

    public XMLMapperBuilder(InputStream inputStream, Configuration configuration, String resource) throws DocumentException {
        this(new SAXReader().read(inputStream), configuration, resource);
    }

    public XMLMapperBuilder(Document document, Configuration configuration, String resource) {
        super(configuration);
        this.element = document.getRootElement();
        this.resource = resource;
        //助手
        this.builderAssistant = new MapperAssistant(configuration, resource);
    }


    public void parse() throws Exception {
        // 如果当前资源没有加载过再加载，防止重复加载
        if (!configuration.isResourceLoaded(resource)) {
            configurationElement(element);
            // 标记一下，已经加载过了
            configuration.addLoadedResource(resource);
            // 绑定映射器到namespace
            configuration.addMapper(Resources.classForName(builderAssistant.getCurrentNamespace()));
        }
    }

    private void configurationElement(Element element) {
        // 1.配置namespace
        String currentNamespace = element.attributeValue("namespace");
        if (currentNamespace.equals("")) {
            throw new RuntimeException("Mapper's namespace cannot be empty");
        }
        builderAssistant.setCurrentNamespace(currentNamespace);
        // 2.配置select|insert|update|delete
        buildStatementFromContext(element.elements("select"));
    }


    // 配置select|insert|update|delete
    private void buildStatementFromContext(List<Element> list) {
        for (Element element : list) {
            final XMLStatementBuilder statementParser = new XMLStatementBuilder(configuration, element, builderAssistant);
            statementParser.parseStatementNode();
        }
    }
}
