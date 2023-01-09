package com.lsj.algorithm;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Simple simple = new Simple();
        int[] list = new int[]{-4, 3, -5, -2, -1, -2, -6, -2};
        int divideAndConquer = simple.divideAndConquer(list, 0, list.length - 1);
        System.out.println(divideAndConquer);
        int maxSum = simple.maxSubSum(list, 0, list.length - 1);
        System.out.println(maxSum);

        TwoSum twoSum = new TwoSum();
        System.out.println(twoSum.threeSums(new int[] {-1,0,1,2,-1,-4}));

        Pow pow = new Pow();
        System.out.println(pow.pow(4, 2));
        System.out.println(pow.myPow(4, -2));

        BinarySearch binarySearch = new BinarySearch();
        System.out.println("找出给定目标值在数组中的开始位置和结束位置");
        System.out.println(Arrays.toString(binarySearch.searchRange(new int[]{2, 3, 3, 4, 6}, 3)));


        System.out.println("最长上升序列的长度");
        LongestConsecutive longestConsecutive = new LongestConsecutive();
        System.out.println(longestConsecutive.longestConsecutive(new int[]{2, 3, 4, 6, 5, 3, 21, 46, 47, 48}));

        System.out.println(binarySearch.singleNumber(new int[]{4,1,2,1,2}));

        System.out.println(binarySearch.minEatingSpeed(new int[]{805306368,805306368,805306368}, 1000000000));

        System.out.println(Arrays.toString(binarySearch.exchange(new int[]{1, 2, 3, 4})));
    }
}
