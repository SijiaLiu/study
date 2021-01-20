package com.lsj.us;

/**
 * 求岛屿数量的并查集
 */
public class LandUnionSet {

    private int[] roots;
    // boss个数
    private int count;
    // boss 权重
    private int[] rank;

    public LandUnionSet(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        roots = new int[row * col];
        rank = new int[row * col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    roots[i * col + j] = i * col + j;
                    rank[i * col + j] = 1;
                    count++;
                }
            }
        }
    }

    public int find(int n) {
        int root = n;
        while (root != roots[root]) {
            root = roots[root];
        }
        // 路径加速
        while (n != roots[n]) {
            int temp = roots[n];
            roots[temp] = root;
            n = temp;
        }
        return root;
    }

    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot) {
            return;
        }
        if (rank[xRoot] > rank[yRoot]) {
            roots[yRoot] = xRoot;
            rank[xRoot] += rank[xRoot];
        } else if (rank[xRoot] < rank[yRoot]) {
            roots[xRoot] = yRoot;
            rank[yRoot] += rank[yRoot];
        } else {
            roots[xRoot] = yRoot;
            rank[yRoot] += rank[yRoot];
        }
        count--;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
