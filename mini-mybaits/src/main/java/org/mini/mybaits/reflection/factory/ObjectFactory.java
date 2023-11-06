/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.reflection.factory;


import java.util.List;
import java.util.Properties;

/**
 * ObjectFactory.
 *
 * @author Carl, 2023-11-03 8:57
 * @version CrisisGo v1.0
 */
public interface ObjectFactory {

    /**
     * setProperties.
     * 设置属性
     *
     * @param properties
     * @return void
     */
    void setProperties(Properties properties);

    /**
     * createObject.
     * 创建对象,无参构造函数
     * @param tClass 实体类类型
     * @return T
     */
    <T> T createObject(Class<T> tClass);

    /**
     * create.
     * 有参构造函数
     * @param type
     * @param constructorArgTypes 参数类型
     * @param constructorArgs  参数
     * @return T
     */
    <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs);

}
