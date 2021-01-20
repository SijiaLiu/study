package com.lsj.dfs;

public class FindTargetSumWays {

    public int findTargetSumWays(int[] nums, int S) {
        int res = 0;
        for (int i = 1; i < 3; i++) {
            res += dfs(nums, 0, 0, S, i);
        }
        return res;
    }

    private int dfs(int[] nums, int i, int post, int target, int symbolType) {
        if (i >= nums.length) {
            return 0;
        }
        if (i == nums.length - 1 && (symbolType == 1 && post + nums[i] == target
                || symbolType == 2 && post - nums[i] == target)) {
            return 1;
        }
        if (symbolType == 1) {
            post = post + nums[i];
        }
        if (symbolType == 2) {
            post = post - nums[i];
        }
        int dfs = 0;
        for (int j = 1; j < 3; j++) {
            dfs += dfs(nums, i + 1, post, target, j);
        }
        return dfs;
    }
}
