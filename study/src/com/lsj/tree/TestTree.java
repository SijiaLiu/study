package com.lsj.tree;
/**
 * class_name: TestTree
 * package: algorithm.tree
 * describe:
 * @author liusijia
 * @Date 2019/1/31
**/

public class TestTree {
    public static void main(String[] args) {
        BinTree binTree = new BinTree();
        Object[] objs = {10, 1, 20, 3, 5, 8, 19, 25, 22, 40, 15, 6, 17};
        binTree.createTree(objs);
        binTree.preOrder(binTree.getRoot()); //先序遍历
        System.out.println();
        binTree.inOrder(binTree.getRoot()); //中序遍历
        System.out.println();
        binTree.inOrderNonRecursive(binTree.getRoot());
        System.out.println();
        binTree.postOrderStack(binTree.getRoot()); //后序遍历
        System.out.println();

        long st = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            binTree.postOrderNonRecursive(binTree.getRoot());
        }
        System.out.println(System.currentTimeMillis() - st);
        System.out.println("-------BinaryTree---------");


        TreeRoot treeRoot = new TreeRoot();
        for (int i = 0; i < objs.length; i++) {
            BinaryTree.createBinaryTree(treeRoot, (Integer) objs[i]);
        }
        System.out.println("树的深度 分治+DFS: " + BinaryTree.getTreeHeight(treeRoot.getTreeNode()));
        System.out.println("树的深度 BFS: " + BinaryTree.getTreeHeightBfs(treeRoot.getTreeNode()));
        System.out.println("树的最小深度 BFS: " + BinaryTree.getMinTreeHeightBfs(treeRoot.getTreeNode()));
        BinaryTree.preOrderStack(treeRoot.getTreeNode());
        System.out.println("前序" + BinaryTree.getTreeList());
        BinaryTree.inOrderStack(treeRoot.getTreeNode());
        System.out.println("中序" + BinaryTree.getTreeList());
        BinaryTree.postOrderStack(treeRoot.getTreeNode());
        System.out.println("后序" + BinaryTree.getTreeList());
        BinaryTree.levelOrderTraversal(treeRoot.getTreeNode());
        System.out.println("层序" + BinaryTree.getTreeList());
        // 最大值
        System.out.println(BinaryTree.findMax(treeRoot.getTreeNode()));
        // 最小值
        System.out.println(BinaryTree.findMin(treeRoot.getTreeNode()));
        // 插入二叉树
        BinaryTree.insertTreeNode(treeRoot.getTreeNode(), 14);
        BinaryTree.levelOrderTraversal(treeRoot.getTreeNode());
        System.out.println("层序" + BinaryTree.getTreeList());
        // 删除元素
        BinaryTree.deleteTreeNode(treeRoot.getTreeNode(), 20);
        BinaryTree.levelOrderTraversal(treeRoot.getTreeNode());
        System.out.println("层序" + BinaryTree.getTreeList());

        // 判断是否是二叉搜索树
        System.out.println("是否是二叉搜索树：" + BinaryTree.isBinaryTree(treeRoot.getTreeNode(), null, null));

        // 层序遍历
        System.out.println("层序遍历BFS : " + BinaryTree.levelOrderBfs(treeRoot.getTreeNode()));
        System.out.println("层序遍DFS : " + BinaryTree.levelOrderDfs(treeRoot.getTreeNode()));
        // 蛇形输出二叉树
        TreeNode t = new TreeNode(1);
        TreeNode t1 = new TreeNode(2);
        t.right = t1;
        System.out.println("蛇形输出二叉树 : " + BinaryTree.serpentineOutputBinaryTree(t));

        System.out.println(BinaryTree.verifyPostorder(new int[]{4, 8, 6, 12, 16, 14, 10}));

    }
}
