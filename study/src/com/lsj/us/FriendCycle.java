package com.lsj.us;

public class FriendCycle {

    public int findCircleNum(int[][] M) {
        FriendCycleUnionSet friendCycleUnionSet = new FriendCycleUnionSet(M);
        for (int i = 0; i < M.length; i++) {
            // 对称矩阵，所以只用遍历一半即可
            for (int j = 0; j < i; j++) {
                if (M[i][j] == 1) {
                    friendCycleUnionSet.union(i, j);
                }
            }
        }
        return  friendCycleUnionSet.getCount();
    }
}
