/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.reflection;


import org.mini.mybaits.reflection.factory.ObjectFactory;
import org.mini.mybaits.reflection.wrapper.ObjectWrapper;
import org.mini.mybaits.reflection.wrapper.ObjectWrapperFactory;

/**
 * MetaObject.
 *
 * @author Carl, 2023-11-03 9:04
 * @version CrisisGo v1.0
 */
public class MetaObject {

    //原对象
    private Object originalObject;
    //对象包装器
    private ObjectWrapper objectWrapper;
    //对象工厂
    private ObjectFactory objectFactory;
    //对象包装器工厂
    private ObjectWrapperFactory objectWrapperFactory;



}
