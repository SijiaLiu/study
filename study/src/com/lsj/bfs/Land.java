package com.lsj.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class Land {

    /**
     * 计算岛屿的数量
     *
     * BFS
     * @param grid
     * @return
     */
    public int numLandsBfs(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int rowLength = grid.length;
        int colLength = grid[0].length;
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<Integer> queue = new LinkedList<>();
        int land = 0;
        for(int i = 0; i < rowLength; i++) {
            for(int j = 0; j < colLength; j++) {
                if(grid[i][j] == '1') {
                    land++;
                    grid[i][j] = '0';
                    queue.add(i * colLength + j);
                    while(!queue.isEmpty()) {
                        int q = queue.poll();
                        int row = q / colLength;
                        int col = q % colLength;
                        for (int[] ints : dir) {
                            int newRow = row + ints[0];
                            int newCol = col + ints[1];
                            if (newRow >= 0 && newRow < rowLength && newCol >= 0 && newCol < colLength && grid[newRow][newCol] == '1') {
                                queue.add(newRow * colLength + newCol);
                                grid[newRow][newCol] = '0';
                            }
                        }
                    }
                }
            }
        }
        return land;
    }
}
