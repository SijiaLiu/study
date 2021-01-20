package com.lsj.dp;

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

}
