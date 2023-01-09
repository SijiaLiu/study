package com.lsj.dp;

/**
 * 给你两个单词word1 和word2，请你计算出将word1转换成word2 所使用的最少操作数。
 * <p>
 * 你可以对一个单词进行如下三种操作：
 * <p>
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 */
public class EditDistance {

    public int minDistance(String word1, String word2) {
        int w1 = word1.toCharArray().length;
        int w2 = word2.toCharArray().length;
        // dp[i][j] 表示直到word1第i个字符和word2第j个字符 word1转换成word2时需要的最小转换次数
        int[][] dp = new int[w1 + 1][w2 + 1];
        // init 某一个单词长度为0。 则需要一直插入或者删除
        for (int i = 0; i <= w1; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= w2; i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= w1; i++) {
            for (int j = 1; j <= w2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 如果 A 的第 i 个字符和 B 的第 j 个字符原本就相同，那么我们实际上不需要进行修改操作。在这种情况下，D[i][j] 最小可以为 D[i-1][j-1]
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // D[i][j-1] 为 A 的前 i 个字符和 B 的前 j - 1 个字符编辑距离的子问题。
                    // 即对于 B 的第 j 个字符，我们在 A 的末尾添加了一个相同的字符，所以 B 的第 j 个字符就匹配上了，所以 j 前进一步
                    // 那么 D[i][j] 最小可以为 D[i][j-1] + 1；

                    // D[i-1][j] 为 A 的前 i - 1 个字符和 B 的前 j 个字符编辑距离的子问题。
                    // 即对于 B 的第 j 个字符，我看A的第i个字符不爽，我不喜欢他，我把删掉，让他和i-1比较，此时B的第j个字符还没匹配上，让他和i-1比，所以 i 前进一步
                    // 那么 D[i][j] 最小可以为 D[i-1][j] + 1；

                    // D[i-1][j-1] 为 A 前 i - 1 个字符和 B 的前 j - 1 个字符编辑距离的子问题。
                    // 即对于 B 的第 j 个字符，我们修改 A 的第 i 个字符使它们相同，那么i, j 就完全匹配上了,那就共同前进 D[i][j] 最小可以为 D[i-1][j-1] + 1。
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[w1][w2];
    }
}
