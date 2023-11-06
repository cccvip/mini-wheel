/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.builder;


import org.mini.mybaits.mapping.SqlSource;
import org.mini.mybaits.session.Configuration;

import java.util.HashMap;

/**
 * SqlSourceBuilder.
 * 
 * @version CrisisGo v1.0
 * @author Carl, 2023-11-06 16:15
 */
public class SqlSourceBuilder extends BaseBuilder{

    public SqlSourceBuilder(Configuration configuration) {
        super(configuration);
    }


    public SqlSource parse(String sql, Class<?> clazz, HashMap<Object, Object> objectObjectHashMap) {
        return null;
    }


}
