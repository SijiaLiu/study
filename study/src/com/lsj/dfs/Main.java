package com.lsj.dfs;

public class Main {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}};
        Land land = new Land();
        //System.out.println(land.numLands(grid));
        System.out.println("岛屿最大面积：" + land.maxAreaOfIsland(grid));


        FindTargetSumWays findTargetSumWays = new FindTargetSumWays();
        System.out.println(findTargetSumWays.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));
    }


}
