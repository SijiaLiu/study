package com.lsj.tree;


/**
 * 面试题68 - II.
 */
public class Ancestor {

    /**
     * 二叉树的最近公共祖先 lowest common ancestor LCA
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * <p>
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉树中。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 节点为空，或者找到了p或者q，返回节点
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.getLeft(), p, q);
        TreeNode right = lowestCommonAncestor(root.getRight(), p, q);
        // 通过上面两个递归，肯定能找到p或者q
        // 如果左子树为空，说明p,q 都在右子树上，根节点的右节点即是最近公共祖先，同理，右子树也是
        // 如果左右子树都不为空，说明p,q分别在根节点的左右子树上，则根节点即是最近公共祖先
        return left == null ? right : right == null ? left : root;
    }

    /**
     * 二叉搜索树的最近公共祖先
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestorBinaryTree(TreeNode root, TreeNode p, TreeNode q) {
        // p,q均小于根节点,只用在左子树找
        if (p.getVal() < root.getVal() && root.getVal() > q.getVal()) {
            return lowestCommonAncestorBinaryTree(root.getLeft(), p, q);
        }
        if (p.getVal() > root.getVal() && root.getVal() < q.getVal()) {
            return lowestCommonAncestorBinaryTree(root.getRight(), p, q);
        }
        // root 位于 p,q之间，root即是最近公共祖先
        return root;
    }
}
