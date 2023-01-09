package com.lsj.dp;

public class CuttingRope {

    public int cuttingRope(int n) {
        if (n == 0 || n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                int current = dp[j] * dp[i - j];
                dp[i] = Math.max(current, dp[i]);
            }
        }
        return dp[n];
    }
}
