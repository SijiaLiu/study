package com.lsj.dp;

/**
 * 股票买卖问题
 */
public class Stock {

    /**
     * 给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 注意：你不能在买入股票前卖出股票。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
     *
     * @param prices 股票价格 只允许买卖一次
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int res = 0;
        // 最低价
        int least = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < least) {
                // 拿到最低价
                least = prices[i];
            } else {
                // 当前最大利润和之前的最大利润作比较
                res = Math.max(res, prices[i] - least);
            }
        }

        return res;
    }

    /**
     * 给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
     *
     * @param prices 允许多次买卖的股票价格
     * @return
     */
    public int maxProfit1(int[] prices) {
        int length = prices.length;
        // mp[i][j] j=0标识空仓 1标识满仓 i标识天数 mp[i][j] 标识第i天空仓或满仓的最大利润
        int[][] mp = new int[length][2];
        mp[0][0] = 0;
        mp[0][1] = -prices[0];
        for (int i = 1; i < length; i++) {
            // 当天空仓，
            // 1、要么是前一天空仓，今天不买不卖
            // 2、今天卖出，今天卖出的后的最大利润等于今天的价格加上之前满仓的最大利润
            mp[i][0] = Math.max(mp[i - 1][0], prices[i] + mp[i - 1][1]);
            // 当天满仓，
            // 1、前一天满仓，今天不买不卖
            // 2、今天买入，今天买入的后的最大利润等于之前满仓的最大利润减去今天的价格
            mp[i][1] = Math.max(mp[i - 1][1], mp[i - 1][0] - prices[i]);
        }
        return mp[length - 1][0];
    }

    /**
     * 可以买卖多次
     *
     * @param prices
     * @return
     */
    public int maxProfit11(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int res = 0;
        // 一个原则 只要后一天的价格比前一天的价格高 就买 然后第二天卖 贪心
        for (int i = 1; i < prices.length; i++) {
            res += prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0;
        }
        return res;
    }

    /**
     * 买卖k次
     *
     * @param prices
     * @param K
     * @return
     */
    public int maxProfit(int[] prices, int K) {
        int length = prices.length;
        // mp[i][j][k] j=0标识空仓 1标识满仓 i标识天数 mp[i][j] 标识第i天空仓或满仓的最大利润，K标识最多可以交易的次数，买入算一次交易
        int[][][] mp = new int[length][2][K + 1];
        // 第一天 满仓 没有交易 不可能有收益
        mp[0][1][0] = -Integer.MAX_VALUE;
        // 第一天 满仓 一笔交易 收益为负数
        mp[0][1][1] = -prices[0];
        // 第二天开始计算
        for (int i = 1; i < length; i++) {
            for (int k = 1; k <= K; k++) {
                if (i < k) {
                    mp[i][0][k] = -Integer.MAX_VALUE;
                    mp[i][1][k] = -Integer.MAX_VALUE;
                } else {
                    mp[i][0][k] = Math.max(mp[i - 1][0][k], mp[i - 1][1][k] + prices[i]);
                    mp[i][1][k] = Math.max(mp[i - 1][1][k], mp[i - 1][0][k - 1] - prices[i]);
                }
                System.out.println("i: " + i + " j: " + 0 + " k: " + k + " value: " + mp[i][0][k]);
                System.out.println("i: " + i + " j: " + 1 + " k: " + k + " value: " + mp[i][1][k]);
                System.out.println();
            }
        }
        int res = mp[length - 1][0][0];
        for (int i = 1; i <= K; i++) {
            res = Math.max(res, mp[length - 1][0][i]);
        }
        return res;
    }


    /**
     * i 表示天数，k 表示最大交易次数 0代表没有股票，1代表持有股票
     * dp[i][k][0] = max(dp[i-1][k][0], price[i] + dp[i-1][k][1])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - price[i])
     */

    /**
     * 给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
     * <p
     *
     * @param prices
     * @return
     */
    public int maxProfit_1(int[] prices) {
        // 题目说只能买一次 所以k = 1 忽略一维
        int length = prices.length;
        int[][] dp = new int[length][2];
        for (int i = 0; i < length; i++) {
            // base case
            if (i - 1 == -1) {
                // dp[-1][0] = Math.max(dp[-1][0], dp[-1][1] + prices[i])
                // 第1天，没有股票的利润是0，持有股票是负无穷
                dp[i][0] = 0;
                // 第1天，有股票的利润是-prices[i]
                dp[i][1] = -prices[i];
                continue;
            }
            // 第i天没有股票，可能是前一天就没有股票，也可能是昨天持有，今天卖掉，今天卖掉加上今天的价格
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 第i天有股票，可能是前一天就有股票，也可能是昨天没有，今天买入，今天买入减去今天的价格
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return dp[length - 1][0];
    }

    /**
     * 可以无限次买卖
     *
     * @param prices
     * @return
     */
    int maxProfit_k_inf(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                // base case
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * 卖出之后 需隔一天才能买入
     *
     * @param prices
     * @return
     */
    int maxProfit_with_cool(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                // base case 1
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            if (i - 2 == -1) {
                // base case 2
                // 其实就是分析一下 dp[1][0] 和 dp[1][1] 等于多少
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                // i - 2 小于 0 时根据状态转移方程推出对应 base case
                dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
                //   dp[i][1]
                // = max(dp[i-1][1], dp[-1][0] - prices[i])
                // = max(dp[i-1][1], 0 - prices[i])
                // = max(dp[i-1][1], -prices[i])
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 因为有冷冻期 所以当你打算买入的时候，要从 i-2 天 取未持有股票的最大值
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    /**
     * 接雨水
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int l_max = 0, r_max = 0;
        int res = 0;
        while (left < right) {

            // l_max 代表 height[0, left] 中最高的柱子
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            // 两个指针往中间走的过程中，坐标最高的比右边最高的小，那说明左边当前位置能接的水肯定是以左边最高的柱子作为边界了，所以直接计算
            if (l_max < r_max) {
                res += Math.max(l_max, height[left]) - height[left];
                left++;
            } else {
                res += Math.max(r_max, height[right]) - height[right];
                right--;
            }
        }
        return res;
    }
}
