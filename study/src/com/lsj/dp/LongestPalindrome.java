package com.lsj.dp;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindrome {

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * <p>
     * 示例 1：
     * <p>
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     * <p>
     * 输入: "cbbd"
     * 输出: "bb"
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int start = 0;
        int maxLen = 1;
        int length = s.length();
        // dp[i][j] 表示字符串s[i, j] 是否是回文串
        boolean[][] dp = new boolean[length][length];
        // 单个字符 肯定是回文串
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        // 因为dp[i][j] 依赖于dp[i+1][j-1]的值，所以需要先把列填完 再填行 从上往下 从左往右
        for (int j = 1; j < length; j++) {
            for (int i = 0; i < j; i++) {
                // 首尾字符不相等 肯定不是回文串
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    // 首尾字符串相等，且间距小于3，肯定是回文串 例如'aba'
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        // 首尾字符串相同，则看去掉首尾后的字符串
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    start = i;
                    maxLen = j - i + 1;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    public void traverseNoCopy(int[][] a) {
        int length = a.length;
        for (int j = 0; j < length; j++) {
            System.out.print(a[j][j] + " ");
            System.out.println();
        }
        // Traverse the upper part
//        for(int j = 0; j < a[0].length; j++)
//        {
//            for(int k = 0; k <= j; k++)
//            {
//                System.out.print(a[k][j - k] + " ");
//            }
//            System.out.println();
//        }
//
//        // Traverse the lower part
//        for(int i = 1; i < a.length; i++)
//        {
//            for(int j = i; j < a.length; j++)
//            {
//                System.out.print(a[j][a.length - j + i - 1] + " ");
//            }
//            System.out.println();
//        }
    }

    /**
     * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        if (length <= 0) {
            return 0;
        }
        int max = 1;
        // dp[i] 表示以第i个字符为结尾的字符串的无重复字符的最长子串长度
        int[] dp = new int[length];
        // 新建一个map，用来记录每个字符出现的位置
        Map<Character, Integer> map = new HashMap<>();
        // 初始化第一个字母
        dp[0] = 1;
        map.put(chars[0], 0);

        for (int i = 1; i < length; i++) {
            // 说明当前字母没有出现过
            if (map.get(chars[i]) == null) {
                dp[i] = dp[i - 1] + 1;
            } else {
                // 当前字母出现的位置与上一次出现该字母的位置的距离
                int gap = i - map.get(chars[i]);

                if (gap == dp[i - 1]) {
                    // 上一次出现当前字母的位置刚好在dp[i-1]最长无重复子串的头结点
                    dp[i] = dp[i - 1];
                } else if (gap > dp[i - 1]) {
                    // 上一次出现当前字母的位置不在dp[i-1]最长无重复子串中
                    dp[i] = dp[i - 1] + 1;
                } else {
                    // 上一次出现当前字母的位置刚在dp[i-1]最长无重复子串的中间，所以只能取 gap
                    dp[i] = gap;
                }
            }
            // 记录当前字母最新出现的位置
            map.put(chars[i], i);
            max = Math.max(dp[i], max);
        }
        return max;
    }

    /**
     * 从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        // 新建一个map，用来记录每个字符出现的位置
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        int tmp = 0;
        for (int i = 0; i < s.length(); i++) {
            // s[i] 上一次出现的位置
            int j = map.getOrDefault(s.charAt(i), -1);
            // s[i] 最新出现的位置
            map.put(s.charAt(i), i);
            // tmp 代表的是以s[i-1]结尾的最长无重复子串的长度
            // i - j > tmp 说明 s[i] 不在上一个子串中
            tmp = i - j > tmp ? tmp + 1 : i - j;
            max = Math.max(tmp, max);
        }
        return max;
    }

    }
