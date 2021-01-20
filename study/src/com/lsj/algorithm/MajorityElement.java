package com.lsj.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 众数
 */
public class MajorityElement {

    /**
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
     *
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/majority-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.merge(nums[i], 1, Integer::sum);
        }

        List<Map.Entry<Integer,Integer>> list = new ArrayList(map.entrySet());
        list.sort((o1, o2) -> (o2.getValue() - o1.getValue()));
        return list.get(0).getKey();
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{2, 2, 1}));
        System.out.println(majorityElements(new int[]{2, 2, 1, 2, 2, 3, 3, 3, 3}));

    }

    /**
     * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     *
     * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
     * @param nums
     *
     * 摩尔根投票法
     * @return
     */
    public static List<Integer> majorityElements(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        // 给候选人统计票数
        int candidate1 = nums[0];
        int count1 = 0;
        int candidate2 = nums[0];
        int count2 = 0;
        for (int i : nums) {
            // 遇到候选1 计数加一
            if (i == candidate1) {
                count1++;
                continue;
            }
            // 遇到候选2 计数加一
            if (i == candidate2 ) {
                count2++;
                continue;
            }

            // 遇到了候选3
            // 若此时 候选1没票数了 把候选3换成换选1
            if (count1 == 0) {
                candidate1 = i;
                count1++;
                continue;
            }
            // 若此时 候选2没票数了 把候选3换成换选2
            if (count2 == 0) {
                candidate2 = i;
                count2++;
                continue;
            }
            // 此时候选1和候选2都还有票 但他们遇到了候选3 需要把自己的票减一
            count1--;
            count2--;
        }

        // 计数阶段
        // 找到了两个候选人之后，需要确定票数是否满足大于 N/3
        count1 = 0;
        count2 = 0;
        for (int num : nums) {
            if (candidate1 == num) count1++;
            else if (candidate2 == num) count2++;
        }


        if (count1 > nums.length / 3) {
            res.add(candidate1);
        }
        if (count2 > nums.length / 3) {
            res.add(candidate2);
        }
        return res;
    }

}
