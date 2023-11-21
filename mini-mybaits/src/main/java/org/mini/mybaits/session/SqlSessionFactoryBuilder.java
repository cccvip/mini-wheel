/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session;


import org.mini.mybaits.builder.xml.XMLConfigBuilder;
import org.mini.mybaits.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

/**
 * SqlSessionFactoryBuilder.
 * Sql_XML Session配置
 *
 * @author Carl, 2023-10-31 14:43
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        XMLConfigBuilder xmlSqlSessionBuilder = new XMLConfigBuilder(reader);
        return build(xmlSqlSessionBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}
