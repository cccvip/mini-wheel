/*
 * All Rights Reserved.
 *
 */
package heap;


/**
 * IHeap.
 *
 * @author Carl, 2023-12-25 11:37
 */
public interface IHeap<E> {
    //取出元素
    Boolean offer(E e);

    //添加元素
    Boolean add(E e);

    //取出元素
    E poll();

    //元素
    E peek();
}
