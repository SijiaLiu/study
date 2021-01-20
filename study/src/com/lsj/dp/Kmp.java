package com.lsj.dp;

import java.util.Arrays;
import java.util.Comparator;

public class Kmp {
    // dp[i][j] 表示处于i状态的字符串遇到新字符对应的字符编码j 的下一个状态是什么
    private int[][] dp;

    public Kmp() {
    }

    ;

    public Kmp(String pat) {
        dp = new int[pat.length()][256];
        // base case
        dp[0][pat.charAt(0)] = 1;
        // 前置状态 用于状态回退
        int x = 0;
        for (int i = 1; i < pat.length(); i++) {
            for (int j = 0; j < 256; j++) {
                // 除非遇到了目标字符，否则都需要回退状态
                dp[i][j] = dp[x][j];
            }
            // 遇到了目标字符，状态推进
            dp[i][pat.charAt(i)] = i + 1;
            // 更新回退的状态
            x = dp[x][pat.charAt(i)];
        }
    }

    public int search(String source, String pat) {
        int length = pat.length();
        // pat 的初始状态
        int j = 0;
        for (int i = 0; i < source.length() && j < length; i++) {
            j = dp[j][source.charAt(i)];
            // 状态全部匹配完了 返回
            if (j == length) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public int searchKmp(String text, String pat) {
        int M = text.length();
        int N = pat.length();
        // b[i] 的值为 当t[k] != p[i] 时 t[k]下一次需要比较的值 即t[k] 和 p[b[i]] 比较
        int[] b = new int[N];
        // init b
        int i = 0;
        int j = -1;
        b[i] = j;
        while (i < N - 1) {
            while (j > -1 && pat.charAt(i) != pat.charAt(j)) {
                j = b[j];
            }
            // 当j=-1 时 从头开始
            // p[i] = p[j] 时 两个共同前进一步
            b[++i] = ++j;
        }

        // search
        // text 指针
        i = 0;
        // pat 指针
        j = 0;
        while (i < M && j < N) {
            while (j > -1 && text.charAt(i) != pat.charAt(j)) {
                j = b[j];
            }
            // t[i] = t[j] 共同前进
            i++;
            j++;
            if (j == N) {
                return i - j;
            }
        }
        return -1;
    }

    public int[] divingBoard(int shorter, int longer, int k) {
        if(k == 0) {
            return new int[0];
        }
        if (shorter == longer) {
            return new int[]{longer * k};
        }
        int[] res = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            res[i] = i * longer + (k - i) * shorter;
        }
        int[][] intervals = new int[10][10];
        int length = intervals.length;
        Arrays.sort(intervals, (o1, o2) -> o2[1] - o1[1]);
        return res;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        int length = intervals.length;
        if (length <= 1) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        int result = 1;
        int end = intervals[0][1];
        for (int[] in : intervals) {
            int start = in[0];
            if (start >= end) {
                result++;
                end = in[1];
            }
        }
        return length - result;
    }


}
