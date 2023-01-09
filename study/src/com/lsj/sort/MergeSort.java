package com.lsj.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MergeSort {

    // 临时数组
    private int[] tmp;

    public void mergeSort(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return;
        }
        tmp = new int[nums.length];
        // 递归
        sort(0, nums.length - 1, nums);

        // 迭代
        int N = nums.length;
        // sz 每次归并排序的子数组的大小
        for (int sz = 1; sz < N; sz = sz + sz) {
            // i 每次归并排序的起始点
            for (int i = 0; i < N - sz; i += sz + sz) {
                merge(i, i + sz - 1, Math.min(i + sz + sz - 1, N - 1), nums);
            }
        }
    }

    private void sort(int low, int hi, int[] nums) {
        if (low >= hi) {
            return;
        }
        int mid = low + (hi - low) / 2;
        sort(low, mid, nums);
        sort(mid + 1, hi, nums);
        merge(low, mid, hi, nums);
    }


    private void merge(int low, int mid, int hi, int[] nums) {
        int i = low;
        int j = mid + 1;

        // 切记 不能从0开始，因为一次递归只合并一定区间内的
        for (int k = low; k <= hi; k++) {
            tmp[k] = nums[k];
        }

        for (int k = low; k <= hi; k++) {
            if (i > mid) {
                // 左边的元素全用完了
                nums[k] = tmp[j++];
            } else if (j > hi) {
                // 右边的元素用完了
                nums[k] = tmp[i++];
            } else if (tmp[i] < tmp[j]) {
                // 左边当前指针的元素小于右边当前指针的元素，取左边当前指针的元素
                nums[k] = tmp[i++];
            } else {
                nums[k] = tmp[j++];
            }
        }
    }


    /**
     * 给你一个整数数组 nums ，按要求返回一个新数组counts 。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于nums[i] 的元素的数量。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [5,2,6,1]
     * 输出：[2,1,1,0]
     * 解释：
     * 5 的右侧有 2 个更小的元素 (2 和 1)
     * 2 的右侧仅有 1 个更小的元素 (1)
     * 6 的右侧有 1 个更小的元素 (1)
     * 1 的右侧有 0 个更小的元素
     * 示例 2：
     *
     * 输入：nums = [-1]
     * 输出：[0]
     * 示例 3：
     *
     * 输入：nums = [-1,-1]
     * 输出：[0,0]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-of-smaller-numbers-after-self
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    // 增加一个新的数据结构来记录原来每个元素的位置
    class Pair {
        public Pair(int num, int index) {
            this.num = num;
            this.index = index;
        }
        // 元素的值
        int num;
        // 元素在原数组的位置
        int index;
    }
    // 结果集
    int[] index;
    // 临时存放元素
    Pair[] temp;
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null) {
            return null;
        }
        index = new int[nums.length];
        temp = new Pair[nums.length];
        // 把原列表放到新的结构中，记录记录每个元素的位置
        Pair[] pairs = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pairs[i] = new Pair(nums[i], i);
        }
        // 利用归并排序 算出结果
        sort(pairs, 0, pairs.length - 1);
        List<Integer> res = new ArrayList<>();
        for (int j : index) {
            res.add(j);
        }
        return res;
    }

    private void sort(Pair[] pairs, int low, int hi) {
        if (low == hi) {
            return;
        }

        int mid = low + (hi - low) / 2;

        sort(pairs, low, mid);
        sort(pairs, mid + 1, hi);

        merge(pairs, low, mid, hi);
    }

    private void merge(Pair[] pairs, int low, int mid, int hi) {
        for (int i = low; i <= hi; i++) {
            temp[i] = pairs[i];
        }

        int i = low;
        int j = mid + 1;
        // 可以画一下归并排序的原理图，i, j 在向前移动的时候，i ，j 之间的元素有什么特性？
        // 当往排序的结果集中放入temp[i]时，temp[mid + 1, j） 之间是不是都是比 temp[i] 小的元素
        // 所以 在每次放入temp[i]时更新一下比这个元素小的元素有多少，累加就行，为什么累加？因为每次归并排序只是一个小区间
        for (int k = low; k <= hi; k++) {
            if (i == mid + 1) {
                pairs[k] = temp[j++];
            } else if (j == hi + 1) {
                pairs[k] = temp[i++];
                index[pairs[k].index] += j - mid - 1;
            } else if (temp[i].num <= temp[j].num) {
                // 一定要加等号哈，当他们相等的时候就是要填充temp[i]了
                pairs[k] = temp[i++];
                index[pairs[k].index] += j - mid - 1;
            } else {
                pairs[k] = temp[j++];
            }
        }
    }


}
