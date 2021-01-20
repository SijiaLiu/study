package com.lsj.heap;

/**
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * <p>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。
 * 每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 */
public class KthLargest {

    /**
     * 小顶堆
     */
    private int[] minHeap;
    /**
     * 堆的规模
     */
    private int k;
    /**
     * 堆的最后一个元素的索引
     */
    private int last;

    public int getK(int k, int[] nums) {
        this.k = k;
        // 从一开始 比较好计算
        this.minHeap = new int[k + 1];
        // 防止数组越界
        for (int i = 1; i <= k && i <= nums.length; i++) {
            this.minHeap[i] = nums[i - 1];
            this.last = i;
        }

        /*如果小顶堆被填满了,就继续添加元素,并使堆有序*/
        if (last == k) {
            order();
            for (int i = k; i < nums.length; i++) {
                add(nums[i]);
            }
        }
        return minHeap[1];
    }

    public int add(int val) {
        /*如果堆还未被填满（由题意堆会在至多一次add后被填满）,则将元素放在无序堆的末尾，然后将堆有序化*/
        if (last == k - 1) {
            minHeap[++last] = val;
            order();
        }
        /*如果新元素大于堆中第k大的元素（minHeap[1]），则替换它，并将堆有序化*/
        else if (val > minHeap[1]) {
            minHeap[1] = val;
            /*此时处堆尖处无序外,其他部分为有序,所以只需将堆顶下沉即可*/
            sink(1);
        }
        return minHeap[1];
    }

    /**
     * 堆的有序化
     */
    private void order() {
        for (int i = k / 2; i >= 1; i--) {
            sink(i);
        }
    }

    /**
     * 下沉
     */
    private void sink(int i) {
        while (2 * i <= k) {
            int j = 2 * i;

            if (j < k && minHeap[j] > minHeap[j + 1]) {
                j++;
            }

            if (minHeap[i] > minHeap[j]) {
                int temp = minHeap[i];
                minHeap[i] = minHeap[j];
                minHeap[j] = temp;

                i = j;
            } else {
                break;
            }
        }
    }

}
