package com.lsj.tree;
/**
 * class_name: TreeNode
 * package: algorithm.tree
 * describe: TODO
 * @author liusijia
 * @Date 2019/1/31
**/


public class TreeNode {

    public TreeNode left;

    public TreeNode right;

    public int val;

    private int visit;

    public TreeNode next;

    public TreeNode() {

    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;

    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getNext() {
        return next;
    }

    public void setNext(TreeNode next) {
        this.next = next;
    }
}
