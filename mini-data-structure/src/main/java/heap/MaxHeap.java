/*
 * All Rights Reserved.
 *
 */
package heap;


/**
 * MaxHeap.
 *
 * @author Carl, 2023-12-25 15:19
 */
public class MaxHeap extends Heap<Integer> {
    @Override
    public int compareTo(Integer firstElement, Integer secondElement) {
        return firstElement.compareTo(secondElement);
    }
}
