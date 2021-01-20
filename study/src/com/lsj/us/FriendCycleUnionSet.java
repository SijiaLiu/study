package com.lsj.us;

/**
 * 朋友圈并查集
 */
public class FriendCycleUnionSet {

    private int[] roots;
    // boss个数
    private int count;
    // boss 权重
    private int[] rank;

    public FriendCycleUnionSet(int[][] cycle) {
        int row = cycle.length;
        roots = new int[row];
        rank = new int[row];
        for (int i = 0; i < row; i++) {
            roots[i ] = i;
            rank[i] = 1;
            count++;
        }
    }

    public int find(int n) {
        int root = n;
        while (root != roots[root]) {
            root = roots[root];
        }
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
