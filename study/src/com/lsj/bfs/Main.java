package com.lsj.bfs;


import com.lsj.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {


        OpenLock openLock = new OpenLock();
        System.out.println(openLock.openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202"));


        Land land = new Land();
        char[][] grid1 = new char[][]{{'1'}, {'1'}};
        System.out.println(land.numLandsBfs(grid1));

        NumSquares numSquares = new NumSquares();
        System.out.println(numSquares.numSquares(18));

        LadderLength ladderLength = new LadderLength();
        String[] strings = new String[]{"lest","lose","code","lode","robe","lost"};

        System.out.println("1 : " + ladderLength.ladderLength("leet", "code", new ArrayList<>(Arrays.asList(strings))));
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(8);
        TreeNode t9 = new TreeNode(9);
        TreeNode t10 = new TreeNode(10);

        t1.setLeft(t2);
        t1.setRight(t3);
        t2.setLeft(t4);
        t3.setLeft(t5);
        t4.setLeft(t10);
        t5.setLeft(t7);
        t5.setRight(t6);
        t7.setLeft(t8);
        t7.setRight(t9);
    }


}
