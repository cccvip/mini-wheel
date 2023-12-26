/*
 * All Rights Reserved.
 *
 */
package heap;


import com.alibaba.fastjson2.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

/**
 * Heap.
 *
 * @author Carl, 2023-12-25 11:40
 */
public abstract class Heap<T> implements IHeap<T> {

    private Logger logger = LogManager.getLogger(Heap.class);

    private Integer initSize = 8;

    private Object[] element;

    private Integer index = 0;

    public Heap() {
        this.element = new Object[initSize];
    }

    @Override
    public Boolean offer(T t) {
        return add(t);
    }

    @Override
    public Boolean add(T t) {
        //如果size不够,需要进行扩容处理
        if (t == null) {
            throw new NullPointerException();
        }
        int i = index;
        if (i >= element.length) {
            grow(i + 1);
        }
        index = i + 1;
        if (i == 0) {
            element[0] = t;
        } else {
            siftUp(i, t);
        }
        return true;
    }

    private void siftUp(int k, T x) {
        siftUpComparable(k, x);
    }

    private void siftUpComparable(int k, T x) {
        logger.info("【入队】元素：{} 当前队列：{}", JSON.toJSONString(x), JSON.toJSONString(element));
        while (k > 0) {
            // 获取父节点Idx，相当于除以2
            int parent = (k - 1) >>> 1;
            logger.info("【入队】寻找当前节点的父节点位置。k：{} parent：{}", k, parent);
            Object e = element[parent];
            // 如果当前位置元素，大于父节点元素，则退出循环
            if (compareTo(x, (T) e) >= 0) {
                logger.info("【入队】值比对，父节点：{} 目标节点：{}", JSON.toJSONString(e), JSON.toJSONString(x));
                break;
            }
            // 相反父节点位置大于当前位置元素，则进行替换
            logger.info("【入队】替换过程，父子节点位置替换，继续循环。父节点值：{} 存放到位置：{}", JSON.toJSONString(e), k);
            element[k] = e;
            k = parent;
        }
        element[k] = x;
        logger.info("【入队】完成 Idx：{} Val：{} \r\n当前队列：{} \r\n", k, JSON.toJSONString(x), JSON.toJSONString(element));
    }

    //最大堆/最小堆
    public abstract int compareTo(T firstElement, T secondElement);

    private void grow(int minCapacity) {
        int oldCapacity = element.length;
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
                (oldCapacity + 2) :
                (oldCapacity >> 1));
        if (newCapacity - Integer.MAX_VALUE - 8 > 0) {
            newCapacity = (minCapacity > Integer.MAX_VALUE - 8) ?
                    Integer.MAX_VALUE :
                    Integer.MAX_VALUE - 8;
        }
        element = Arrays.copyOf(element, newCapacity);
    }


    //移除元素
    @Override
    public T poll() {
        if (index == 0) {
            return null;
        }
        int s = --index;
        T result = (T) element[0];
        T x = (T) element[s];
        element[s] = null;
        if (s != 0) {
            siftDown(0, x);
        }
        return result;
    }

    private void siftDown(int k, T x) {
        siftDownComparable(k, x);
    }

    @SuppressWarnings("unchecked")
    private void siftDownComparable(int k, T x) {
        // 先找出中间件节点
        int half = index >>> 1;
        while (k < half) {
            // 找到左子节点和右子节点，两个节点进行比较，找出最大的值
            int child = (k << 1) + 1;
            Object c = element[child];
            int right = child + 1;
            // 左子节点与右子节点比较，取最小的节点
            if (right < index && compareTo((T) c, (T) element[right]) > 0) {
                logger.info("【出队】左右子节点比对，获取最小值。left：{} right：{}", JSON.toJSONString(c), JSON.toJSONString(element[right]));
                c = element[child = right];
            }
            // 目标值与c比较，当目标值小于c值，退出循环。说明此时目标值所在位置适合，迁移完成。
            if (compareTo(x, (T) c) <= 0) {
                break;
            }
            // 目标值小于c值，位置替换，继续比较
            logger.info("【出队】替换过程，节点的值比对。上节点：{} 下节点：{} 位置替换", JSON.toJSONString(element[k]), JSON.toJSONString(c));
            element[k] = c;
            k = child;
        }
        // 把目标值放到对应位置
        logger.info("【出队】替换结果，最终更换位置。Idx：{} Val：{}", k, JSON.toJSONString(x));
        element[k] = x;
    }

    @Override
    public T peek() {
        return poll();
    }
}
