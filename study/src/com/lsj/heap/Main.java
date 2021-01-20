package com.lsj.heap;

public class Main {
    public static void main(String[] args) {
        MyHeap heap = new MyHeap();
        int[] nums = {2, 1, 3, 5, 7, 9, 20};
        heap.buildMinHeap(nums);
        heap.out(nums);
        System.out.println(heap.getMaxHeapTop(nums));

        KthLargest kthLargest = new KthLargest();
        System.out.println(kthLargest.getK(3, nums));

        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-4);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(-5);
        System.out.println(medianFinder.findMedian());
    }


}
