package com.lsj.pointer;

import java.util.Arrays;

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
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 回文串长度 是 奇数 或者 偶数
            String s1 = longestPalindrome(s, i, i);
            String s2 = longestPalindrome(s, i, i + 1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    private String longestPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        int v = (int) (1 % (Math.pow(10, 3) + 1));
        System.out.println(Math.pow(10, 3) + 1);
        return s.substring(left + 1, right);
    }

    public String reverseWords(String s) {
        String cur = reverse(s);
        StringBuilder sb = new StringBuilder();
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (cur.charAt(i) == ' ') {
                sb.append(reverse(cur.substring(left, i)));
                sb.append(' ');
                left = i + 1;
            }
            if (i == s.length() - 1) {
                sb.append(reverse(cur.substring(left, i + 1)));
            }
        }
        return sb.toString();
    }

    private String reverse(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            char tmp = s.charAt(i);
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < chars.length; k++) {
            sb.append(chars[k]);
        }
        return sb.toString();
    }

}
