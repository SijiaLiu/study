package com.lsj.bfs;

import java.util.*;

public class LadderLength {

    /**
     * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
     *
     * 每次转换只能改变一个字母。
     * 转换过程中的中间单词必须是字典中的单词。
     * 说明:
     *
     * 如果不存在这样的转换序列，返回 0。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     * 示例 1:
     *
     * 输入:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     *
     * 输出: 5
     *
     * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     *      返回它的长度 5。
     * 示例 2:
     *
     * 输入:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     *
     * 输出: 0
     *
     * 解释: endWord "cog" 不在字典中，所以无法进行转换。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-ladder
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int step = 0;
        Set<String> set = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        String replace = "abcdefghijklmnopqrstuvwxyz";
        queue.add(beginWord);
        while (!queue.isEmpty()) {
            ++step;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (poll.equals(endWord)) {
                    return step;
                }
                // 单词列表为空了 说明能转换的都转换了，当前队列如果没有 那就是没有了
                if (set.isEmpty()) {
                    continue;
                }
                // 遍历这个单词，这个单词的每个字符都可以转换，但要判断转换的单词在不在单词列表中
                for (int j = 0; j < poll.length(); j++) {
                    for (int k = 0; k < 26; k++) {
                        String temp = convert(poll, j, replace.charAt(k));
                        if (set.contains(temp)) {
                            // 转换后的单词在单词列表中，把它加到队列中
                            queue.add(temp);
                            // 移除 下次不用找这个单词了
                            set.remove(temp);
                        }
                    }
                }
            }
        }
        return 0;
    }

    private String convert(String s, int n, char c) {
        char[] chars = s.toCharArray();
        chars[n] = c;
        StringBuilder sb = new StringBuilder();
        for (char c1 : chars) {
            sb.append(c1);
        }
        return sb.toString();
    }
}
