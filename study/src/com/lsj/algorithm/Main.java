package com.lsj.algorithm;

public class Main {
    public static void main(String[] args) {
        Simple simple = new Simple();
        int[] list = new int[]{-4, 3, -5, -2, -1, -2, -6, -2};
        int divideAndConquer = simple.divideAndConquer(list, 0, list.length - 1);
        System.out.println(divideAndConquer);
        int maxSum = simple.maxSubSum(list, 0, list.length - 1);
        System.out.println(maxSum);
    }
}
