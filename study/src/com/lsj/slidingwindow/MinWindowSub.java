package com.lsj.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MinWindowSub {

    /**
     * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
     *
     * 示例：
     *
     * 输入: S = "ADOBECODEBANC", T = "ABC"
     * 输出: "BANC"
     * 说明：
     *
     * 如果 S 中不存这样的子串，则返回空字符串 ""。
     * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-window-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param resource
     * @param target
     * @return
     */
    public String minWindowSub(String resource, String target) {

        if (resource == null || target == null) {
            return "";
        }
        // 记录原字符串中目标字符的数量
        Map<Character, Integer> window = new HashMap<>();
        // 目标字符串中每个字符的数量
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : target.toCharArray()) {
            targetMap.merge(c, 1, Integer::sum);
        }
        String res = "";
        int left = 0;
        int right = 0;
        int valid = 0;
        int len = resource.length() + 1;
        // 右边窗口未出界 轮询
        while (right < resource.length()) {
            char r = resource.charAt(right);
            right++;
            // 只记录目标字符的个数
            if (targetMap.containsKey(r)) {
                window.merge(r, 1, Integer::sum);
                // 注意 Window里面目标字符的个数可以大于targetMap中目标字符的个数 但只有当他们刚好相等的时候 valid加一
                if (window.get(r).equals(targetMap.get(r))) {
                    valid++;
                }
            }
            // 找到了可行解 接下来通过压缩左边界 找最优解
            while (valid == targetMap.size()) {
                if (len > right - left) {
                    len = right - left;
                    res = resource.substring(left, right);
                }
                // 先把左边界对应的值取出来
                char l = resource.charAt(left);
                // 左边界向右靠拢
                left++;
                // 若左边界的对应的字符是目标字符
                if (targetMap.containsKey(l)) {
                    // 如果当前字符原来是符合要求，由于当前窗口左移，所以当前窗口不符合了
                    if (window.get(l).equals(targetMap.get(l))) {
                        valid--;
                    }
                    // 窗口中的目标字符个数减一
                    window.merge(l, -1, Integer::sum);
                }

            }
        }
        return res;
    }

    /**
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     *
     * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
     *
     * 示例1:
     *
     * 输入: s1 = "ab" s2 = "eidbaooo"
     * 输出: True
     * 解释: s2 包含 s1 的排列之一 ("ba").
     *  
     *
     * 示例2:
     *
     * 输入: s1= "ab" s2 = "eidboaoo"
     * 输出: False
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/permutation-in-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }
        // 记录原字符串中目标字符的数量
        Map<Character, Integer> window = new HashMap<>();
        // 目标字符串中每个字符的数量
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : s1.toCharArray()) {
            targetMap.merge(c, 1, Integer::sum);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s2.length()) {
            char r = s2.charAt(right);
            right++;
            // 只记录目标字符的个数
            if (targetMap.containsKey(r)) {
                window.merge(r, 1, Integer::sum);
                // 注意 Window里面目标字符的个数可以大于targetMap中目标字符的个数 但只有当他们刚好相等的时候 valid加一
                if (window.get(r).equals(targetMap.get(r))) {
                    valid++;
                }
            }
            if (right - left >= s1.length()) {
                if (valid == targetMap.size()) {
                    return true;
                }
                // 先把左边界对应的值取出来
                char l = s2.charAt(left);
                // 左边界向右靠拢
                left++;
                // 若左边界的对应的字符是目标字符
                if (targetMap.containsKey(l)) {
                    // 如果当前字符原来是符合要求，由于当前窗口左移，所以当前窗口不符合了
                    if (window.get(l).equals(targetMap.get(l))) {
                        valid--;
                    }
                    // 窗口中的目标字符个数减一
                    window.merge(l, -1, Integer::sum);
                }
            }

        }
        return false;
    }

}
