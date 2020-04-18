package com.lsj.dfs;

import java.util.LinkedList;
import java.util.Queue;

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

    public int numLandsBfs(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int ans = 0;
        int row = grid.length;
        // 必须使用列
        int col = grid[0].length;
        int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    ++ans;
                    grid[i][j] = '0';
                    int q = i * col + j;
                    queue.add(q);
                    while (!queue.isEmpty()) {
                        Integer top = queue.remove();
                        int x = top / col;
                        int y = top % col;
                        for (int[] ints : dir) {
                            int newRow = x + ints[0];
                            int newCol = y + ints[1];
                            if (newRow >= 0 && newRow < row && newCol >= 0 && newCol < col && grid[newRow][newCol] == '1') {
                                queue.add(newRow * col + newCol);
                                grid[newRow][newCol] = '0';
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
}
