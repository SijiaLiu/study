package com.lsj.dp;

import java.util.Arrays;
import java.util.List;

public class WordBreak {

    /**
     * /**
     *      * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
     *      *
     *      * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
     *      *
     *      *  
     *      *
     *      * 示例 1：
     *      *
     *      * 输入: s = "leetcode", wordDict = ["leet", "code"]
     *      * 输出: true
     *      * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
     *      *
     *      * 来源：力扣（LeetCode）
     *      * 链接：https://leetcode.cn/problems/word-break
     *      * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *      * @param s
     *      * @param wordDict
     *      * @return
     *      */
    int[] memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        // 备忘录，-1 代表未计算，0 代表 false，1 代表 true
        memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return dp(s, 0, wordDict);
    }

    /**
     *  返回s[start] - s[len - 1] 字符能不能在 字典中找到
     *  同时 也说明了 s[start] 之前的已经匹配上了
     * @param s
     * @param start
     * @param wordDict
     * @return
     */
    private boolean dp(String s, int start, List<String> wordDict) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != -1) {
            return memo[start] == 1;
        }
        for (int i = 0; i < wordDict.size(); i++) {
            String word = wordDict.get(i);
            int len = word.length();
            // 拼接后长度超过的直接去掉
            if (start + len > s.length()) {
                continue;
            }
            // 如果这段能匹配，那就继续匹配下一段
            if (s.substring(start, start + len).equals(word)) {
                memo[i] = 1;
                return dp(s, start + len, wordDict);
            }
        }
        // 默认就是匹配不上
        memo[start] = 0;
        return false;
    }
}
