/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.reflection.invoker;


import java.lang.reflect.Field;

/**
 * SetFiledInvoker.
 *
 * @author Carl, 2023-11-01 15:53
 * @version CrisisGo v1.0
 */
public class SetFiledInvoker implements Invoker {

    private final Field field;

    public SetFiledInvoker(Field field) {
        this.field = field;
    }

    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        field.set(target, args[0]);
        return null;
    }

    @Override
    public Class<?> getType() {
        return field.getType();
    }
}
