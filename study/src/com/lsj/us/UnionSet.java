package com.lsj.us;

/**
 * 并查集
 */
public class UnionSet {

    private int[] roots;

    public UnionSet(int N) {
        roots = new int[N];
        // 初始化 每个元素的上级都是自己
        for (int i = 0; i < N; i++) {
            roots[i] = i;
        }
    }

    public int unionSearch(int n) {
        int root = n;
        // 当前值的上级不是本身 则一层层的找上去 当root等于roots[root]时 root即是大boss
        while (root != roots[root]) {
            root = roots[root];
        }
        // 压缩路径 把n的上级 全部指向大boss
        while (roots[n] != n) {
            int temp = roots[n];
            roots[n] = root;
            n = temp;
        }
        return root;
    }

    public boolean connected(int p, int q) {
        return unionSearch(p) == unionSearch(q);
    }

    public void union(int p, int q) {
        int pRoot = unionSearch(p);
        int qRoot = unionSearch(q);
        roots[pRoot] = qRoot;
    }
}
