package com.lsj.backtrack;

import java.util.ArrayList;
import java.util.List;

public class SubSet {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums.length < 1) {
            return res;
        }
        helper(res, new ArrayList(), nums, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> cur, int[] nums, int index) {
        if(index == nums.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        cur.add(nums[index]);
        helper(res, cur, nums, index + 1);
        cur.remove(cur.size() - 1);
        helper(res, cur, nums, index + 1);
    }
}
