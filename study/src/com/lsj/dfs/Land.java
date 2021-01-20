package com.lsj.dfs;

public class Land {

    /**
     * 计算陆地的数量 DFS
     *
     * @param grid
     * @return
     */
    public int numLands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                ans += lands(grid, i, j);
            }
        }
        return ans;
    }

    private int lands(char[][] grid, int i, int j) {
        // 如果是0说明是海水，直接返回0
        if (grid[i][j] == '0') {
            return 0;
        }
        // 把陆地置为海水
        grid[i][j] = '0';
        // 使用深度优先算法，把这块陆地关联的陆地都设置为海水
        int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int k = 0; k < dir.length; k++) {
            int[] ints = dir[k];
            if (i + ints[0] >= 0 && i + ints[0] < grid.length && j + ints[1] >= 0 && j + ints[1] < grid[0].length) {
                lands(grid, i + ints[0], j + ints[1]);
            }
        }
        // 这次发现了一块陆地
        return 1;
    }

    /**
     * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
     *
     * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
     * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
     *
     * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/max-area-of-island
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j, row, col));
                }
            }
        }
        return res;
    }

    private int dfs(int[][] grid, int i, int j, int row, int col) {
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == 0) {
            return 0;
        }
        int num = 1;
        grid[i][j] = 0;
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] d : dir) {
            num += dfs(grid, i + d[0], j + d[1], row, col);
        }
        return num;
    }
}
