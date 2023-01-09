package com.lsj.backtrack;

import java.util.*;

public class LetterCombinations {

    List<String> res = new ArrayList<>();
    Map<Character, List<Character>> map = new HashMap<>();
    StringBuilder path = new StringBuilder();
    public List<String> letterCombinations_2(String digits) {
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('x', 'y', 'z', 'w'));
        helper(digits, 0);
        return res;
    }

    private void helper(String s, int start) {
        if (start >= s.length()) {
            return;
        }
        if (path.length() == s.length()) {
            res.add(path.toString());
            return;
        }
        char c = s.charAt(start);
        List<Character> strings = map.get(c);
        for (int i = 0; i < strings.size(); i++) {
            path.append(strings.get(i));
            helper(s, start + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }

    /**
     * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     *
     *
     *  
     *
     * 示例 1：
     *
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * 示例 2：
     *
     * 输入：digits = ""
     * 输出：[]
     * 示例 3：
     *
     * 输入：digits = "2"
     * 输出：["a","b","c"]
     *  
     *
     * 提示：
     *
     * 0 <= digits.length <= 4
     * digits[i] 是范围 ['2', '9'] 的一个数字。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() < 1) {
            return res;
        }
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('x', 'y', 'z', 'w'));

        helper(res, new ArrayList<>(), map, digits.toCharArray(), 0);
        return res;
    }

    private void helper(List<String> res, List<Character> cur, Map<Character, List<Character>> map, char[] chars, int n) {
        if (n >= chars.length) {
            StringBuilder sb = new StringBuilder();
            for (Character c : cur) {
                sb.append(c);
            }
            res.add(sb.toString()) ;
            return;
        }

        for (int i = 0; i < map.get(chars[n]).size(); i++) {
            cur.add(map.get(chars[n]).get(i));
            helper(res, cur, map, chars, n + 1);
            cur.remove(cur.size() - 1);
        }
    }

    /**
     * 查询和等于目标值的所有数字组合，数字可以重复使用
     *
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(res, cur, 0, candidates, target);
        return res;
    }

    private void helper(List<List<Integer>> res, List<Integer> cur, int start, int[] candidates, int target) {
        // 目标值会一直减小，小于0说明不符合，因为原始数组中所有的都是正整数
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }
        // 数字可以重复使用，所有查询起点不变
        for (int i = start; i < candidates.length; i++) {
            cur.add(candidates[i]);
            helper(res, cur, i, candidates, target - candidates[i]);
            cur.remove(cur.size() - 1);
        }
    }

    /**
     * 合并区间
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        // 排序二维数组，优先比较第一位
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        // index 从1开始，方便改原数组
        int index = 1;
        int len = intervals.length;
        for(int i = 1; i < len;i++) {
            // 被比较的区间，后面新区间都和这个区间比较
            int[] arr = intervals[index - 1];
            if(intervals[i][0] > arr[1]) {
                // 新区间的起始值 大于被比较区间的末尾值，说明没有重叠，被比较区间往前推进，以后都拿新区间去比较
                intervals[index++] = intervals[i];
            } else {
                // 新区间的起始值小于或等于被比较区间的末尾值，则考虑合并，合并后的区间取这两个区间的末尾值的较大值
                arr[1] = Math.max(intervals[i][1], arr[1]);
            }
        }
        // 原数组已经改变了，索引位置向前推进了多少，就取多少
        return Arrays.copyOf(intervals, index);
    }

    public int lastRemaining(int n, int m) {
        int len = n;
        int tmp = 0;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }
        while (len > 1) {
            tmp = (tmp + m + len - 1) % len;
            array[tmp] = 0;
            len --;
        }
        return array[tmp];
    }

}
