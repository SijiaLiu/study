package com.lsj.dp;

/**
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 * 示例 2：
 * <p>
 * <p>
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：matrix = [["0"]]
 * 输出：0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximalSquare {

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1','0', '1', '0', '0'},
                {'1','0', '1', '1', '1'},
                {'1','1', '1', '1', '1'},
                {'1','0', '0', '1', '0'}
        };
        char[][] ma = new char[][] {
                {'0', '1'},
                {'1', '0'}
        };

        int i = maximalSquare(ma);
        System.out.println(i);
        System.out.println(compareVersion("1.01", "1.001"));
    }

    private static int maximalSquare(char[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return 0;
        }
        int result = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        // 辅助数组，用来映射原数组上某个位置最大的边长
        int[][] m = new int[row][col];
        // 初始化辅助数组的两条边，注意，如果有'1'的话，需要把result赋值1，因为至少都有一个正方形
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == '1') {
                m[i][0] = 1;
                result = 1;
            }
        }
        for (int i = 0; i < col; i++) {
            if (matrix[0][i] == '1') {
                m[0][i] = 1;
                result = 1;
            }
        }
        // 辅助数组已经初始化了，所以原数组从[1,1]这个位置开始
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                // 只有遇到'1'的时候才要改辅助数组的值，0的时候就是0
                if (matrix[i][j] == '1') {
                    // 取目标位置相邻的位置的值的最小的再加1
                    // 取最小的原因是因为目标是要一个正方形，画个图就明白了
                    m[i][j] = Math.min(Math.min(m[i - 1][j - 1], m[i - 1][j]), m[i][j - 1]) + 1;
                    // 每次都更新最大值，如果有的话
                    result = Math.max(result, m[i][j]);
                }
            }
        }
        return result * result;
    }

    /**
     * 比较版本
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        // 肯定是要两个指针遍历字符串，遇到 . 的时候停下来，比较 . 之前的数据
        int m = version1.length();
        int n = version2.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            int num1 = 0, num2 = 0;
            while (i < m && version1.charAt(i) != '.') {
                // i 前进的同时，数字前进一位，同时针对 '0' 要特殊处理
                // 开头的 '0' 也能干掉
                num1 = num1 * 10 + version1.charAt(i++) - '0';
            }
            while (j < n && version2.charAt(j) != '.') {
                // i 前进的同时，数字前进一位，同时针对 '0' 要特殊处理
                // 开头的 '0' 也能干掉
                num2 = num2 * 10 + version1.charAt(j++) - '0';
            }
            if (num1 > num2) {
                return 1;
            }
            if (num2 > num1) {
                return -1;
            }
            // 上面只是比较了一个 . 之前的版本号，继续比较
            i++;
            j++;
        }
        return 0;
    }
}
