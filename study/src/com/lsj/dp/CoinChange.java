package com.lsj.dp;

public class CoinChange {


    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     *
     * @param amount
     * @param coins
     * @return
     */
    public int minChangeCount(int amount, int[] coins) {
        if (amount == 0 || coins.length == 0) {
            return 0;
        }
        // dp[i] 表示 换算成 i 值时最小的兑换次数
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            // 临时变量 标识使用某一种coin时的最小使用次数，减去amount的原因是担心dp[i-coin] + 1 由最大值变成负数
            int minAmount = Integer.MAX_VALUE - amount;
            // 循环硬币列表 使用不同的硬币 兑换次数会变化 取最小的
            for (int coin : coins) {
                if (i >= coin) {
                    minAmount = Math.min(minAmount, dp[i - coin] + 1);
                }
            }
            // 当需要兑换的数较小时，即i<coin[0], 用接近最大的整型数字标识。后续三元表达式替换
            dp[i] = minAmount;
        }
        // 没有符合的兑换规则时 dp[amount] 是一个很大的值。
        return dp[amount] > amount ? -1 : dp[amount];
    }

    /**
     * 零钱兑换的次数，可以使用无数次硬币
     * @param amount 目标金额
     * @param coins 硬币列表
     * @return
     */
    public int changePath(int amount, int[] coins) {
        if (amount == 0 || coins.length == 0) {
            return 0;
        }
        // dp[i] 表示使用任意个硬币 组成i的组合数
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        // 对于前 i 个硬币 组合成目标值的个数
        for (int coin : coins) {
            // 目标值要大于硬币才开始算
            for (int x = coin; x <= amount; ++x) {
                // 轮询都某一个硬币时 我们可以选择使用这个硬币 或者 不使用这个硬币
                // 使用这个硬币 组合数为 dp[x-coin]，因为要使用当前硬币，硬币值为coin 所以组合数和dp[x-coin]一致
                // 不使用这个硬币 组合数为 dp[x] 此时的dp[x] 表示的是轮询到前一个硬币时，凑出目标值x的最大组合数
                // 所以最终的组合数为两者相加
                dp[x] += dp[x - coin];
            }
        }
        return dp[amount];
    }
}
