/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.builder.xml;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.mini.mybaits.builder.AbstractSqlSessionBuilder;
import org.mini.mybaits.io.Resources;
import org.mini.mybaits.mapping.MappingStatement;
import org.mini.mybaits.mapping.SqlCommandType;
import org.mini.mybaits.session.Configuration;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * XmlSqlSessionBuilder.
 *
 * @author Carl, 2023-09-14 9:59
 * @version CrisisGo v1.0
 */
public class XmlSqlSessionBuilder extends AbstractSqlSessionBuilder {

    Reader reader;

    public XmlSqlSessionBuilder(Reader reader) {
        super(new Configuration());
        this.reader = reader;
    }

    @Override
    public void parse() {
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            Element root = document.getRootElement();
            System.out.println(root);
            List<Element> mapperList = root.elements("mappers");
            for (Element e : mapperList) {
                Element mapper = e.element("mapper");
                System.out.println(mapper);
                String resource = mapper.attributeValue("resource");
                //资源加载
                Reader reader = Resources.getResourceAsReader(resource);
                saxReader = new SAXReader();
                document = saxReader.read(new InputSource(reader));
                root = document.getRootElement();
                //命名空间
                String namespace = root.attributeValue("namespace");
                // SELECT
                List<Element> selectNodes = root.elements("select");
                for (Element node : selectNodes) {
                    String id = node.attributeValue("id");
                    String parameterType = node.attributeValue("parameterType");
                    String resultType = node.attributeValue("resultType");
                    String sql = node.getText();

                    // ? 匹配
                    Map<Integer, String> parameter = new HashMap<>();
                    Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                    Matcher matcher = pattern.matcher(sql);
                    for (int i = 1; matcher.find(); i++) {
                        String g1 = matcher.group(1);
                        String g2 = matcher.group(2);
                        parameter.put(i, g2);
                        sql = sql.replace(g1, "?");
                    }
                    String msId = namespace + "." + id;
                    String nodeName = node.getName();
                    SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
                    MappingStatement mappedStatement = new MappingStatement(msId, parameterType, resultType, sql, parameter, sqlCommandType);
                    // 添加解析 SQL
                    configuration.addMappedStatement(mappedStatement);
                }

                // 注册Mapper映射器
                configuration.addMapper(Resources.classForName(namespace));
            }

        } catch (DocumentException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
