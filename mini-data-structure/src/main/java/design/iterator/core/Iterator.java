/*
 * Copyright @2024 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package design.iterator.core;


/**
 * Iterator.
 * 
 * @version CrisisGo v1.0
 * @author Carl, 2024-01-10 13:35
 */
public interface Iterator<E> {

    boolean hasNext();

    E next();
}
