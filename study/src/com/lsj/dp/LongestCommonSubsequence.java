package com.lsj.dp;

public class LongestCommonSubsequence {

    /**
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
     * <p>
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
     * <p>
     * 若这两个字符串没有公共子序列，则返回 0。
     * <p>
     * 示例 1:
     * <p>
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace"，它的长度为 3。
     * 示例 2:
     * <p>
     * 输入：text1 = "abc", text2 = "abc"
     * 输出：3
     * 解释：最长公共子序列是 "abc"，它的长度为 3。
     * 示例 3:
     * <p>
     * 输入：text1 = "abc", text2 = "def"
     * 输出：0
     * 解释：两个字符串没有公共子序列，返回 0。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int t1 = text1.length();
        int t2 = text2.length();
        if (t1 == 0 || t2 == 0) {
            return 0;
        }
        // 对于text1和text2 前i个和前j个字符中最长的公共子序列 dp[i][j]
        int[][] dp = new int[t1 + 1][t2 + 1];
        for (int i = 1; i <= t1; i++) {
            for (int j = 1; j <= t2; j++) {
                // 若第i个字符和第j个字符相等，则他们的最长公共子序列等于前一个加一
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 不相等，那就看舍弃哪个能取到最大值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[t1][t2];
    }

    /**
     * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
     * <p>
     *  
     * <p>
     * 示例 1:
     * 输入:
     * <p>
     * "bbbab"
     * 输出:
     * <p>
     * 4
     * 一个可能的最长回文子序列为 "bbbb"。
     * <p>
     * 示例 2:
     * 输入:
     * <p>
     * "cbbd"
     * 输出:
     * <p>
     * 2
     * 一个可能的最长回文子序列为 "bb"。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int length = s.length();
        // dp[i][j] 表示字符串s[i,j]的最长回文子序列
        int[][] dp = new int[length][length];
        // 单个字符串肯定是回文序列，长度为1
        for (int i = 0; i < length; i++) {
            dp[i][i] = 1;
        }
        // 因为dp[i][j] 依赖于dp[i+1][j] d[i][j-1] 所以反着遍历
        // 目标值是dp[0][len-1] 在右上角，所以需要从右下角开始降序遍历，左边升序遍历
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 两个字符中选一个，取最大值
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        // 斜着遍历？
        for (int j = 1; j < length; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    // 两个字符中选一个，取最大值
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][length - 1];
    }

    /**
     * 给你一个字符串 s ，每一次操作你都可以在字符串的任意位置插入任意字符。
     *
     * 请你返回让 s 成为回文串的 最少操作次数 。
     *
     * 「回文串」是正读和反读都相同的字符串。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "zzazz"
     * 输出：0
     * 解释：字符串 "zzazz" 已经是回文串了，所以不需要做任何插入操作。
     * 示例 2：
     *
     * 输入：s = "mbadm"
     * 输出：2
     * 解释：字符串可变为 "mbdadbm" 或者 "mdbabdm" 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        int length = s.length();
        // dp[i][j] 表示字符串s[i,j]在任意位置插入任意字符后变成回文串的操作次数
        int[][] dp = new int[length][length];
        // 单个字符串肯定是回文序列，不需要操作
        for (int i = 0; i < length; i++) {
            dp[i][i] = 0;
        }
        for (int i = length - 1; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                // i , j 相同，那就看子串的最小次数
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    // i , j 不同，那就可以在子串中插入一个，左插或者右插
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][length - 1];
    }

    /**
     * 给定一个未经排序的整数数组，找到最长且连续的的递增序列，并返回该序列的长度。
     * <p>
     *  
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,3,5,4,7]
     * 输出: 3
     * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
     * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
     * 示例 2:
     * <p>
     * 输入: [2,2,2,2,2]
     * 输出: 1
     * 解释: 最长连续递增序列是 [2], 长度为1。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int findLengthOfLCIS(int[] nums) {
        int length = nums.length;
        if (length <= 1) {
            return length;
        }
        int pre = 1;
        int cur = 1;
        int res = 1;
        // nums[i] 表示目标序列中的最小值
        for (int i = 1; i < length; i++) {
            // 因为要求是递增且连续的序列，当前值大于前一个值则pre + 1
            // 否则 pre 从头再来
            if (nums[i] > nums[i - 1]) {
                cur = pre + 1;
                pre = cur;
            } else {
                pre = 1;
            }
            res = Math.max(cur, res);
        }
        return res;
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, helper(grid, i, j));
                }
            }
        }
        return res;
    }

    private int helper(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return 0;
        }

        grid[i][j] = 0;
        return 1 + helper(grid, i - 1, j)
                + helper(grid, i, j - 1)
                + helper(grid, i + 1, j)
                + helper(grid, i, j + 1);
    }

    int res = 0;
    public int closedIsland(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    helper_1(grid, i, j);
                }
            }
        }
        return res;
    }

    private void helper_1(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) {
            return;
        }
        res ++;
        grid[i][j] = 0;
        helper(grid, i - 1, j);
        helper(grid, i, j - 1);
        helper(grid, i + 1, j);
        helper(grid, i, j + 1);
    }
}
