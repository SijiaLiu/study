package com.lsj.dp;

import java.util.List;

/**
 * 三角形的最短路径
 */
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        // 为什么能用一维数组？ 自底向上，每一层只关心自己下一层的数值
        // 加一是为了能直接从最后一行(row-1)开始算起, 因为dp[row] = 0
        int[] dp = new int[row + 1];
        for(int i = row - 1; i >= 0; i--) {
            for(int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j+1]);
            }
        }
        return dp[0];
    }
}
