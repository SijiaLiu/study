package com.lsj.backtrack;

public class FindTarget {

    /**
     * 给定一个正整数数组 nums 和一个整数 target 。
     *
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     *
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/YaVDxD
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    int pathSum = 0;
    public int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 0) return 0;
        return helper(nums, 0, target);
    }

    /**
     * 给 index 位置加 + 或者 - 的结果
     *
     * @param nums
     * @param index
     * @param target
     * @return
     */
    private int helper(int[] nums, int index, int target) {
        if (index == nums.length && target == pathSum) {
            // 说明所有位置都加好了，并且结果和目标值相等 那就找到了一种结果
            return 1;
        }
        // 越界了 不算
        if (index >= nums.length) {
            return 0;
        }
        // 每个位置 都可以用 + 或者 - 要统计 两种选择带来的可能性
        int res = 0;

        // 用加号拿到的结果
        pathSum += nums[index];
        int add = helper(nums, index + 1, target);
        pathSum -= nums[index];
        // 用减号拿到的结果
        pathSum -= nums[index];
        int sub = helper(nums, index + 1, target);
        pathSum += nums[index];
        // 结果相加
        res = add + sub;
        return res;
    }
}
