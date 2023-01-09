package com.lsj.dp;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：
 * 0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可
 * //能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * //
 * //
 * //
 * // 示例 1:
 * //
 * // 输入: 12258
 * //输出: 5
 * //解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 */
public class TranslateNum {

    public int translateNum(int num) {
        if (num == 0) {
            return 1;
        }
        char[] chars = Integer.toString(num).toCharArray();
        int length = chars.length;
        // dp[i] 表示以第i位数字开头的数字的翻译方法数量
        int[] dp = new int[length + 1];
        dp[length - 1] = 1;
        dp[length] = 1;
        for (int i = length - 2; i >= 0; i--) {
            dp[i] = dp[i + 1];
            // 尝试的一下当前位置和后一个位置是否能组成一个新的合法数字
            int sum = (chars[i] - '0') * 10 + (chars[i + 1] - '0');
            // 只有当数字大于10 且 小于26 才有可能有多种翻译方法
            if (sum > 9 && sum < 26) {
                dp[i] = dp[i + 1] + dp[i + 2];
            }
        }
        return dp[0];
    }
}
