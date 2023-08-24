/*
 * All Rights Reserved.
 *
 */
package mini.spring.aop.support;


import com.sun.istack.internal.Nullable;
import mini.spring.aop.proxy.AopProxy;

/**
 * AopUtils.
 *
 * @author Carl, 2023-08-24 16:19
 */
public class AopUtils {
    public static final String CGLIB_CLASS_SEPARATOR = "$$";

    public static Class<?> getTargetClass(Object candidate) {
        return isCglibProxy(candidate) ? candidate.getClass().getSuperclass() : candidate.getClass();
    }

    public static boolean isCglibProxy(@Nullable Object object) {
        return (object instanceof AopProxy &&
                object.getClass().getName().contains(CGLIB_CLASS_SEPARATOR));
    }

}
