package com.lsj.dfs;

import java.util.HashSet;

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
     * <p>
     * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。
     * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
     * <p>
     * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/max-area-of-island
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
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


    /**
     * 二维矩阵 grid由 0（土地）和 1（水）组成。岛是由最大的4个方向连通的 0 组成的群，
     * 封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
     * <p>
     * 请返回 封闭岛屿 的数目。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
     * 输出：2
     * 解释：
     * 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/number-of-closed-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int closedIsland(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        // 把上下的边的 陆地变为海水
        for (int i = 0; i < col; i++) {
            helper(grid, 0, i);
            helper(grid, row - 1, i);
        }
        // 把左右边的 陆地变为海水
        for (int i = 0; i < col; i++) {
            helper(grid, i, 0);
            helper(grid, i, col - 1);
        }

        int res = 0;
        // 中间剩下的陆地即是封闭的岛屿
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 找到了陆地 把周围的陆地全部变成海水即可
                if (grid[i][j] == 0) {
                    res++;
                    helper(grid, i, j);
                }
            }
        }
        return res;
    }

    private void helper(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 1) {
            return;
        }
        grid[i][j] = 1;
        helper(grid, i - 1, j);
        helper(grid, i, j - 1);
        helper(grid, i + 1, j);
        helper(grid, i, j + 1);
    }

    /**
     * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
     * <p>
     * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
     * <p>
     * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
     * 输出：3
     * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
     * 示例 2：
     * <p>
     * <p>
     * 输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
     * 输出：0
     * 解释：所有 1 都在边界上或可以到达边界。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/number-of-enclaves
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public int numEnclaves(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        // 把上下的边的 陆地变为海水
        for (int j = 0; j < col; j++) {
            numEnclavesHelper(grid, 0, j);
            numEnclavesHelper(grid, row - 1, j);
        }
        // 把左右边的 陆地变为海水
        for (int i = 0; i < row; i++) {
            numEnclavesHelper(grid, i, 0);
            numEnclavesHelper(grid, i, col - 1);
        }

        int res = 0;
        // 中间剩下的陆地即是封闭的岛屿
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    res += 1;
                }
            }
        }
        return res;
    }

    private void numEnclavesHelper(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        // 把陆地置为海水
        grid[i][j] = 0;
        // 把周围的陆地都变为海水，并统计陆地数量
        numEnclavesHelper(grid, i - 1, j);
        numEnclavesHelper(grid, i, j - 1);
        numEnclavesHelper(grid, i + 1, j);
        numEnclavesHelper(grid, i, j + 1);
    }

    /**
     * 给你两个 m x n 的二进制矩阵 grid1 和 grid2 ，它们只包含 0 （表示水域）和 1 （表示陆地）。一个 岛屿 是由 四个方向 （水平或者竖直）上相邻的 1 组成的区域。任何矩阵以外的区域都视为水域。
     *
     * 如果 grid2 的一个岛屿，被 grid1 的一个岛屿 完全 包含，也就是说 grid2 中该岛屿的每一个格子都被 grid1 中同一个岛屿完全包含，那么我们称 grid2 中的这个岛屿为 子岛屿 。
     *
     * 请你返回 grid2 中 子岛屿 的 数目 。
     *
     *  
     *
     * 示例 1：
     *
     *
     * 输入：grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
     * 输出：3
     * 解释：如上图所示，左边为 grid1 ，右边为 grid2 。
     * grid2 中标红的 1 区域是子岛屿，总共有 3 个子岛屿。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-sub-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        // 当岛屿 B 中所有陆地在岛屿 A 中也是陆地的时候，岛屿 B 是岛屿 A 的子岛
        // grid2[i][j] 是岛屿 但grid1 不是，那肯定不是子岛
        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[0].length; j++) {
                if (grid2[i][j] == 1 && grid1[i][j] == 0) {
                    countSubIslandsHelper(grid2, i, j);
                }
            }
        }
        // 剩下的肯定是子岛了
        int res = 0;
        for (int i = 0; i < grid1.length; i++) {
            for (int j = 0; j < grid1[0].length; j++) {
                if (grid2[i][j] == 1 && grid1[i][j] == 1) {
                    res++;
                    countSubIslandsHelper(grid2, i, j);
                }
            }
        }
        return res;
    }

    // 把某个岛附近的都沉没掉
    private void countSubIslandsHelper(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        countSubIslandsHelper(grid, i - 1, j);
        countSubIslandsHelper(grid, i, j - 1);
        countSubIslandsHelper(grid, i + 1, j);
        countSubIslandsHelper(grid, i, j + 1);

    }

    /**
     * 力扣第 694 题「 不同的岛屿数量」，题目还是输入一个二维矩阵，0 表示海水，1 表示陆地，这次让你计算 不同的 (distinct) 岛屿数量，函数签名如下：
     */
    public int numDistinctIslands(int[][] grid) {
        HashSet<String> set = new HashSet<>();
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    numDistinctIslandsHelper(grid, i, j, sb, 1111);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }

    /**
     * 记录每个节点向四周扩散的顺序
     *
     * @param grid
     * @param i
     * @param j
     * @param sb
     * @param dir
     */
    private void numDistinctIslandsHelper(int[][] grid, int i, int j, StringBuilder sb, int dir) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 1;
        // 前序位置，先记录是通过什么操作进来的
        sb.append(dir).append(",");
        numDistinctIslandsHelper(grid, i - 1, j, sb, 1); // 左
        numDistinctIslandsHelper(grid, i, j - 1, sb, 2); // 下
        numDistinctIslandsHelper(grid, i + 1, j, sb, 3); // 上
        numDistinctIslandsHelper(grid, i, j + 1, sb, 4); // 下
        // 后序位置 记录撤销操作
        sb.append(-dir).append(",");
    }

}
