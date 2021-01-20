package com.lsj.dp;

/**
 * 高楼扔鸡蛋
 */
public class ThrowEggs {

    /**
     * 你面前有一栋从 1 到 N 共 N 层的楼，然后给你 K 个鸡蛋（K 至少为 1）。
     * 现在确定这栋楼存在楼层 0 <= F <= N，在这层楼将鸡蛋扔下去，鸡蛋恰好没摔碎（高于 F 的楼层都会碎，低于 F 的楼层都不会碎）。
     * 现在问你，最坏情况下，你至少要扔几次鸡蛋，才能确定这个楼层 F 呢？
     * @param k k个鸡蛋
     * @param n n层楼
     * @return
     */
    public int throwEggs(int k, int n) {
        if (k == 0 || n == 0) {
            return 0;
        }
        int[][] dp = new int[k + 1][n + 1];
        int res = dpBinary(k, n, dp);
        for (int[] i : dp) {
            for (int j : i) {
                System.out.print(j + ",");
            }
            System.out.println();
        }
        return res;
    }

    /**
     * @param k
     * @param n
     * @param dp dpTable
     * @return
     */
    private int dp(int k, int n, int[][] dp) {
        if (k == 1) {
            return n;
        }
        if (n == 0) {
            return 0;
        }
        if (dp[k][n] != 0) {
            return dp[k][n];
        }
        int res = Integer.MAX_VALUE;
        // 在第i层楼扔鸡蛋
        for (int i = 1; i <= n; i++) {
            // 最少扔鸡蛋次数 min
            res = Math.min(res,
                    // 第i层楼扔鸡蛋扔鸡蛋 有两种可能，
                    // 鸡蛋没碎，还剩下k个鸡蛋，则需要试验的楼层在i楼之上，n-i 层楼
                    // 鸡蛋碎了，还剩下k-1个鸡蛋，则需要试验的楼层在i楼之下，i-1 层楼
                    // 要求最坏情况下 所以用max
                    Math.max(dp(k, n - i, dp), dp(k - 1, i - 1, dp)) + 1);
        }
        dp[k][n] = res;
        return res;
    }

    private int dpBinary(int k, int n, int[][] dp) {
        if (k == 1) {
            return n;
        }
        if (n == 0) {
            return 0;
        }
        if (dp[k][n] != 0) {
            return dp[k][n];
        }
        int lo = 0, hi = n;
        int res = Integer.MAX_VALUE;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            // 当 k 和 n 固定时，broken 和 notBroken 是关于mid的函数
            // broken关于mid单调递增
            // notBroken 关于mid单调递减
            // 当他们相等时 即是结果
            int broken = dp(k - 1, mid - 1, dp);
            int notBroken = dp(k, n - mid, dp);
            if (broken > notBroken) {
                hi = mid - 1;
                res = Math.min(res, broken + 1);
            } else {
                lo = mid + 1;
                res = Math.min(res, notBroken + 1);
            }
        }
        dp[k][n] = res;
        return dp[k][n];
    }
}
