package com.lsj.tree;



import java.util.*;

/**
 * class_name: BinaryTree
 * package: algorithm.tree
 * describe: TODO
 *
 * @author liusijia
 * @Date 2019/1/31
 **/

public class BinaryTree {

    private static List<Integer> treeList = new ArrayList<>();

    /**
     * 动态创建二叉搜索树
     * 二叉搜索树的定义: 对于某一个节点，他的左节点都比他小， 右节点都比他大
     *
     * @param treeRoot
     * @param value
     */
    public static void createBinaryTree(TreeRoot treeRoot, int value) {
        // 如果传入的根节点是空 则创建一颗新的二叉树
        if (treeRoot.getTreeNode() == null) {
            TreeNode treeNode = new TreeNode(value);
            treeRoot.setTreeNode(treeNode);
        } else {
            TreeNode root = treeRoot.getTreeNode();
            while (root != null) {
                // 当前值比根节点大 往右边走
                if (value > root.getVal()) {
                    // 如果没有右孩子 则新建一个右孩子插入
                    if (root.getRight() == null) {
                        root.setRight(new TreeNode(value));
                        return;
                    }
                    // 如果有右孩子 则把右孩子当做根节点
                    root = root.getRight();
                } else {
                    if (root.getLeft() == null) {
                        root.setLeft(new TreeNode(value));
                        return;
                    }
                    root = root.getLeft();
                }
            }
        }
    }

    /**
     * 前序遍历 递归
     *
     * @param treeNode
     */
    public static void preOrder(TreeNode treeNode) {
        if (treeNode != null) {
            treeList.add(treeNode.getVal());
            preOrder(treeNode.getLeft());
            preOrder(treeNode.getRight());
        }
    }

    /**
     * 前序遍历 栈
     *
     * @param treeNode
     */
    public static void preOrderStack(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        if (treeList.size() > 0) {
            treeList = new ArrayList<>();
        }
        if (treeNode != null) {
            stack.push(treeNode);
            while (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeList.add(treeNode.getVal());
                if (treeNode.getRight() != null) {
                    stack.push(treeNode.getRight());
                }
                if (treeNode.getLeft() != null) {
                    stack.push(treeNode.getLeft());
                }
            }
        }
    }

