package com.lsj.us;

public class Land {

    public int numLands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        LandUnionSet landUnionSet = new LandUnionSet(grid);
        int[][] dir = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    for (int[] ints : dir) {
                        int newRow = i + ints[0];
                        int newCol = j + ints[1];
                        if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length && grid[newRow][newCol] == '1') {
                            int x = i * col + j;
                            int y = newRow * col + newCol;
                            landUnionSet.union(x, y);
                        }
                    }
                }
            }
        }
        return landUnionSet.getCount();
    }
}
