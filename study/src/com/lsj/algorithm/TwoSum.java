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
}
