/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx.sync;


import cn.hutool.core.thread.threadlocal.NamedThreadLocal;

import java.util.Map;

/**
 * TransactionSynchronizationManager.
 *
 * @author Carl, 2023-08-24 17:03
 */
public class TransactionSynchronizationManager {

    private static final ThreadLocal<Map<Object, Object>> resources = new NamedThreadLocal<>("Transactional resources");

    public static Object doGetResource(Object actualKey) {
        Map<Object, Object> map = resources.get();
        if (map == null) {
            return null;
        }

        return map.get(actualKey);
    }

}
