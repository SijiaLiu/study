package com.lsj.algorithm;

public class Simple {


    /**
     * 最大子列和 分治算法 O(N*logN)
     *
     * @param list
     * @param left
     * @param right
     * @return
     */
    public int divideAndConquer(int[] list, int left, int right) {

        int maxLeft;
        int maxRight;

        if (left == right) {
            return Math.max(list[left], 0);
        }
        // 分
        int center = (left + right) / 2;
        // 两边子列的最大和
        maxLeft = divideAndConquer(list, left, center);
        maxRight = divideAndConquer(list, center + 1, right);

        int maxLeftBorder = 0;
        int maxLeftSum = 0;
        for (int i = center; i >= left; i--) {
            maxLeftSum += list[i];
            if (maxLeftSum > maxLeftBorder) {
                maxLeftBorder = maxLeftSum;
            }
        }

        int maxRightBorder = 0;
        int maxRightSum = 0;
        for (int i = center + 1; i <= right; i++) {
            maxRightSum += list[i];
            if (maxRightSum > maxRightBorder) {
                maxRightBorder = maxRightSum;
            }
        }
        // 跨分界线的最大子列和
        int border = maxLeftBorder + maxRightBorder;
        // 治
        return maxLeft > maxRight ? Math.max(maxLeft, border) : Math.max(maxRight, border);
    }

    /**
     * 最大子列和 在线处理，O(N)
     *
     * @param list
     * @param left
     * @param right
     * @return
     */
    public int maxSubSum(int[] list, int left, int right) {
        int maxSum = 0;
        int thisSum = 0;

        for (int i = left; i <= right; i++) {
            thisSum += list[i];
            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }
}
