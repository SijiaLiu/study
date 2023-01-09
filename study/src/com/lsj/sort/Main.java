package com.lsj.sort;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 1, 1, 2, 5, 3, 19};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(nums);
        //quickSort.quickSort(nums, 0 , nums.length);
        Arrays.stream(nums).forEach(System.out::println);

        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(nums);
        Arrays.stream(nums).forEach(System.out::println);

    }

    private void sort(int[] nums, int left, int right) {
        if (left < right) {
            int l = left;
            int r = right;
            // 定义一个基准点 数组的末尾
            int pivot = nums[right];
            while (l < r) {
                // 从左开始，比基准点小的数都在左边 直到遇到比他大的
                while (l < r && nums[l] <= pivot) {
                    l++;
                }
                if (l < r) {
                    // 此时 nums[l] 是大于基准点的 所以移到右边
                    nums[r] = nums[l];
                }
                while (l < r && nums[r] > pivot) {
                    r--;
                }
                if (l < r) {
                    // 此时nums[r] 小于基准点 所以移到左边
                    // 之前nums[l]
                    nums[l] = nums[r];
                }
            }
            nums[r] = pivot;
            sort(nums, left, r - 1);
            sort(nums, r+1, right);

        }
    }
}
