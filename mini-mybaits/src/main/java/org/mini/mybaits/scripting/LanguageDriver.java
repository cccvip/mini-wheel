/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.scripting;


import org.dom4j.Element;
import org.mini.mybaits.mapping.SqlSource;
import org.mini.mybaits.session.Configuration;

/**
 * LanguageDriver.
 * 脚本语言驱动
 * @author Carl, 2023-11-06 15:53
 */
public interface LanguageDriver {

    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);

}
