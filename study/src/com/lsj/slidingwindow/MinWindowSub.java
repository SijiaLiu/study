package com.lsj.slidingwindow;

import java.util.*;

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


    public boolean checkInclusion_1(String s1, String s2) {
        if (s1 == null || s2 == null) {
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
        // 记录符合要求的元素个数
        int valid = 0;
        while (right < s2.length()) {
            char c1 = s2.charAt(right);
            right++;
            // 遇到目标元素才开始窗口统计
            if (targetMap.containsKey(c1)) {
                window.merge(c1, 1, Integer::sum);
                // 某个元素足够了
                if (window.get(c1).equals(targetMap.get(c1))) {
                    valid++;
                }
            }

            // 开始压缩左侧窗口
            while (right - left >= s1.length()) {
                if (valid == targetMap.size()) {
                    return true;
                }
                char c2 = s2.charAt(left);
                if (targetMap.containsKey(c2)) {
                    if (window.get(c2).equals(targetMap.get(c2))) {
                        valid--;
                    }
                    window.merge(c2, -1, Integer::sum);
                }
                left++;
            }
        }

        return false;
    }

    /**
     * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     *
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     *  示例 2:
     *
     * 输入: s = "abab", p = "ab"
     * 输出: [0,1,2]
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/find-all-anagrams-in-a-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s1
     * @param s2
     * @return
     */
    public List<Integer> findAnagrams(String s1, String s2) {
        List<Integer> res = new ArrayList<>();
        if (s1 == null || s2 == null) {
            return res;
        }
        // 记录原字符串中目标字符的数量
        Map<Character, Integer> window = new HashMap<>();
        // 目标字符串中每个字符的数量
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : s2.toCharArray()) {
            targetMap.merge(c, 1, Integer::sum);
        }
        int left = 0;
        int right = 0;
        // 记录符合要求的元素个数
        int valid = 0;
        while (right < s1.length()) {
            char c1 = s1.charAt(right);
            right++;
            // 遇到目标元素才开始窗口统计
            if (targetMap.containsKey(c1)) {
                window.merge(c1, 1, Integer::sum);
                // 某个元素足够了
                if (window.get(c1).equals(targetMap.get(c1))) {
                    valid++;
                }
            }

            // 开始压缩左侧窗口
            while (right - left >= s2.length()) {
                if (valid == targetMap.size()) {
                    res.add(left);
                }
                char c2 = s1.charAt(left);
                if (targetMap.containsKey(c2)) {
                    if (window.get(c2).equals(targetMap.get(c2))) {
                        valid--;
                    }
                    window.merge(c2, -1, Integer::sum);
                }
                left++;
            }
        }
        return res;
    }

    /**
     * 给定两个字符串s1和s2，写一个函数来判断 s2 是否包含 s1的排列。
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


    /**
     * 不含重复字符的最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 记录窗口内每个字符的数量
        Map<Character, Integer> window = new HashMap<>();

        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 窗口增加新的元素
            window.merge(c, 1, Integer::sum);

            // 仔细想想什么时候压缩左窗口
            // 题目要求不能重复的子串，那是不是某个元素在窗口里的数量大于1的时候就不符合了
            // 因为子串是连续的，所以要一直删除 直到删掉那个重复元素为止
            while (window.get(c) > 1) {
                char c1 = s.charAt(left);
                left++;
                window.merge(c1, -1, Integer::sum);
            }
            // 每次窗口里没有重复元素的时候就更新一次结果
            res = Math.max(res, right - left);
        }
        return res;
    }

    /**
     * 给定一个字符串 找出至多包含两个不同字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 3) {
            return s.length();
        }
        int left = 0, right = 0, res = 0;
        // 包含每个字符的窗口 记录每个字符的数量
        Map<Character, Integer> window = new HashMap<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            window.merge(c, 1, Integer::sum);
            right++;
            // 每次 窗口大小符合时都算一次最大值
            if (window.size() == 2) {
                res = Math.max(res, right - left);
            }

            while (window.size() > 2) {
                char c1 = s.charAt(left);
                window.merge(c1, -1, Integer::sum);
                if (window.get(c1) == 0) {
                    window.remove(c1);
                }
                left++;
            }
        }
        return res;
    }

    /**
     * 移除 数组中重复的元素
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 1;
        while (fast < nums.length){
            if (nums[fast] != nums[slow]) {
                nums[++slow] = nums[fast];
            }
            fast ++;
        }
        return slow + 1;
    }

    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int i : nums) {
            list.add(String.valueOf(i));
        }
        list.sort((o1, o2) -> {
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            // 其实就是把两个字符串 加起来 之后比较，大的在前面
            return s2.compareTo(s1);
        });
        StringBuilder sb = new StringBuilder();
        list.forEach(sb::append);
        if (sb.charAt(0) == '0') {
            return "0";
        }
        return sb.toString();
    }
}
