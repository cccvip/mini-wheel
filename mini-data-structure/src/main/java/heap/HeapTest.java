/*
 * All Rights Reserved.
 *
 */
package heap;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * HeapTest.
 *
 * @author Carl, 2023-12-26 16:00
 */
public class HeapTest {

    private static Logger logger = LogManager.getLogger(HeapTest.class);

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.add(1);
        minHeap.add(2);
        minHeap.add(3);
        minHeap.add(4);
        minHeap.add(5);
        minHeap.add(6);
        minHeap.add(7);
        minHeap.add(8);
        do {
            Integer i = minHeap.peek();
            if (null == i) {
                break;
            }
            logger.info(i);
        } while (true);
    }

}
