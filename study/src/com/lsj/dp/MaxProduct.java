package com.lsj.dp;

/**
 * 给你一个整数数组 nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxProduct {

    public int maxProduct(int[] nums) {
        int length = nums.length;
        // max[i] 代表使用第i位数字的 最大的子序列的乘积
        int[] max = new int[length];
        int[] min = new int[length];
        max[0] = nums[0];
        min[0] = nums[0];
        for (int i = 1; i < length; i++) {
            if (nums[i] >= 0) {
                // nums[i] 是正数，最大值等于之前的最大值乘以当前值
                max[i] = Math.max(nums[i], max[i - 1] * nums[i]);
                min[i] = Math.min(nums[i], min[i - 1] * nums[i]);
            } else {
                // 当前值是负数，最大值等于之前的最小值乘以当前值
                // 当前值是负数，最小值等于之前最大值乘以当前值
                max[i] = Math.max(nums[i], min[i - 1] * nums[i]);
                min[i] = Math.min(nums[i], max[i - 1] * nums[i]);
            }
        }
        int res = max[0];
        for (int value : max) {
            if (value > res) {
                res = value;
            }
        }
        return res;
    }

    public int maxValue(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 1; i < row; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < col; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                grid[i][j] += Math.max(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[row - 1][col - 1];
    }


    /**
     * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
     *
     *  
     *
     * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: 1
     * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
     * 示例 2:
     *
     * 输入: 2
     * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
     *  
     *
     * 限制：
     *
     * 1 <= n <= 11
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public double[] dicesProbability(int n) {
        // 每个骰子只可能出现1-6
        // 题目要求概率，那就算出n个骰子后加起来的值的和出现的次数 然后出所有的可能次数6的n次方即可
        // 比如 2 个骰子 那就可能出现 2,3。。。。12
        // 定义一个函数 f(i, j)表示 i个骰子，和是 j 的次数
        // 那么f(2, 5) 等于多少，(f(1,1) + f(1,4)) + (f(1,2) + f(1,3)) ?
        // 其实很好理解 对于第i个骰子 出现j值的 次数 等于 f(i-1, j - 1) + f(i-1, j - 2) +..+f(i-1, j - 5)
        // 最大值是6n
        int[][] dp = new int[n + 1][6*n + 1];
        // base case 一个骰子时 点数和出现的次数
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }

        // n 个骰子
        for (int i = 2; i < n + 1; i++) {
            // 可能出现的和
            for (int j = i; j < 6 * i + 1; j++) {
                for (int k = 1; k <= 6 && k <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }
        // 为什么是 6 * n - n + 1 ? n 个骰子的最小和是n
        double[] ans = new double[6 * n - n + 1];
        for (int i = n; i < 6 * n; i++) {
            // 注意这里i - n, 因为ans 数组 只有 6 * n - n + 1 个空格
            ans[i - n] = dp[n][i] / Math.pow(6, n);
        }
        return ans;
    }

    /**
     * 跳跃游戏
     * 
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int max = nums[0];
        for (int i = 0; i <= max; i++) {
            max = Math.max(max, nums[i] + i);
            if (max >= len - 1) {
                return true;
            }
        }
        return false;
    }
}
