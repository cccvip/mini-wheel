/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.reflection.invoker;


import java.lang.reflect.Method;

/**
 * MethodInvoker.
 *
 * @author Carl, 2023-11-01 15:49
 * @version CrisisGo v1.0
 */
public class MethodInvoker implements Invoker {

    private Class<?> type;

    private Method method;

    public MethodInvoker(Method method) {
        this.method = method;
        if (method.getParameterTypes().length == 1) {
            type = method.getParameterTypes()[0];
        }else {
            type = method.getReturnType();
        }
    }


    @Override
    public Object invoke(Object target, Object[] args) throws Exception {
        return method.invoke(target, args);
    }

    @Override
    public Class<?> getType() {
        return type;
    }
}
