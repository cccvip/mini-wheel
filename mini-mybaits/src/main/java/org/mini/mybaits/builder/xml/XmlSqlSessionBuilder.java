/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.builder.xml;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.mini.mybaits.builder.AbstractSqlSessionBuilder;
import org.mini.mybaits.datasource.DataSourceFactory;
import org.mini.mybaits.io.Resources;
import org.mini.mybaits.mapping.Environment;
import org.mini.mybaits.mapping.MappingStatement;
import org.mini.mybaits.mapping.SqlCommandType;
import org.mini.mybaits.session.Configuration;
import org.mini.mybaits.transaction.TransactionFactory;
import org.xml.sax.InputSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * XmlSqlSessionBuilder.
 *
 * @author Carl, 2023-09-14 9:59
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

            //扫描mapper方法
            addEnvironmentElement(root);
            addMapperElement(root);
        } catch (DocumentException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void addEnvironmentElement(Element root) throws IllegalAccessException, InstantiationException {

        Element environmentRoot = root.element("environments");
        String value = environmentRoot.attributeValue("default");

        List<Element> environments = environmentRoot.elements("environment");

        for (Element e : environments) {
            String id = e.attributeValue("id");
            if (value.equals(id)) {
                // 事务管理器
                TransactionFactory txFactory = (TransactionFactory) typeAliasRegistry.resolveAlias(e.element("transactionManager").attributeValue("type")).newInstance();

                // 数据源
                Element dataSourceElement = e.element("dataSource");
                DataSourceFactory dataSourceFactory = (DataSourceFactory) typeAliasRegistry.resolveAlias(dataSourceElement.attributeValue("type")).newInstance();
                List<Element> propertyList = dataSourceElement.elements("property");
                Properties props = new Properties();
                for (Element property : propertyList) {
                    props.setProperty(property.attributeValue("name"), property.attributeValue("value"));
                }
                dataSourceFactory.setProperties(props);
                DataSource dataSource = dataSourceFactory.getDataSource();

                // 构建环境
                Environment.Builder environmentBuilder = new Environment.Builder(id)
                        .transactionFactory(txFactory)
                        .dataSource(dataSource);

                configuration.setEnvironment(environmentBuilder.build());
            }
        }
    }


    private void addMapperElement(Element root) {
        try {
            List<Element> mapperList = root.elements("mappers");
            SAXReader saxReader = new SAXReader();
            for (Element e : mapperList) {
                Element mapper = e.element("mapper");
                System.out.println(mapper);
                String resource = mapper.attributeValue("resource");
                //资源加载
                Reader reader = Resources.getResourceAsReader(resource);
                Document document = saxReader.read(new InputSource(reader));
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
                    System.out.println(msId);
                    String nodeName = node.getName();
                    SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase(Locale.ENGLISH));
                    MappingStatement mappedStatement = new MappingStatement(msId, parameterType, resultType, sql, parameter, sqlCommandType);
                    // 添加解析 SQL
                    configuration.addMappedStatement(mappedStatement);
                }

                // 注册Mapper映射器
                configuration.addMapper(Resources.classForName(namespace));
            }
        } catch (IOException | DocumentException | ClassNotFoundException ioException) {
            ioException.printStackTrace();
        }
    }


}
