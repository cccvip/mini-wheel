/*
 * Copyright @2024 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package design.iterator.core;


/**
 * Iteratable.
 *
 * @author Carl, 2024-01-10 13:36
 * @version CrisisGo v1.0
 */
public interface Iteratable<E> {

    Iterator<E> iterator();

}
