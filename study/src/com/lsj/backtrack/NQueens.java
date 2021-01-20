package com.lsj.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens(5);
        System.out.println(lists);
    }

    private static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        initBoard(board);
        helper(res, board, 0);
        return res;
    }

    // 初始化board
    private static void initBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '.');
        }
    }

    // 判断当前位置是否合法
    private static boolean isValid(char[][] board, int row, int col) {
        // 当前列是否合法
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }

        // 左上对角线是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j-- ) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // 右上对角线是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++ ) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    private static List<String> generateBoard(char[][] board) {
        List<String> list = new ArrayList<>();
        for (char[] cs : board) {
            StringBuilder sb = new StringBuilder();
            for (char c : cs) {
                sb.append(c);
            }
            list.add(sb.toString());
        }
        return list;
    }

    // 从第一行开始 逐行填充 dfs
    // 行数在每次dfs的时候都会加一，所以用列数做循环条件
    private static void helper(List<List<String>> res, char[][] board, int row){
        // 访问到最后一行的时候
        if (row == board.length) {
            res.add(generateBoard(board));
            return;
        }
        // 第一列开始尝试
        for (int col = 0; col < board.length; col++) {
            if (isValid(board, row, col)) {
                board[row][col] = 'Q';
                helper(res, board, row + 1);
                // unChoose 回溯 还原
                board[row][col] = '.';
            }
        }
    }
}
