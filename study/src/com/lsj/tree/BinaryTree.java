package com.lsj.tree;


import com.lsj.lineartable.ListNode;

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
            if (!stack.isEmpty()) {
                treeNode = stack.pop();
                treeNode = treeNode.getRight();
            }
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
     * 层序遍历 不需要分层
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
        if (root == null) {
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
                list.add(poll.val);
                // 当前节点的左右子树入队，他们属于下一层，不会在这层遍历
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 蛇形输出二叉树
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> serpentineOutputBinaryTree(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        int step = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    tmp.add(poll.val);
                    if (poll.left != null) {
                        queue.add(poll.left);
                    }
                    if (poll.right != null) {
                        queue.add(poll.right);
                    }
                }
            }
            if (step % 2 == 0) {
                Collections.reverse(tmp);
            }
            res.add(tmp);
            step++;
        }
        return res;
    }

    /**
     * 层序遍历 dfs
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderDfs(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
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
     * @param root
     * @param min
     * @param max
     * @return
     */
    public static Boolean isBinaryTree(TreeNode root, Integer min, Integer max) {
        // 空树 或者到叶子节点了 返回
        if (root == null) {
            return true;
        }
        // 左子树必须小于根节点
        if (min != null && root.getVal() <= min) {
            return false;
        }
        // 右子树必须大于根节点
        if (max != null && root.getVal() >= max) {
            return false;
        }
        // 递归判断当前树的左右子树
        return isBinaryTree(root.getLeft(), min, root.getVal())
                && isBinaryTree(root.getRight(), root.getVal(), max);
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
        if (root == null) {
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
        if (root == null) {
            return 0;
        }
        int ans = 1;
        // BFS
        // 队列控制先进先出
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int level = queue.size();
            for (int i = 0; i < level; i++) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    // 某个节点的左右节点都为空，说明他是叶子节点 最小深度的节点就是它
                    if (poll.getLeft() == null || poll.getRight() == null) {
                        queue.clear();
                        break;
                    }
                    if (poll.getLeft() != null) {
                        queue.add(poll.getLeft());
                    }
                    if (poll.getRight() != null) {
                        queue.add(poll.getRight());
                    }
                }
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
     *
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
     * 查询二叉搜索数的最小值
     *
     * @param treeNode
     * @return
     */
    public static TreeNode findMinTreeNode(TreeNode treeNode) {
        if (treeNode == null) {
            return null;
        }
        while (treeNode.getLeft() != null) {
            treeNode = treeNode.getLeft();
        }
        return treeNode;
    }


    /**
     * 查询二叉搜索树的最大值
     *
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
     * 二叉搜索树上查询某个元素 递归 和 循环
     *
     * @param treeNode
     * @param element
     * @return
     */
    public static TreeNode findTreeNode(TreeNode treeNode, int element) {
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
     *
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
     *
     * @param treeNode
     * @param element
     * @return
     */
    public static TreeNode deleteTreeNode(TreeNode treeNode, int element) {
        if (treeNode == null) {
            return null;
        } else if (element > treeNode.getVal()) {
            return deleteTreeNode(treeNode.getRight(), element);
        } else if (element < treeNode.getVal()) {
            return deleteTreeNode(treeNode.getLeft(), element);
        } else {
            if (treeNode.getRight() == null)
                return treeNode.getLeft();
            else if (treeNode.getLeft() == null)
                return treeNode.getRight();
            else {
                // 找右子树中最小的元素来当新的根节点
                TreeNode min = findMinTreeNode(treeNode.getRight());
                TreeNode right = deleteTreeNode(treeNode.getRight(), min.val);
                min.setLeft(treeNode.getLeft());
                min.setRight(right);
                return min;
            }
        }
    }

    /**
     * 根据前序遍历和中序遍历的数组 重建二叉树
     *
     * @param pre
     * @param inner
     * @return
     */
    public TreeNode rebuildTreeWithPreAndInner(int[] pre, int[] inner) {
        // 如果前中遍历的数组都为空，直接返回
        if (pre == null || inner == null) {
            return null;
        }
        // 构建二叉树
        return rebuildTreeCore(pre, 0, pre.length - 1,
                inner, 0, inner.length - 1);
    }

    /**
     * 重建二叉树的核心方法
     *
     * @param pre        前序遍历的数组
     * @param startPre   前序遍历数组中基于某个根节点的子树的起始位置
     * @param endPre     前序遍历数组中基于某个根节点的子树的末尾位置
     * @param inner      中序遍历的数组
     * @param startInner 中序遍历的数组 基于某个根节点的子树的起始位置
     * @param endInner   中序遍历的数组 基于某个根节点的子树的末尾位置
     * @return 二叉树的根节点
     */
    private TreeNode rebuildTreeCore(int[] pre, int startPre, int endPre,
                                     int[] inner, int startInner, int endInner) {
        if (startPre > endPre || startInner > endInner) {
            return null;
        }
        // 前序遍历的第一个数字即是根节点
        TreeNode root = new TreeNode(pre[startPre]);
        // 从中序遍历中找到根节点，当中序遍历数组中的值和前序遍历的第一个值相等，说明中序遍历的数组中的那个值的即为根节点，那它前面的值都属于左子树
        for (int i = startInner; i <= endInner; i++) {
            // 找到中序遍历的根节点 每次都要循环去找根节点？那干嘛不用map存起来 👇🏻的解法就是用map存起来
            if (pre[startPre] == inner[i]) {
                // i - startInner 表示左子树有多少个元素

                // 构建左子树

                // 对于前序遍历的数组来说，根节点已经找到了(startPre)，左子树从他下一个开始，所以startPre = startPre + 1
                // 因为左子树上一共有 i - startInner 个值，所以前序遍历的末尾是 startPre + (i - startInner)

                // 对于中序遍历的数组来说，根节点找到了(i)，左子树的起始位置还是不变(startInner)，左子树的末尾位置是 i - 1
                root.setLeft(rebuildTreeCore(pre, startPre + 1, startPre + (i - startInner),
                        inner, startInner, i - 1));
                // 构建右子树

                // 对于前序遍历的数组来说，根节点一直都是(startPre)，已经找到了左子树的所有元素（上面构造左子树的时候，末尾位置是 startPre + (i - startInner)），
                // 剩下的元素都属于右子树，所以右子树的起始位置就是 startPre + (i - startInner) + 1，右子树的末尾位置是 endPre

                // 对于中序遍历的数组来说，根节点找到了(i) 右子树的起始位置就是 i+1，末尾位置不变 endInner
                root.setRight(rebuildTreeCore(pre, startPre + (i - startInner) + 1, endPre,
                        inner, i + 1, endInner));
            }
        }
        return root;
    }

    private TreeNode rebuildTreeCore(int[] pre, int startPre, int endPre,
                                     int[] inner, int startInner, int endInner, Map<Integer, Integer> map) {
        if (startPre > endPre || startInner > endInner) {
            return null;
        }
        // 前序遍历的第一个数字即是根节点
        TreeNode root = new TreeNode(pre[startPre]);
        // 直接获取根节点在中序遍历的位置
        Integer i = map.get(pre[startPre]);
        // 从中序遍历中找到根节点，当中序遍历数组中的值和前序遍历的第一个值相等，说明中序遍历的数组中的那个值的即为根节点，那它前面的值都属于左子树
        // i - startInner 表示左子树有多少个元素

        // 构建左子树

        // 对于前序遍历的数组来说，根节点已经找到了(startPre)，左子树从他下一个开始，所以startPre = startPre + 1
        // 因为左子树上一共有 i - startInner 个值，所以前序遍历的末尾是 startPre + (i - startInner)

        // 对于中序遍历的数组来说，根节点找到了(i)，左子树的起始位置还是不变(startInner)，左子树的末尾位置是 i - 1
        root.setLeft(rebuildTreeCore(pre, startPre + 1, startPre + (i - startInner),
                inner, startInner, i - 1));
        // 构建右子树

        // 对于前序遍历的数组来说，根节点一直都是(startPre)，已经找到了左子树的所有元素（上面构造左子树的时候，末尾位置是 startPre + (i - startInner)），
        // 剩下的元素都属于右子树，所以右子树的起始位置就是 startPre + (i - startInner) + 1，右子树的末尾位置是 endPre

        // 对于中序遍历的数组来说，根节点找到了(i) 右子树的起始位置就是 i+1，末尾位置不变 endInner
        root.setRight(rebuildTreeCore(pre, startPre + (i - startInner) + 1, endPre,
                inner, i + 1, endInner));
        return root;
    }


    /**
     * 根据 中序遍历和后序遍历 重建二叉树
     *
     * @param inner
     * @param startInner
     * @param endInner
     * @param order
     * @param startOrder
     * @param endOrder
     * @return
     */
    private TreeNode rebuildTreeNodeCore(int[] inner, int startInner, int endInner,
                                         int[] order, int startOrder, int endOrder) {
        if (startInner > endInner || startOrder > endOrder) {
            return null;
        }
        TreeNode root = new TreeNode(order[endOrder]);
        for (int i = startInner; i <= endInner; i++) {
            if (inner[i] == order[endOrder]) {
                // i - startInner 表示左子树有多少个元素

                // endOrder = startOrder - 1 + (i - startInner)，为什么要减1，因为包含了起始元素(同理 构建右子树的时候，不用加一)
                root.setLeft(rebuildTreeNodeCore(inner, startInner, i - 1,
                        order, startOrder, startOrder - 1 + (i - startInner)));
                root.setRight(rebuildTreeNodeCore(inner, i + 1, endInner,
                        order, startOrder + (i - startInner), endOrder - 1));
            }
        }
        return root;
    }


    /**
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
     *
     * @param root
     * @return
     */
    private TreeNode mirrorTree(TreeNode root) {
        if (root == null || (root.getLeft() == null && root.getRight() == null)) {
            return root;
        }
        // 每个根节点进来，都是要左右节点交换，先认为递归已经帮你做好了，那就是改变左右节点就行了
        TreeNode right = mirrorTree(root.getLeft());
        TreeNode left = mirrorTree(root.getRight());
        root.setLeft(right);
        root.setRight(left);
        return root;
    }

    /**
     * 判断二叉树是否是对称二叉树
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null && rightNode == null) {
            return true;
        }
        if (leftNode == null || rightNode == null || leftNode.val != rightNode.val) {
            return false;
        }
        return helper(leftNode.right, rightNode.left) && helper(leftNode.left, rightNode.right);
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
     * <p>
     *  
     * <p>
     * 参考以下这颗二叉搜索树：
     * <p>
     * 5
     * / \
     * 2   6
     * / \
     * 1   3
     * 示例 1：
     * <p>
     * 输入: [1,6,3,2,5]
     * 输出: false
     * 示例 2：
     * <p>
     * 输入: [1,3,2,6,5]
     * 输出: true
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param postorder
     * @return
     */
    public static boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return true;
        }
        return helper(postorder, 0, postorder.length - 1);
    }

    /**
     * 把数组分成一小部分，每部分是一个小的二叉树，用递归去解决每一棵小二叉树的问题
     * 后序遍历，最后一个是根节点，所以根据最后一个节点（前提是二叉搜索树），把树分成两部分
     *
     * @param order
     * @param start 某棵二叉树的起始节点
     * @param end   某棵二叉树的末尾节点
     * @return
     */
    private static boolean helper(int[] order, int start, int end) {
        // 终止条件，起始节点和末尾节点位置重合
        if (start >= end) {
            return true;
        }
        // 寻找中间节点（二叉搜索树，前半部分比根节点小，后半部分比根节点大）
        int mid = start;
        while (order[mid] < order[end]) {
            mid++;
        }
        // 此时order[mid] 已经比根节点大了，所以后面递归左子树时，mid-1
        int tmp = mid;
        while (tmp < end) {
            // 如果右子树中有比根节点小的，说明不符合，直接返回false
            if (order[tmp++] < order[end]) {
                return false;
            }
        }
        // 递归查询本次分好的左右两棵子树
        // 右子树的起点是mid,末尾节点是根节点的前一个节点
        // 左子树的起点永远都不会变，末尾节点是mid-1
        return helper(order, mid, end - 1) && helper(order, start, mid - 1);
    }


    /**
     * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
     * <p>
     *  
     * <p>
     * 示例:
     * 给定如下二叉树，以及目标和 target = 22，
     * <p>
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \    / \
     * 7    2  5   1
     * 返回:
     * <p>
     * [
     * [5,4,11,2],
     * [5,8,4,5]
     * ]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        helper(root, sum);
        return res;
    }

    void helper(TreeNode root, int tar) {
        // 空节点，return
        if (root == null) return;
        // 做选择，把当前节点加入路径
        path.add(root.val);
        // 目标值减少
        tar -= root.val;
        // 目标值为0且当前节点是叶子节点
        if (tar == 0 && root.left == null && root.right == null)
            // 必须new，不然就被后面的影响了
            res.add(new LinkedList<>(path));
        // 向下继续找
        helper(root.left, tar);
        helper(root.right, tar);
        // 向下找完后，把一开始做选择的那个值去掉，不能影响后续的试探
        path.removeLast();
    }

    /**
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * <p>
     *  
     * <p>
     * 示例 :
     * 给定二叉树
     * <p>
     * 1
     * / \
     * 2   3
     * / \
     * 4   5
     * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
     * <p>
     *  
     * <p>
     * 注意：两结点之间的路径长度是以它们之间边的数目表示。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    private int ans;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        depth(root);
        return ans;
    }

    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 计算左右节点的深度
        int left = depth(root.left);
        int right = depth(root.right);
        // 左右节点深度相加就是以当前节点为根的直径
        ans = Math.max(ans, left + right);
        // 返回当前节点为根的最大深度
        return Math.max(left, right) + 1;
    }

    /**
     * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
     * <p>
     * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * Tree 1                     Tree 2
     * 1                         2
     * / \                       / \
     * 3   2                     1   3
     * /                           \   \
     * 5                             4   7
     * 输出:
     * 合并后的树:
     * 3
     * / \
     * 4   5
     * / \   \
     * 5   4   7
     * 注意: 合并必须从两个树的根节点开始。
     * <p>
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-two-binary-trees
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root1
     * @param root2
     * @return
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }


    /**
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * <p>
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：root = [1,2,5,3,4,null,6]
     * 输出：[1,null,2,null,3,null,4,null,5,null,6]
     * 示例 2：
     * <p>
     * 输入：root = []
     * 输出：[]
     * 示例 3：
     * <p>
     * 输入：root = [0]
     * 输出：[0]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    // 辅助节点
    private TreeNode resNode;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;

        // 最后只能有右边 所以把左边置为null
        resNode.left = null;
        // 最终节点的右边等于当前节点
        resNode.right = root;
        // 递归 更新
        resNode = root;

        flatten(left);
        flatten(right);
    }

    public void flatten_2(TreeNode root) {
        while (root != null) {
            if (root.left == null) {
                root = root.right;
                continue;
            }
            // 找到左子树的最右叶子节点
            TreeNode pre = root.left;
            while (pre.right != null) {
                pre = pre.right;
            }
            // 把右子树接到左子树的最优子节点
            pre.right = root.right;
            // 左子树换到右子树
            root.right = root.left;
            root.left = null;
            // 处理右子树即可
            root = root.right;
        }
    }


    /**
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
     * <p>
     * struct Node {
     * int val;
     * Node *left;
     * Node *right;
     * Node *next;
     * }
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * <p>
     * 初始状态下，所有 next 指针都被设置为 NULL。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/populating-next-right-pointers-in-each-node
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public TreeNode connect(TreeNode root) {
        if (root == null) {
            return null;
        }
        connect(root.left, root.right);
        return root;
    }

    private void connect(TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return;
        }
        // 把传入的节点链接起来
        node1.next = node2;
        // 相同父节点的子节点链接起来
        connect(node1.left, node1.right);
        connect(node2.left, node2.right);
        // 两个节点不属于同一个根节点，node1的右子节点链接node2的左子节点
        connect(node1.right, node2.left);
    }

    // 这个更快，也更好理解
    public TreeNode connect_2(TreeNode root) {
        if (root == null) {
            return null;
        }
        connect_bfs(root);
        return root;
    }

    private void connect_bfs(TreeNode root) {
        if (root.left == null || root.right == null) {
            return;
        }
        // 链接同一个根节点的左右节点
        root.left.next = root.right;
        // 如果根节点的有右邻居(说明跨节点了)，需要把根节点的右节点链接到右邻居的左节点上
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        // 继续做遍历就行了
        connect_bfs(root.left);
        connect_bfs(root.right);
    }

    /**
     * 翻转二叉树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 递归
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;
        return root;
    }


    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return build(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
        if (preStart > preEnd || postStart > postEnd) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }
        // 先构造根节点
        TreeNode root = new TreeNode(preorder[preStart]);
        // index 代表左子树的根节点在后序遍历中的位置
        int index = -1;
        // 前序遍历的第二个节点是左子树的根节点
        // 可以优化一下 hashmap 存放位置
        int leftRoot = preorder[preStart + 1];
        for (int i = postStart; i <= postEnd; i++) {
            if (leftRoot == postorder[i]) {
                index = i;
            }
        }
        // 算出左子树有多少个元素
        int leftSize = index - postStart + 1;
        // 构造左右子树
        root.left = build(preorder, preStart + 1, preStart + leftSize, postorder, postStart, index);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, postorder, index + 1, preEnd - 1);

        return root;
    }

    /**
     * 中序和后序遍历构造二叉树
     *
     * @param innerOrder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] innerOrder, int[] postorder) {
        return build_2(innerOrder, 0, innerOrder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build_2(int[] innerOrder, int innerStart, int innerEnd, int[] postorder, int postStart, int postEnd) {
        if (innerStart > innerEnd || postStart > postEnd) {
            return null;
        }
        if (innerStart == innerEnd) {
            return new TreeNode(innerOrder[innerStart]);
        }
        // 先构造根节点
        TreeNode root = new TreeNode(postorder[postEnd]);
        // index 代表根节点在中序遍历中的位置
        int index = -1;
        // 寻找根节点在中序遍历的位置
        for (int i = innerStart; i <= innerEnd; i++) {
            if (postorder[postEnd] == innerOrder[i]) {
                index = i;
            }
        }
        // 算出左子树有多少个元素,
        int leftSize = index - innerStart;
        // 构造左右子树
        root.left = build(innerOrder, innerStart, index - 1, postorder, postStart, postStart + leftSize - 1);
        root.right = build(innerOrder, index + 1, innerEnd, postorder, postStart + leftSize, postEnd - 1);

        return root;
    }

    /**
     * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。
     * 树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
     * <p>
     * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * <p>
     * 1
     * /   \
     * 3     2
     * / \     \
     * 5   3     9
     * <p>
     * 输出: 4
     * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
     * 示例 2:
     * <p>
     * 输入:
     * <p>
     * 1
     * /
     * 3
     * / \
     * 5   3
     * <p>
     * 输出: 2
     * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
     * 示例 3:
     * <p>
     * 输入:
     * <p>
     * 1
     * / \
     * 3   2
     * /
     * 5
     * <p>
     * 输出: 2
     * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
     * 示例 4:
     * <p>
     * 输入:
     * <p>
     * 1
     * / \
     * 3   2
     * /     \
     * 5       9
     * /         \
     * 6           7
     * 输出: 8
     * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximum-width-of-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    class SeqNode {
        public TreeNode node;
        public int seq;

        public SeqNode(TreeNode node, int seq) {
            this.node = node;
            this.seq = seq;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        // 计算一个二叉树的宽度，最先想到的就是bfs 因为bfs能知道每一行的情况，但是怎么记录宽度呢
        // 如果能给每个子节点编号就好了，拿最右测的编号减去最左侧的编号，就是宽度
        // 但是怎么编号呢，仔细想想如果是一颗满二叉树，根节点是x,左节点是不是2x,右节点是2x+1,刚好bfs 是先知道根节点，然后左右节点(编号)入栈，完美符合要求
        int res = 0;
        if (root == null) {
            return res;
        }
        // 加一个新结构记录编号
        SeqNode rootNode = new SeqNode(root, 1);
        Queue<SeqNode> queue = new LinkedList<>();
        queue.add(rootNode);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // start 和 end 记录这一层节点的起始
            int start = 0;
            int end = 0;
            for (int i = 0; i < size; i++) {
                SeqNode poll = queue.poll();
                if (i == 0) {
                    start = poll.seq;
                }
                if (i == size - 1) {
                    end = poll.seq;
                }
                // 记录左右节点的编号 加到队列中
                if (poll.node.left != null) {
                    queue.add(new SeqNode(poll.node.left, 2 * poll.seq));
                }
                if (poll.node.right != null) {
                    queue.add(new SeqNode(poll.node.right, 2 * poll.seq + 1));
                }
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }

    /**
     * 二叉树的序列化和反序列化
     *
     * @param root
     * @return
     */
    String sep = ",";
    String nill = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        // 按前序遍历输出，空节点用#表示
        StringBuilder sb = new StringBuilder();
        build(root, sb);
        return sb.toString();
    }

    private void build(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(nill).append(sep);
            return;
        }
        sb.append(root.val).append(sep);
        build(root.left, sb);
        build(root.right, sb);
    }

    // 之前需要前序和中序遍历才行，因为会担心有空节点不知道在哪
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (null == data) {
            return null;
        }
        LinkedList<String> nodes = new LinkedList<>(Arrays.asList(data.split(sep)));
        return build(nodes);
    }

    private TreeNode build(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        // 列表中第一个元素即是根节点
        String first = nodes.removeFirst();
        // 当遇到 null 时 说明没有了
        if (first.equals(nill)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.left = build(nodes);
        root.right = build(nodes);
        return root;
    }

    /**
     * 给定一棵二叉树 root，返回所有重复的子树。
     * <p>
     * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
     * <p>
     * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：root = [1,2,3,4,null,2,4,null,null,4]
     * 输出：[[2,4],[4]]
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/find-duplicate-subtrees
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    Map<String, Integer> subTreeMap = new HashMap<>();
    List<TreeNode> subTreeNodes = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // 寻找重复子树？那我怎么知道他有没有重复呢？
        // 需要有个东西帮忙记录每一棵子树重现的次数，所以需要一个map,value用来存次数，key 存什么呢？直接存root?
        // 直接存节点怎么比较？二叉树是二维结构，不好比较。所以不行，那能不能把这个节点拉直？这不就是之前学的序列化吗？所以key存某个节点为根的树的序列化结果
        // 那怎么序列化比较好呢？前序肯定不行，因为前序只知道根节点的信息，不知道子节点的信息，所以在后序的位置进行操作
        getTreeString(root);
        return subTreeNodes;
    }

    /**
     * 返回传入的节点序列化字符串
     *
     * @param root
     * @return
     */
    private String getTreeString(TreeNode root) {
        if (root == null) {
            return "#";
        }
        StringBuilder sb = new StringBuilder();

        String left = getTreeString(root.left);
        String right = getTreeString(root.right);

        String rootString = sb.append(root.val).append(",").append(left).append(",").append(right).toString();
        // subTreeMap 记录的是某个根节点的序列化字符串出现的次数
        Integer num = subTreeMap.getOrDefault(rootString, 0);
        // 第一次出现重复，说明已经找到了，加到结果里，后面遇到重复也不加了
        if (num == 1) {
            subTreeNodes.add(root);
        }
        subTreeMap.put(rootString, num + 1);
        return rootString;
    }

    /**
     * 合并两个有序数组
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public static void merge(int[] A, int m, int[] B, int n) {
        // 题目的返回值void 说明最终是要判断A数组的，所以定义一个临时数组存原来A数组的元素
        int[] tmp = new int[m];
        for (int i = 0; i < m; i++) {
            tmp[i] = A[i];
        }
        int aStart = 0;
        int bStart = 0;
        for (int i = 0; i < A.length; i++) {
            if (aStart == m) {
                // 说明A数组的元素全部用完了，全部去B数组的元素就行了
                A[i] = B[bStart++];
            } else if (bStart == n) {
                // 说明B数组的元素全部用完了，全部去A数组的元素就行了
                A[i] = tmp[aStart++];
            } else if (tmp[aStart] < B[bStart]) {
                // 两个数组中的哪个元素小就取哪个，取完之后前进一位
                A[i] = tmp[aStart++];
            } else {
                A[i] = B[bStart++];
            }
        }
    }


    /**
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
     * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * <p>
     * 一般来说，删除节点可分为两个步骤：
     * <p>
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     *  
     * <p>
     * 示例 1:
     * <p>
     * <p>
     * <p>
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/delete-node-in-a-bst
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        // 删除的节点是根节点
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                // 刚好是叶子节点，直接删除
                return null;
            }
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            // 左子树和右子树都有
            // 那就找右子树最小的或者左子树最大的来当根节点
            TreeNode max = getMax(root.left);
            // 把右子树中最小的那个删掉
            max.left = deleteNode(root.left, max.val);
            // 左子树拼接到新的根节点
            max.right = root.right;
            return max;

        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    private TreeNode getMax(TreeNode root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    /**
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：n = 3
     * 输出：5
     * 示例 2：
     * <p>
     * 输入：n = 1
     * 输出：1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/unique-binary-search-trees
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    int[][] mem;

    public int numTrees(int n) {
        mem = new int[n][n];
        return numTrees(1, n);
    }

    /**
     * 在[low, hi] 闭区间内二叉搜索树的个数
     *
     * @param low
     * @param hi
     * @return
     */
    private int numTrees(int low, int hi) {
        // 是个 null 也属于一种情况
        if (low > hi) {
            return 1;
        }
        if (mem[low][hi] != 0) {
            return mem[low][hi];
        }
        int res = 0;
        // 计算以 i 为根节点的二叉搜索树的个数
        for (int i = low; i <= hi; i++) {
            int left = numTrees(low, i - 1);
            int right = numTrees(i + 1, hi);
            // 左右子树的个数 相乘 即是 结果
            res += left * right;
        }
        mem[low][hi] = res;
        return res;
    }

    /**
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：n = 3
     * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
     * 示例 2：
     * <p>
     * 输入：n = 1
     * 输出：[[1]]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/unique-binary-search-trees-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return generateTrees(1, n);
    }

    /**
     * 生成 [low, hi] 区间内的二叉搜索树
     *
     * @param low
     * @param hi
     * @return
     */
    // 函数定义：生成某个区间内的所有二叉搜索树
    private List<TreeNode> generateTrees(int low, int hi) {
        List<TreeNode> res = new LinkedList<>();
        if (low > hi) {
            res.add(null);
            return res;
        }

        for (int i = low; i <= hi; i++) {
            List<TreeNode> leftNodes = generateTrees(low, i - 1);
            List<TreeNode> rightNodes = generateTrees(i + 1, hi);
            for (TreeNode left : leftNodes) {
                for (TreeNode right : rightNodes) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }

        return res;
    }

    public void sort(int[] nums, int low, int hi) {
        if (low > hi) {
            return;
        }
        int mid = findMid(nums, low, hi);

        sort(nums, low, mid - 1);
        sort(nums, mid + 1, hi);
    }

    private int findMid(int[] nums, int low, int hi) {
        if (low >= hi) {
            return low;
        }
        int i = low + 1;
        int j = hi;
        int pivot = nums[low];
        while (i <= j) {
            while (i < j && nums[j] > pivot) {
                // 跳出循环时 nums[j] <= pivot
                j--;
            }
            while (i < j && nums[i] <= pivot) {
                // 跳出循环时 nums[i] > pivot
                i++;
            }
            // 经过上面两个循环后，i 可能大于 j，此时 nums[j] <= pivot, nums[i] > pivot
            // 那是不是可以跳出大循环 把 pivot 和 j 的元素换一下就行了
            if (i > j) {
                break;
            }
            swap(nums, i, j);
        }
        swap(nums, low, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    /**
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     * <p>
     * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
     * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/count-complete-tree-nodes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lh = 0;
        int rh = 0;
        // 计算左右子树的树高
        TreeNode left = root;
        while (left != null) {
            lh++;
            left = left.left;
        }
        TreeNode right = root;
        while (right != null) {
            rh++;
            right = right.right;
        }
        // 树高相同，说明是满二叉树，直接用公式
        if (lh == rh) {
            return (int) (Math.pow(2, lh) - 1);
        }
        // 树高不同，只能硬算
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * 给定一个二叉搜索树的 根节点 root 和一个整数 k , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 k 。假设二叉搜索树中节点的值均唯一。
     *
     *  
     *
     * 示例 1：
     *
     * 输入: root = [8,6,10,5,7,9,11], k = 12
     * 输出: true
     * 解释: 节点 5 和节点 7 之和等于 12
     * 示例 2：
     *
     * 输入: root = [8,6,10,5,7,9,11], k = 22
     * 输出: false
     * 解释: 不存在两个节点值之和为 22 的节点
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/opLdQZ
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    Set<Integer> targetSet = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        int val = root.val;
        int tmp = k - val;
        if (targetSet.contains(tmp)) {
            return true;
        }
        targetSet.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }


    /**
     * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * 输出: 4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<Integer> reskthLargest = new ArrayList<>();
    public int kthLargest(TreeNode root, int k) {
        sort(root);
        return reskthLargest.get(k - 1);
    }

    private void sort(TreeNode root) {
        if (root == null) {
            return;
        }
        sort(root.right);
        reskthLargest.add(root.val);
        sort(root.left);
    }

    /**
     * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
     *
     * 节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/P5rCT8
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @param p
     * @return
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        // 目标节点有右子树，那后继节点就是右子树中最左边的那个
        if (p.right != null) {
            TreeNode right = p.right;
            while (right.left != null) {
                right = right.left;
            }
            return right;
        }
        TreeNode res = null;
        TreeNode node = root;
        while (node != null) {
            // 当前节点大于目标节点，那就去左子树找
            // 因为 p 的后继是肯定比 p 大的，多以node > p 的时候，都有可能是p 的后继节点
            if (node.val > p.val) {
                res = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return res;
    }

    /**
     * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，
     * 使得所有节点的值在[low, high]中。修剪树 不应该 改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案 。
     *
     * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/trim-a-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            // 小于最小值的直接不要
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            // 大于最大值的直接不要
            return trimBST(root.left, low, high);
        } else {
            // [low, high] 区间的让子树去决定
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }

    /**
     * 恢复二叉搜索树
     *
     * 给你二叉搜索树的根节点 root ，该树中的 恰好 两个节点的值被错误地交换。请在不改变其结构的情况下，恢复这棵树 。
     *
     * 输入：root = [1,3,null,null,2]
     * 输出：[3,1,null,null,2]
     * 解释：3 不能是 1 的左孩子，因为 3 > 1 。交换 1 和 3 使二叉搜索树有效。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/recover-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     */
    // 判断中序遍历的有序性
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    // 题目强调了 恰好 两个字，说明是 有一个元素是小于它前面的元素，有一个元素大于它后面的元素
    // 是不是中序遍历，排序的过程中找到这两个元素就行了
    TreeNode first = null;
    TreeNode second = null;
    public void recoverTree(TreeNode root) {
        inOrderRecoverTree(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inOrderRecoverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderRecoverTree(root.left);
        // 二叉搜索树的中序遍历肯定是升序的，所以发现比前面的小，肯定是位置放错了
        if (root.val < prev.val) {
            // 题目明确说了只有两个节点放错位置了，
            if (first == null) {
                first = prev;
            }
            // 第一个是前驱节点，第二个就是当前节点
            second = root;
        }
        // 每一个节点都可以是前驱节点
        prev = root;
        inOrderRecoverTree(root.right);
    }

    /**
     * 给你一棵二叉搜索树，请你返回一棵 平衡后 的二叉搜索树，新生成的树应该与原来的树有着相同的节点值。如果有多种构造方法，请你返回任意一种。
     *
     * 如果一棵二叉搜索树中，每个节点的两棵子树高度差不超过 1 ，我们就称这棵二叉搜索树是 平衡的 。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/balance-a-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public TreeNode balanceBST(TreeNode root) {
        // 第一想法就是利用二叉搜索树的特性，先排序，然后重新构造这棵树，想要平衡很简单，就是一直取中间那个数做为根节点
        List<Integer> inOrders = inorder(root, new ArrayList<>());
        return build(inOrders, 0, inOrders.size() - 1);
    }

    private List<Integer> inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return res;
        }
        res.addAll(inorder(root.left, res));
        res.add(root.val);
        res.addAll(inorder(root.right, res));
        return res;
    }

    private TreeNode build(List<Integer> nodes, int low, int hi) {
        if (low > hi) {
            return null;
        }
        int mid = low + (hi - low) / 2;
        TreeNode root = new TreeNode(nodes.get(mid));
        root.left = build(nodes, low, mid - 1);
        root.right = build(nodes, mid + 1, hi);
        return root;
    }


    /**
     * 满二叉树是一类二叉树，其中每个结点恰好有 0 或 2 个子结点。
     *
     * 返回包含 N 个结点的所有可能满二叉树的列表。 答案的每个元素都是一个可能树的根结点。
     *
     * 答案中每个树的每个结点都必须有 node.val=0。
     *
     * 你可以按任何顺序返回树的最终列表。
     *
     *  
     *
     * 示例：
     *
     * 输入：7
     * 输出：[[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
     * 解释：
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/all-possible-full-binary-trees
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    List<TreeNode>[] memTreeNode;
    public List<TreeNode> allPossibleFBT(int n) {
        if (n % 2 == 0) {
            return new ArrayList<>();
        }
        // 和 leetcode 96题很像
        memTreeNode = new ArrayList[n + 1];
        return helper(n);
    }

    private List<TreeNode> helper(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        if (memTreeNode[n] != null) {
            return memTreeNode[n];
        }
        // 满二叉树，所以每次加2
        for (int i = 1; i <= n; i += 2) {
            int j = n - i - 1;
            // 分解成不同子树
            List<TreeNode> leftNodes = helper(i);
            List<TreeNode> rightNodes = helper(j);
            for (TreeNode left : leftNodes) {
                for (TreeNode right : rightNodes) {
                    // 不同的子树排列组合
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        memTreeNode[n] = res;
        return res;
    }

    /**
     * 给定一个二叉树的根 root 和两个整数 val 和 depth ，在给定的深度 depth 处添加一个值为 val 的节点行。
     *
     * 注意，根节点 root 位于深度 1 。
     *
     * 加法规则如下:
     *
     * 给定整数 depth，对于深度为 depth - 1 的每个非空树节点 cur ，创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。
     * cur 原来的左子树应该是新的左子树根的左子树。
     * cur 原来的右子树应该是新的右子树根的右子树。
     * 如果 depth == 1 意味着 depth - 1 根本没有深度，那么创建一个树节点，值 val 作为整个原始树的新根，而原始树就是新根的左子树。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/add-one-row-to-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    int targetVal;
    int depth_1;
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        // 给二叉树加一行，前序遍历的就是在进入节点的时候做一些事情，那就加一行
        if (depth == 1) {
            TreeNode treeNode = new TreeNode(val);
            treeNode.left = root;
            return treeNode;
        }
        targetVal = val;
        depth_1 = depth;
        addOneRowHelper(root);
        return root;
    }

    int curDepth = 0;
    private void addOneRowHelper(TreeNode root) {
        if (root == null) {
            return;
        }
        // 进来 深度加一
        curDepth++;
        // 当遇到 目标深度前一的时候 开始做操作
        if (depth_1 - 1 == curDepth) {
            TreeNode left = new TreeNode(targetVal);
            TreeNode right = new TreeNode(targetVal);
            left.left = root.left;
            right.right = root.right;
            root.left = left;
            root.right = right;
        }
        // 不是的话继续向下找
        addOneRowHelper(root.left);
        addOneRowHelper(root.right);
        // 这次执行的操作 不能影响别人
        // 后序遍历位置 执行的操作等于你出这个节点的时候做什么，进来的时候深度增加了，出去的时候还原
        curDepth--;
    }

    /**
     * 给定一个单链表的头节点  head ，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
     *
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差不超过 1。
     *
     *  
     *
     * 示例 1:
     *
     *
     *
     * 输入: head = [-10,-3,0,5,9]
     * 输出: [0,-3,9,-10,null,5]
     * 解释: 一个可能的答案是[0，-3,9，-10,null,5]，它表示所示的高度平衡的二叉搜索树。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        return sortedList(head, null);
    }

    /**
     * 返回制定链表的高度平衡二叉搜索树
     * 高度平衡，那肯定是对半分比较好
     * 把链表左闭右开区间 [begin, end) 的节点构造成 BST
     * @param begin
     * @param end
     * @return
     */
    private TreeNode sortedList(ListNode begin, ListNode end) {
        if (begin == end) {
            return null;
        }
        // 快慢指针找到列表的中间节点
        ListNode mid = getMid(begin, end);
        // 中间节点即是 根节点
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedList(begin, mid);
        root.right = sortedList(mid.next, end);
        return root;
    }

    // 找某个区间的中心节点 [begin, end）左开右闭
    private ListNode getMid(ListNode begin, ListNode end) {
        ListNode slow = begin, fast = begin;
        while (fast != end && fast.next != end) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    List<List<Integer>> permuteRes = new LinkedList<>();
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) {
            return permuteRes;
        }
        backTrack(nums, new LinkedList<>());
        return permuteRes;
    }

    private void backTrack(int[] nums, LinkedList<Integer> cur) {
        if (cur.size() == nums.length) {
            permuteRes.add(new LinkedList<>(cur));
            return ;
        }
        for (int i = 0; i < nums.length; i++) {
            if (cur.contains(nums[i])) {
                continue;
            }
            cur.add(nums[i]);
            backTrack(nums, cur);
            cur.removeLast();
        }
    }

    /**
     * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
     * 每条从根节点到叶节点的路径都代表一个数字：
     *
     * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
     * 计算从根节点到叶节点生成的 所有数字之和 。
     *
     * 叶节点 是指没有子节点的节点。
     *
     *  
     *
     * 示例 1：
     *
     *
     * 输入：root = [1,2,3]
     * 输出：25
     * 解释：
     * 从根到叶子节点路径 1->2 代表数字 12
     * 从根到叶子节点路径 1->3 代表数字 13
     * 因此，数字总和 = 12 + 13 = 25
     * 示例 2：
     *
     *
     * 输入：root = [4,9,0,5,1]
     * 输出：1026
     * 解释：
     * 从根到叶子节点路径 4->9->5 代表数字 495
     * 从根到叶子节点路径 4->9->1 代表数字 491
     * 从根到叶子节点路径 4->0 代表数字 40
     * 因此，数字总和 = 495 + 491 + 40 = 1026
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/sum-root-to-leaf-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    StringBuilder sumNumbersPath = new StringBuilder();
    int sumNumbers = 0;
    public int sumNumbers(TreeNode root) {
        traverse(root);
        return sumNumbers;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        // 前序位置 记录根节点
        sumNumbersPath.append(root.val);
        // 到了叶子节点 那就说明找到了一个结果
        if (root.left == null && root.right == null) {
            sumNumbers += Integer.parseInt(sumNumbersPath.toString());
            // 注意 不能直接return 你选择了当前节点 不能影响后面的遍历，需要删掉才行，这里如果想直接return 那就要执行path的删除
        }
        traverse(root.left);
        traverse(root.right);
        // 把选择的节点删除，回溯思想
        sumNumbersPath.deleteCharAt(sumNumbersPath.length() - 1);
    }

}
