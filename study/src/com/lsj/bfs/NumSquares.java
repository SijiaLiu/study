package com.lsj.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NumSquares {

    /**
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
     * 你需要让组成和的完全平方数的个数最少。
     *
     * 示例 1:
     *
     * 输入: n = 12
     * 输出: 3
     * 解释: 12 = 4 + 4 + 4.
     * 示例 2:
     *
     * 输入: n = 13
     * 输出: 2
     * 解释: 13 = 4 + 9.
     * @param n
     * @return
     */
    public int numSquares(int n) {
        // 把小于目标数的平方数先存储起来
        List<Integer> maxNums = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (i * i <= n) {
                maxNums.add(i*i);
            } else {
                break;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);
        int step = 0;
        while (!queue.isEmpty()) {
            step++;
            int size = queue.size();
            // 上一次相减后所有可能的数
            for (int i = 0; i < size; i++) {
                Integer poll = queue.poll();
                for (Integer maxNum : maxNums) {
                    if (poll > maxNum) {
                        // 所有小于目标值的平方数可能选择来和目标数相减，把余数加到新的队列
                        queue.add(poll - maxNum);
                    } else if (poll < maxNum) {
                        // 因为目标数越来越小了，遇见之前较大的存储好的平方数，跳出
                        break;
                    } else {
                        // 说明找到了
                        return step;
                    }
                }
            }
        }
        return step;
    }
}
