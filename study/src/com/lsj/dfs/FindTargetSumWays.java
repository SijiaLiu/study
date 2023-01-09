package com.lsj.dfs;

public class FindTargetSumWays {

    /**
     * 给你一个整数数组 nums 和一个整数 target 。
     *
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     *
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,1,1,1,1], target = 3
     * 输出：5
     * 解释：一共有 5 种方法让最终目标和为 3 。
     * -1 + 1 + 1 + 1 + 1 = 3
     * +1 - 1 + 1 + 1 + 1 = 3
     * +1 + 1 - 1 + 1 + 1 = 3
     * +1 + 1 + 1 - 1 + 1 = 3
     * +1 + 1 + 1 + 1 - 1 = 3
     * 示例 2：
     *
     * 输入：nums = [1], target = 1
     * 输出：1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/target-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, 0, target);
    }

    private int dfs(int[] nums, int i, int target) {
        if (i > nums.length) {
            return 0;
        }
        if (i == nums.length) {
            if (target == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        int dfs = 0;
        for (int j = 1; j < 3; j++) {
            int tmp = j == 1 ? target - nums[i] : target + nums[i];
            dfs += dfs(nums, i + 1, tmp);
        }
        return dfs;
    }
}
