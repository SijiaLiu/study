package com.lsj.dp;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
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
                max[i] = Math.max(nums[i], max[i -1 ] * nums[i]);
                min[i] = Math.min(nums[i], min[i-1] * nums[i]);
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
}
