package com.lsj.bfs;

import java.util.*;

public class OpenLock {

    public int openLock(String[] deadends, String target) {

        int step = 0;
        String init = "0000";
        // 把死亡数字加入到已访问过的列表里面，遇到就跳过
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        Queue<String> queue = new LinkedList<>();
        queue.add(init);
        // 初始值是死亡密码
        if (visited.contains(init)) {
            return -1;
        }
        visited.add(init);
        while (!queue.isEmpty()) {
            // 当前队列的大小，轮询当前队列，这是上一次转动的所有可能值
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (poll.equals(target)) {
                    return step;
                }
                // 接下来四个位置都可以选择转动 所以要枚举4个位置
                for (int j = 0; j < 4; j++) {
                    String half = half(poll, j);
                    // 之前访问过的 或者是死亡密码 跳过
                    if (!visited.contains(half)) {
                        queue.add(half);
                        visited.add(half);
                    }
                    String down = down(poll, j);
                    if (!visited.contains(down)) {
                        queue.add(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }

        return -1;
    }

    /**
     * 向上转动一位
     *
     * @param s
     * @param i
     */
    public String half(String s, int i) {
        char[] chars = s.toCharArray();
        if (chars[i] == '9') {
            chars[i] = '0';
        } else {
            chars[i] += 1;
        }
        return new String(chars);
    }

    /**
     * 向下转动一位
     *
     * @param s
     * @param i
     * @return
     */
    public String down(String s, int i) {
        char[] chars = s.toCharArray();
        if (chars[i] == '0') {
            chars[i] = '9';
        } else {
            chars[i] -= 1;
        }
        return new String(chars);

    }
}
