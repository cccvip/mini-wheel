/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.reflection.invoker;


import java.lang.reflect.Field;

/**
 * GetFiledInvoker.
 * 
 * @version CrisisGo v1.0
 * @author Carl, 2023-11-01 15:53
 */
public class GetFiledInvoker implements Invoker{

    private Field field;

    public GetFiledInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        return field.get(target);
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
