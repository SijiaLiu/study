package com.lsj.backtrack;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 */
public class RobotMovingCount {

    public int movingCount(int m, int n, int k) {
        if (m <= 0 || n <= 0 || k < 0) {
            return 0;
        }
        // 用一个辅助数组来标记机器人是否访问过
        boolean[][] visited = new boolean[m][n];
        return helper(k, visited, m, n, 0, 0);
    }

    private int helper(int k, boolean[][] visited, int m, int n, int row, int col) {
        int count = 0;
        // 判断机器人要访问的点是否合法
        if (row >= 0 && row < m && col >= 0 && col < n
                && (getNum(row) + getNum(col)) <= k && !visited[row][col]) {
            // 本题是要找机器人所有能到达的点有多少个，所以要标记已访问过，但后续不需要回退了
            visited[row][col] = true;
            count = 1 + helper(k, visited, m, n, row - 1, col)
                    + helper(k, visited, m, n, row + 1, col)
                    + helper(k, visited, m, n, row, col - 1)
                    + helper(k, visited, m, n, row, col + 1);
        }
        return count;
    }

    /**
     * 把行数和列数按规则转成数字
     *
     * @param num
     * @return
     */
    private int getNum(int num) {
        int result = 0;
        while (num > 0) {
            result += num % 10;
            num = num / 10;
        }
        return result;
    }

}
