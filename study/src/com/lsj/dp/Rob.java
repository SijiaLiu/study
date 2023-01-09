package com.lsj.dp;

public class Rob {

    /**
     * 打家劫舍 收尾相连
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        // dp[i] 表示nums[1,i]房间里抢劫的最大的收益
        int len = nums.length;
        return Math.max(dp(0, len - 1, nums), dp(1, len, nums));
    }

    /**
     * 在start - end 之间抢劫的最大收益
     *
     * @param start 抢劫的起点
     * @param end   抢劫的末尾
     * @param nums
     * @return
     */
    private int dp(int start, int end, int[] nums) {
        int[] dp = new int[end];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i < end; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[end - 1];
    }
}
