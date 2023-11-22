/*
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.mapping;


import org.mini.mybaits.session.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ResultMap.
 *
 * @author Carl, 2023-11-22 9:40
 */
public class ResultMap {
    private String id;
    private Class<?> type;
    private List<ResultMapping> resultMappings;
    private Set<String> mappedColumns;

    ResultMap() {

    }

    //使用构造器模式
    public static class Builder {
        private ResultMap resultMap = new ResultMap();

        public Builder(Configuration configuration, String id, Class<?> type, List<ResultMapping> resultMappings) {
            resultMap.id = id;
            resultMap.type = type;
            resultMap.resultMappings = resultMappings;
        }

        public ResultMap build() {
            resultMap.mappedColumns = new HashSet<>();
            return resultMap;
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public List<ResultMapping> getResultMappings() {
        return resultMappings;
    }

    public void setResultMappings(List<ResultMapping> resultMappings) {
        this.resultMappings = resultMappings;
    }

    public Set<String> getMappedColumns() {
        return mappedColumns;
    }

    public void setMappedColumns(Set<String> mappedColumns) {
        this.mappedColumns = mappedColumns;
    }
}
