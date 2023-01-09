package com.lsj.dp;

public class BackPack {

    /**
     * 01 背包问题 N件物品 W容量的背包 每件物品对应的容量和价值不一样 求背包能装的最大价值 不能重复放物品
     *
     * @param w
     * @param v
     * @param W
     * @return
     */
    public int backpack01(int[] w, int[] v, int W) {
        int wLength = w.length;
        // dp[i][j] 表示对于前i个物品 放入容量为j的背包 中的最大价值
        int[][] dp = new int[wLength + 1][W + 1];
        // base case 可省略
        for (int i = 0; i <= wLength; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= W; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= wLength; i++) {
            for (int j = 1; j <= W; j++) {
                if (j >= w[i - 1]) {
                    // 可放 可不放
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + v[i - 1]);
                } else {
                    // 当前物品无法放入背包
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[wLength][W];
    }

    /**
     * 零钱兑换 从硬币列表中凑出目标值amount的所有可能数
     * 完全背包问题 N件物品 可用无限次 求有多少种方法恰好装好容量为amount的背包
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int length = coins.length;
        if (length == 0 || amount == 0) {
            return 0;
        }
        // dp[i][j] 表示对于前i个硬币，凑成j值的可能组合数
        int[][] dp = new int[length + 1][amount + 1];
        // 凑成0元只有一种办法 就是不选硬币
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i <= amount; i++) {
            dp[0][i] = 0;
        }
        // 小标从一开始 所以下面要用i-1
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i - 1]) {
                    // 当前硬币 可以选或者不选 所以结果应该是两者相加
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    // 当前硬币选不了
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[length][amount];
    }

    /**
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * 注意:
     *
     * 每个数组中的元素不会超过 100
     * 数组的大小不会超过 200
     * 示例 1:
     *
     * 输入: [1, 5, 11, 5]
     *
     * 输出: true
     *
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     *  
     *
     * 示例 2:
     *
     * 输入: [1, 2, 3, 5]
     *
     * 输出: false
     *
     * 解释: 数组不能分割成两个元素和相等的子集.
     * @param nums
     * @return
     */
    public boolean slidingArray(int[] nums) {
        int length = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        // dp[i][j] 表示对于前i个数字，凑成j值的可能性
        boolean[][] dp = new boolean[length + 1][target + 1];
        // 凑成0只有一种办法 就是不选任何数字
        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= length; i++) {
            for (int j = target; j >= nums[i - 1]; j--) {
                // 前i-1个数字中如果能凑成目标值，则前n(n>i-1)值肯定可以凑成目标值
                // 前i-1个数字中如果能凑成target-nums[i-1],则加上nums[i-1],刚好能凑成目标值
                dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[length][target];
    }

    /**
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if (nums == null) {
            return false;
        }
        int length = nums.length;
        if (length % 2 != 0) {
            return false;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        sum = sum / 2;

        // dp[i][j] 表示对于 第 i 件物品 放入j容量的背包 是否刚好能装满
        boolean[][] dp = new boolean[length + 1][sum + 1];
        // base case 背包容量为0 一直不放就能行
        for (int i = 0; i <= length; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j < nums[i - 1]) {
                    // 背包容量小 装不进去，只能不装
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 装或者不装
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[length][sum];
    }

    /**
     * 状态压缩
     *
     * @param nums
     * @return
     */
    public boolean slidingArray02(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        // dp[i] 表示对于输入的数组 凑成i值的可能性
        boolean[] dp = new boolean[target + 1];
        // base case
        dp[0] = true;
        for (int num : nums) {
            // 必须从大到小遍历 只要小于目标值j 的 dp[j-nums[i]] 为true 则无论nums[i]怎么变化 永远都能够凑成目标值
            // 举个列子 如果从小到大遍历 加入target = 4, num[0] = 1, 则dp[0-4] 都为true
            for (int j = target; j >= num; j--) {
                if (dp[j - num]) {
                    dp[j] = true;
                }
            }
        }
        return dp[target];
    }
}
