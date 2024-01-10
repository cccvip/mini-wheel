/*
 * Copyright @2024 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package design.iterator.core;


/**
 * Collection.
 * 集合迭代
 *
 * @author Carl, 2024-01-10 13:39
 * @version CrisisGo v1.0
 */
public interface Collection<E, L> extends Iteratable<E> {

    boolean add(E e);

    boolean remove(E e);

    boolean addLink(String key, L l);

    boolean removeLink(String key);

    @Override
    Iterator<E> iterator();
}
