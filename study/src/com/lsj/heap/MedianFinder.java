package com.lsj.heap;

import java.util.PriorityQueue;

/**
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 * <p>
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 * <p>
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 */
public class MedianFinder {

    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    int maxSize = 0;
    int minSize = 0;

    /**
     * initialize your data structure here.
     */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
        if (maxSize == 0) {
            maxHeap.add(num);
            maxSize++;
            return;
        }
        if (minSize == 0) {
            // 确保大顶堆的数据都小于小顶堆的数据
            if (num > maxHeap.peek()) {
                minHeap.add(num);
            } else {
                minHeap.add(maxHeap.poll());
                maxHeap.add(num);
            }
            minSize++;
            return;
        }
        if (num > minHeap.peek()) {
            minHeap.add(num);
            minSize++;
        } else {
            maxHeap.add(num);
            maxSize++;
        }
        balance();
    }
    private void balance() {
//        // minSize 不允许大于 maxSize
//        while (minSize > maxSize) {
//            maxHeap.add(minHeap.poll());
//            minSize--;
//            maxSize++;
//        }
//        // 两个堆的大小平衡 如果总数据量是奇数，则大顶堆比小顶堆多一位
//        while (Math.abs(maxSize - minSize) > 1) {
//            // 经历了上面的while，maxSize 肯定是大于 minSize的
//            minHeap.add(maxHeap.poll());
//            minSize++;
//            maxSize--;
//        }
        // 两个堆的大小平衡时 return
        if(Math.abs(maxSize - minSize) <= 1) {
            return;
        }
        if (minSize > maxSize) {
            maxHeap.add(minHeap.poll());
            maxSize++;
            minSize--;
        } else {
            minHeap.add(maxHeap.poll());
            minSize++;
            maxSize--;
        }
    }

    public double findMedian() {
        if (minSize > maxSize) {
            return minHeap.peek();
        }
        if (maxSize > minSize) {
            return maxHeap.peek();
        }
        // 偶数 则取中间两个数的平均值
        return (double) (minHeap.peek() + maxHeap.peek()) / 2;
    }
}
