/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.sync;


import cn.hutool.core.thread.threadlocal.NamedThreadLocal;
import mini.spring.jdbc.tx.manager.ConnectionHolder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * TransactionSynchronizationManager.
 *
 * @author Carl, 2023-08-24 17:03
 * @version CrisisGo v1.0
 */
public class TransactionSynchronizationManager {

    private static final ThreadLocal<Map<Object, Object>> resources = new NamedThreadLocal<>("Transactional resources");

    private static final ThreadLocal<Boolean> actualTransactionActive =
            new NamedThreadLocal<>("Actual transaction active");

    public static Object doGetResource(Object actualKey) {
        Map<Object, Object> map = resources.get();
        if (map == null) {
            return null;
        }

        return map.get(actualKey);
    }

    public static void setActualTransactionActive(boolean active) {
        actualTransactionActive.set(active ? Boolean.TRUE : null);
    }

    public static void unbindResource(Object key) {
        Map<Object, Object> map = resources.get();
        if (map == null) {
            return;
        }
        map.remove(key);
        if (map.isEmpty()) {
            resources.remove();
        }
    }

    public static void bindResource(Object key, Object value) throws IllegalStateException {
        Map<Object, Object> map = resources.get();
        if (map == null) {
            map = new HashMap<>();
            resources.set(map);
        }
    }
}
