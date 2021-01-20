package com.lsj.matrix;

public class Matrix {

    /**
     * 输入一个矩阵和一个目标值 判断该值在矩阵中是否存在
     */
    public static boolean findInMatrix(int[][] matrix, int i) {
        if (null == matrix || matrix.length == 0) {
            return false;
        }
        boolean ans = false;
        int row = matrix.length;
        int col = matrix[0].length;
        int r = 0;
        int c = col - 1;
        // 从右上角开始找 缩小范围
        while (r < row && c >= 0) {
            if (i == matrix[r][c]) {
                ans = true;
                break;
            }
            if (i > matrix[r][c]) {
                r++;
            } else if (i < matrix[r][c]) {
                c--;
            }
        }
        return ans;
    }
}
