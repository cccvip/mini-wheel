/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.session.defaults;


import org.mini.mybaits.builder.xml.XmlSqlSessionBuilder;
import org.mini.mybaits.session.Configuration;
import org.mini.mybaits.session.SqlSession;
import org.mini.mybaits.session.SqlSessionFactory;

import java.io.Reader;

/**
 * DefaultSqlSessionFactory.
 *
 * @author Carl, 2023-08-30 15:08
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private final Configuration configuration;

    public DefaultSqlSessionFactory(Reader reader) {
        XmlSqlSessionBuilder xmlSqlSessionBuilder =new XmlSqlSessionBuilder(reader);
        xmlSqlSessionBuilder.parse();
        this.configuration = xmlSqlSessionBuilder.getConfiguration();
    }


    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

}
