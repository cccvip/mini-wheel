/*
 * All Rights Reserved.
 *
 */
package mini.spring.jdbc.tx;


/**
 * InvocationCallback.
 *
 * @author Carl, 2023-08-24 15:08
 */
@FunctionalInterface
public interface InvocationCallback {
    Object proceedWithInvocation() throws Throwable;
}