    public static void preOrderStack2(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        if (treeList.size() > 0) {
            treeList = new ArrayList<>();
        }
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeList.add(treeNode.getVal());
                treeNode = treeNode.getLeft();
            }
            if (!stack.isEmpty())
                treeNode = stack.pop();
                treeNode = treeNode.getRight();
        }
    }

    /**
     * 后序遍历 栈
     * 前序遍历 根->左->右
     * 后序遍历 左->右->根
     * 仿照前序遍历的写法 根->右->左 然后翻转一下 => 左->右->根
     *
     * @param treeNode
     */
    public static void postOrderStack(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        if (treeList.size() > 0) {
            treeList = new ArrayList<>();
        }
        if (treeNode != null) {
            stack.push(treeNode);
            while (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeList.add(treeNode.getVal());
                if (treeNode.getLeft() != null) {
                    stack.push(treeNode.getLeft());
                }
                if (treeNode.getRight() != null) {
                    stack.push(treeNode.getRight());
                }
            }
        }
        Collections.reverse(treeList);
    }

    /**
     * 中序遍历 栈
     * 遇到一个节点，入栈，遍历它的左子树
     * 左子树全部入栈，栈顶弹出这个节点并访问他
     * 然后遍历右子树
     * 先把所有左孩子入栈
     * 最后一个左孩子出栈
     * 然后右孩子入栈
     *
     * @param treeNode
     */
    public static void inOrderStack(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        if (treeList.size() > 0) {
            treeList = new ArrayList<>();
        }
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.getLeft();
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeList.add(treeNode.getVal());
                treeNode = treeNode.getRight();
            }
        }
    }


    /**
     * 后序遍历
     * 给树的节点增加一个访问次数的属性
     * 左子树入栈
     * 出栈顶的元素，判断次数是不是2
     *
     * @param treeNode
     */
    public static void postOrderStack2(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        if (treeList.size() > 0) {
            treeList = new ArrayList<>();
        }
        while (treeNode != null || !stack.isEmpty()) {
            while (treeNode != null) {
                stack.push(treeNode);
                treeNode.setVisit(treeNode.getVisit() + 1);
                treeNode = treeNode.getLeft();
            }
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                if (treeNode.getVisit() == 2) {
                    treeNode = null;
                    treeList.add(treeNode.getVal());
                } else {
                    treeNode.setVisit(treeNode.getVisit() + 1);
                    stack.push(treeNode);
                    treeNode = treeNode.getRight();
                }
            }
        }
    }


    /**
     * 层序遍历
     * 使用队列从上往下 从左到右依次入队出队
     */
    public static void levelOrderTraversal(TreeNode treeNode) {
        if (treeList.size() > 0) {
            treeList = new ArrayList<>();
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(treeNode);
        while (queue.size() > 0) {
            treeNode = queue.poll();
            treeList.add(treeNode.getVal());
            if (treeNode.getLeft() != null) {
                queue.offer(treeNode.getLeft());
            }
            if (treeNode.getRight() != null) {
                queue.offer(treeNode.getRight());
            }
        }
    }

    /**
     * 层序遍历
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderBfs(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        // BFS
        // 队列控制先进先出
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 这个很重要 标识当层树的元素
            int levelLen = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < levelLen; i++) {
                TreeNode poll = queue.poll();
                list.add(poll.getVal());
                // 当前节点的左右子树入队，他们属于下一层，不会在这层遍历
                if (poll.getLeft() != null) {
                    queue.add(poll.getLeft());
                }
                if (poll.getRight() != null) {
                    queue.add(poll.getRight());
                }
            }
            res.add(list);
        }
        return res;
    }

    public static List<List<Integer>> levelOrderDfs(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        levelOrderDfs(root, 0, res);
        return res;
    }

    private static void levelOrderDfs(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        if (res.size() < level + 1) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.getVal());
        levelOrderDfs(root.getLeft(), level + 1, res);
        levelOrderDfs(root.getRight(), level + 1, res);
    }

    /**
     * 判断一棵树是不是二叉搜索树
     *
     * @param treeNode
     * @param min
     * @param max
     * @return
     */
    public static Boolean isBinaryTree(TreeNode treeNode, Integer min, Integer max) {
        // 空树 或者到叶子节点了 返回
        if (treeNode == null) {
            return true;
        }
        // 左子树必须小于根节点
        if (min != null && treeNode.getVal() <= min) {
            return  false;
        }
        // 右子树必须大于根节点
        if (max != null && treeNode.getVal() >= max) {
            return false;
        }
        // 递归判断当前树的左右子树
        return isBinaryTree(treeNode.getLeft(), min, treeNode.getVal())
                && isBinaryTree(treeNode.getRight(), treeNode.getVal(), max);
    }

    /**
     * 查询树的深度
     * 树的深度的左子树或者右子树最大深度加一
     *
     * @param treeNode
     * @return
     */
    public static Integer getTreeHeight(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        } else {
            int left = getTreeHeight(treeNode.getLeft());
            int right = getTreeHeight(treeNode.getRight());
            return left > right ? left + 1 : right + 1;
        }
    }

    public static Integer getTreeHeightBfs(TreeNode root) {
        if (root == null){
            return 0;
        }
        int ans = 0;
        // BFS
        // 队列控制先进先出
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int level = queue.size();
            for (int i = 0; i < level; i++) {
                TreeNode poll = queue.poll();
                if (poll.getLeft() != null) {
                    queue.add(poll.getLeft());
                }
                if (poll.getRight() != null) {
                    queue.add(poll.getRight());
                }
            }
            ans++;
        }
        return ans;
    }

    /**
     * 树的最小深度
     *
     * @param root
     * @return
     */
    public static Integer getMinTreeHeightBfs(TreeNode root) {
        if (root == null){
            return 0;
        }
        int ans = 0;
        // BFS
        // 队列控制先进先出
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int level = queue.size();
            for (int i = 0; i < level; i++) {
                TreeNode poll = queue.poll();
                if (poll.getLeft() == null || poll.getRight() == null) {
                    queue.clear();
                    break;
                }
                queue.add(poll.getLeft());
                queue.add(poll.getRight());
            }
            ans++;
        }
        return ans;
    }


    public static List<Integer> getTreeList() {
        return treeList;
    }

    public static void setTreeList(List<Integer> treeList) {
        BinaryTree.treeList = treeList;
    }

    /**
     * 查询二叉搜索数的最小值
     * @param treeNode
     * @return
     */
    public static Integer findMin(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }
        while (treeNode.getLeft() != null) {
            treeNode = treeNode.getLeft();
        }
        return treeNode.getVal();
    }

    /**
     * 查询二叉搜索树的最大值
     * @param treeNode
     * @return
     */
    public static Integer findMax(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }
        if (treeNode.getRight() != null) {
            return findMax(treeNode.getRight());
        } else {
            return treeNode.getVal();
        }
    }

    /**
     * 查询某个元素 递归 和 循环
     * @param treeNode
     * @param element
     * @return
     */
    public static TreeNode findTreeNode(TreeNode treeNode, int element){
        if (treeNode == null) {
            return null;
        }
        if (element > treeNode.getVal()) {
            treeNode = findTreeNode(treeNode.getRight(), element);
        } else if (element < treeNode.getVal()) {
            treeNode = findTreeNode(treeNode.getLeft(), element);
        }
//        while (treeNode != null) {
//            if (treeNode.getValue() < element) {
//                treeNode = treeNode.getrTreeNode();
//            } else if (treeNode.getValue() > element) {
//                treeNode = treeNode.getlTreeNode();
//            } else {
//                break;
//            }
//        }
        return treeNode;
    }

    /**
     * 插入元素
     * @param treeNode
     * @param element
     * @return
     */
    public static TreeNode insertTreeNode(TreeNode treeNode, int element) {

        List<Integer> list = new ArrayList<>();
        if (treeNode == null) {
            return new TreeNode(element);
        } else {
            if (element > treeNode.getVal())
                treeNode.setRight(insertTreeNode(treeNode.getRight(), element));
            else if (element < treeNode.getVal())
                treeNode.setLeft(insertTreeNode(treeNode.getLeft(), element));
        }
        return treeNode;
    }

    /**
     * 删除元素
     * 未完成.....
     * @param treeNode
     * @param element
     * @return
     */
    public static TreeNode deleteTreeNode(TreeNode treeNode, int element) {
        int temp = 0;
        if (treeNode == null) {
            return null;
        } else if (element > treeNode.getVal()) {
            return deleteTreeNode(treeNode.getRight(), element);
        } else if (element < treeNode.getVal()) {
            return deleteTreeNode(treeNode.getLeft(), element);
        } else {
            if (treeNode.getLeft() != null && treeNode.getRight() != null){
                temp = findMin(treeNode.getRight());
                treeNode.setVal(temp);
                treeNode.setRight(deleteTreeNode(treeNode.getRight(), temp));
                return treeNode;
            } else {
                if (treeNode.getRight() == null)
                    return treeNode.getLeft();
                else if (treeNode.getLeft() == null)
                    return treeNode.getRight();
                else
                    return null;
            }
        }
    }

    /**
     * 蛇形输出二叉树
     *
     * @param treeNode
     * @return
     */
    public static List<Integer> serpentineOutputBinaryTree(TreeNode treeNode) {
        List<Integer> res = new ArrayList<>();
        if (treeNode == null) {
            return res;
        }
        int step = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(treeNode);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                tmp.add(poll.getVal());
                if (poll.getLeft() != null) {
                    queue.add(poll.getLeft());
                }
                if (poll.getRight() != null) {
                    queue.add(poll.getRight());
                }
            }
            if (step % 2 == 0) {
                Collections.reverse(tmp);
            }
            res.addAll(tmp);
            step++;
        }
        return res;
    }
}
