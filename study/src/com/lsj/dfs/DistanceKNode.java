package com.lsj.dfs;

import com.lsj.tree.TreeNode;

import java.util.*;

public class DistanceKNode {

    /**
     * 给一棵树的根节点 ，找到所有与目标节点的距离为K的节点
     *
     * @param root
     * @param target
     * @param k
     * @return
     */
    List<Integer> res = new ArrayList<>();
    // 用来记录节点的父节点
    Map<Integer, TreeNode> map = new HashMap<>();
    // 像这种类图的遍历 一定要一个visited 防止走回头路
    HashSet<Integer> visited = new HashSet<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 把所有节点的父节点先记录下来
        traverse(root, null);
        // 从目标节点开始DFS，
        dfs(target, 0, k);
        return res;
    }

    private void dfs(TreeNode root, int dis, int k) {
        if (root == null) {
            return;
        }
        if (dis == k) {
            res.add(root.val);
        }
        visited.add(root.val);
        // 找父节点
        if (map.get(root.val) != null && !visited.contains(map.get(root.val).val)) {
            TreeNode parentNode = map.get(root.val);
            dfs(parentNode, dis + 1, k);
        }
        // 找左节点
        if (root.left != null && !visited.contains(root.left.val)) {
            dfs(root.left, dis + 1, k);
        }
        // 找右节点
        if (root.right != null && !visited.contains(root.right.val)) {
            dfs(root.right, dis + 1, k);
        }
    }

    private void traverse(TreeNode root, TreeNode parent) {
        if (root == null) {
            return;
        }
        map.put(root.val, parent);
        if (root.left != null) {
            traverse(root.left, root);
        }
        if (root.right != null) {
            traverse(root.right, root);
        }
    }
}
