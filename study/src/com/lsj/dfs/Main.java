package com.lsj.dfs;

public class Main {
    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1'},{'0'},{'1'},{'1'},{'0'},{'1'},{'1'}};
        Land land = new Land();
        char[][] grid1 = new char[][]{{'1','0','1','1','0','1','1'}};
        System.out.println(land.numLandsBfs(grid1));
    }
}
