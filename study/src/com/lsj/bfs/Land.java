package com.lsj.bfs;

import com.lsj.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class Land {

    /**
     * 计算岛屿的数量
     * 这个解法也可以，但和dfs很像，或者说就是dfs 因为本质是染色，改变了原数组
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

    /**
     * 这才是bfs的正确用法
     *
     * @param grid
     * @return
     */
    public int numLandsBfs1(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int rowLength = grid.length;
        int colLength = grid[0].length;
        // 辅助数组 用来标记某个点是否被访问过
        boolean[][] visited = new boolean[rowLength][colLength];
        int[][] dir = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Queue<Integer> queue = new LinkedList<>();
        int land = 0;
        for(int i = 0; i < rowLength; i++) {
            for(int j = 0; j < colLength; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    land++;
                    visited[i][j] = true;
                    queue.add(i * colLength + j);
                    while(!queue.isEmpty()) {
                        int q = queue.poll();
                        int row = q / colLength;
                        int col = q % colLength;
                        for (int[] ints : dir) {
                            int newRow = row + ints[0];
                            int newCol = col + ints[1];
                            if (newRow >= 0 && newRow < rowLength
                                    && newCol >= 0 && newCol < colLength
                                    && !visited[newRow][newCol]
                                    && grid[newRow][newCol] == '1') {
                                queue.add(newRow * colLength + newCol);
                                visited[newRow][newCol] = true;
                            }
                        }
                    }
                }
            }
        }
        return land;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                if (i == size - 1) {
                    res.add(poll.val);
                }
            }
        }

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        return res;
    }
}
