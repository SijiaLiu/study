package com.lsj.dp;

import java.util.Arrays;

public class MinFallingPathSum {

    /**
     * 给你一个 n x n 的 方形 整数数组matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
     *
     * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。
     * 在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
     * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
     *
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-falling-path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    int[][] mem;
    public int minFallingPathSum(int[][] matrix) {

        int row = matrix.length;
        mem = new int[row][row];
        for (int i = 0; i < row; i++) {
            // 初始值是用来标记的，所以不能使用合法答案的值。
            // matrix 是 n x n 的二维数组，其中 1 <= n <= 100；对于二维数组中的元素，有 -100 <= matrix[i][j] <= 100
            // 所以，我们 memo 的初始值就要避开区间 [-10000, 10000]
            Arrays.fill(mem[i], 1111111);
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j < row; j++) {
            // 结果就在最后一行里 找最后一行的最小值
            res = Math.min(res, dp(matrix, row - 1, j));
        }
        return res;
    }

    /**
     * 计算 i , j 位置的下降路径的最小和
     *
     * @param matrix
     * @param row
     * @param col
     * @return
     */
    private int dp(int[][] matrix, int row, int col) {
        // 数组越界，返回一个极大值，注意要比原数组中的可能出现的最大值大,上面解释了 最大值是 10000
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix.length) {
            return 9999999;
        }
        // 第一行 那只能是自己
        if (row == 0) {
            return matrix[0][col];
        }
        // 防止重复计算
        if (mem[row][col] != 1111111) {
            return mem[row][col];
        }
        // 每个位置的值等于它 左上角 上方 右上角 的最小值 加上自己
        int res = matrix[row][col] + min(dp(matrix, row - 1, col), dp(matrix, row - 1, col - 1), dp(matrix, row - 1, col + 1));
        mem[row][col] = res;
        return res;
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

}
