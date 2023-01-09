package com.lsj.algorithm;

import java.util.*;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            Integer integer = map.get(target - nums[i]);
            if (Objects.nonNull(integer)) {
                result[0] = i;
                result[1] = integer;
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }

    public int[] threeSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[3];
        map.put(nums[0], 0);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                Integer integer = map.get(target - nums[i] - nums[j]);
                if (Objects.nonNull(integer) && integer > j) {
                    result[0] = i;
                    result[1] = j;
                    result[2] = integer;
                } else {
                    map.put(nums[j], j);
                }
            }
        }
        return result;
    }

    public List<List<Integer>> threeSums(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        if (length == 0) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < length - 2; i++) {
            // 排序好之后的数字 nums[i] 是最小值
            if (nums[i] > 0) {
                break;
            }
            // 防止中间有重复数字
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = length - 1;
            int target = -nums[i];
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    result.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[k])));
                    j++;
                    while (j < k && nums[j] == nums[j - 1]) {
                        j++;
                    }
                    k--;
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (nums[j] + nums[k] > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return result;
    }

    public List<List<Integer>> threeSum_2(int[] nums, int target) {
        // 需要先排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 先固定第一个元素
            int tmp = nums[i];
            // 转化为 twoSum
            List<List<Integer>> twoSums = twoSum(nums, i + 1, target - tmp);
            for (List<Integer> list : twoSums) {
                list.add(nums[i]);
                res.add(list);
            }
            // 重复的就跳过了，twoSum里面重复的由twoSum自行跳过
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return res;
    }

    /**
     * 寻找从某个坐标开始的数组中符合目标值的元素
     *
     * @param nums
     * @param start
     * @param target
     * @return
     */
    private List<List<Integer>> twoSum(int[] nums, int start, int target) {
        // 起点
        int low = start;
        int hi = nums.length - 1;
        List<List<Integer>> res = new ArrayList<>();
        while (low < hi) {
            int sum = nums[low] + nums[hi];
            // 注意 这里要记录起点的值 不然去重的时候不知道和谁比，因为low是一直变的
            int left = nums[low];
            int right = nums[hi];
            if (sum > target) {
                // 这里去重就是利用了一开始记录的起点值
                while (low < hi && right == nums[hi]) {
                    hi--;
                }
            } else if (sum < target) {
                while (low < hi && left == nums[low]) {
                    low++;
                }
            } else {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(left);
                tmp.add(right);
                res.add(tmp);
                // 这里去重就是利用了一开始记录的起点值
                while (low < hi && nums[low] == left) {
                    low++;
                }
                while (low < hi && nums[hi] == right) {
                    hi--;
                }
            }
        }
        return res;
    }


}
