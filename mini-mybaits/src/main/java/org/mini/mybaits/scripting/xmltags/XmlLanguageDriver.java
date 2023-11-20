/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.scripting.xmltags;


import org.dom4j.Element;
import org.mini.mybaits.mapping.SqlSource;
import org.mini.mybaits.scripting.LanguageDriver;
import org.mini.mybaits.session.Configuration;

/**
 * XmlLanguageDriver.
 * 
 * @version CrisisGo v1.0
 * @author Carl, 2023-11-20 13:52
 */
public class XmlLanguageDriver implements LanguageDriver {
    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {


        return null;
    }
}
