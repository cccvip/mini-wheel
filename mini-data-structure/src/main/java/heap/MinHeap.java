/*
 * All Rights Reserved.
 *
 */
package heap;


/**
 * MinHeap.
 *
 * @author Carl, 2023-12-25 15:34
 */
public class MinHeap extends Heap<Integer> {
    @Override
    public int compareTo(Integer firstElement, Integer secondElement) {
        return secondElement.compareTo(firstElement);
    }
}
