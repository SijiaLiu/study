package com.lsj.backtrack;

import java.util.ArrayList;
import java.util.List;

public class HasPath {

    public boolean hasPath(String targetPath, char[][] chars) {
        if (targetPath == null || targetPath.length() == 0) {
            return true;
        }
        if (chars == null || chars.length == 0) {
            return false;
        }
        char[] charArray = targetPath.toCharArray();
        int row = chars.length;
        int col = chars[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (hasPath(charArray, 0, i, j, chars)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasPath(char[] charArray, int position, boolean[][] visited,
                            int row, int col, char[][] chars) {
        if (position == charArray.length) {
            return true;
        }
        if (row < 0 || col < 0 || row >= chars.length || col >= chars[0].length
                || charArray[position] != chars[row][col] || visited[row][col]) {
            return false;
        }
        visited[row][col] = true;
        if (hasPath(charArray, position + 1, visited, row + 1, col, chars)
                || hasPath(charArray, position + 1, visited, row - 1, col, chars)
                || hasPath(charArray, position + 1, visited, row, col + 1, chars)
                || hasPath(charArray, position + 1, visited, row, col - 1, chars)) {
            return true;
        } else {
            visited[row][col] = false;
        }
        return false;
    }

    /**
     *
     * @param charArray 目标值的所有字符
     * @param position 目标字符的位置
     * @param row 搜寻地点的行
     * @param col 搜寻地点的列
     * @param chars 原数组
     * @return
     */
    private boolean hasPath(char[] charArray, int position, int row, int col, char[][] chars) {
        if (position == charArray.length) {
            return true;
        }
        if (row < 0 || col < 0 || row >= chars.length || col >= chars[0].length
                || charArray[position] != chars[row][col]) {
            return false;
        }
        // 把访问过的位置 先变成一个非法字符，这样就不用加一个visited的数组了
        chars[row][col] = '\0';
        if (hasPath(charArray, position + 1, row + 1, col, chars)
                || hasPath(charArray, position + 1,row - 1, col, chars)
                || hasPath(charArray, position + 1, row, col + 1, chars)
                || hasPath(charArray, position + 1, row, col - 1, chars)) {
            return true;
        } else {
            // 把这个位置变成原来的
            chars[row][col] = charArray[position];
        }
        return false;
    }


    /**
     * 题目：输入一个含有8个数字的数组，判断有没有可能把这8个数字分别放在正方体的8个顶点上，使得正方体上三组相对的面上的4个顶点的和都相等。
     *
     * @param list
     * @return
     */
    public boolean has(List<Integer> list) {
        if (list == null || list.size() < 8) {
            return false;
        }
        return hasPath(new ArrayList<>(), list, new boolean[8]);
    }

    private boolean hasPath(List<Integer> cur, List<Integer> list, boolean[] visited) {
        // 找到一个排列组合
        if (cur.size() == list.size()) {
            return cur.get(0) + cur.get(1) + cur.get(2) + cur.get(3) == cur.get(4) + cur.get(5) + cur.get(6) + cur.get(7)
                    && cur.get(0) + cur.get(2) + cur.get(4) + cur.get(6) == cur.get(1) + cur.get(3) + cur.get(5) + cur.get(7)
                    && cur.get(0) + cur.get(1) + cur.get(4) + cur.get(5) == cur.get(2) + cur.get(3) + cur.get(6) + cur.get(7);
        }
        for (int i = 0; i < list.size(); i++) {
            if (!visited[i]) {
                cur.add(list.get(i));
                visited[i] = true;
                if (hasPath(cur, list, visited)){
                    return true;
                }
                cur.remove(cur.size() - 1);
                visited[i] = false;
            }
        }
        return false;
    }



}
