package com.lsj.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TrieTest {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("liu");

    }

    public boolean isContainWord(String word, char[][] grid) {
        if (grid.length <= 0 || grid[0].length <= 0) {
            return false;
        }
        Trie trie = new Trie();
        trie.insert(word);
        // 标识该位置是否被访问过
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (search(trie, "", grid, visited, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean search(Trie trie, String str, char[][] grid, boolean[][] visited, int i, int j) {
        // 数组坐标不在范围内 返回
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return false;
        }
        // 该节点被访问过，返回
        if (visited[i][j]) {
            return false;
        }
        str += grid[i][j];
        // 如果该字符串不是字典树开头，也不用搜索了
        if (!trie.startsWith(str)) {
            return false;
        }
        // 如果这字符串是单词，返回找到了
        if (trie.search(str)) {
            return true;
        }
        // 先标识这个点被访问过了，因为接下来要以这个点为起点，上下左右遍历，不标识这个点被访问过的话，就会A跳到B，B跳到A，两个点反复跳
        visited[i][j] = true;
        int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        // 以当前这个点为起点，上下左右遍历
        for (int k = 0; k < dir.length; k++) {
            // 如果找到了 那就返回
            if (search(trie, str, grid, visited, i + dir[k][0], j + dir[k][1])) {
                return true;
            }
        }
        // 没找到正确的单词，把这个点标识为未访问过，不然这个点就没办法被后面的节点找到
        visited[i][j] = false;
        return false;
    }

    public List<String> findWords(String[] words, char[][] grid) {
        if (grid.length <= 0 || grid[0].length <= 0) {
            return null;
        }
        // 构造字典树
        Trie trie = new Trie();
        for (String s : words) {
            trie.insert(s);
        }
        // 使用set 防止重复, 因为同一个单词 可能在数组里通过不同的位置的字母组成
        Set<String> list = new HashSet<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                search(list, trie, "", grid, visited, i, j);
            }
        }

        return new ArrayList<>(list);
    }

    public void search(Set<String> res, Trie trie, String str, char[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        }
        if (visited[i][j]) {
            return;
        }
        // 字符串 最终可能是个单词
        str += grid[i][j];
        if (!trie.startsWith(str)) {
            return;
        }
        if (trie.search(str)) {
            res.add(str);
        }
        visited[i][j] = true;
        int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int k = 0; k < dir.length; k++) {
            search(res, trie, str, grid, visited, i + dir[k][0], j + dir[k][1]);
        }
        visited[i][j] = false;
    }
}
