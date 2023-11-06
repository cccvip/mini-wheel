/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package org.mini.mybaits.reflection.invoker;


/**
 * Invoker.
 * 调用者
 * @version CrisisGo v1.0
 * @author Carl, 2023-11-01 15:46
 */
public interface Invoker {

    Object invoke(Object target, Object[] args) throws Exception;

    Class<?> getType();

}
