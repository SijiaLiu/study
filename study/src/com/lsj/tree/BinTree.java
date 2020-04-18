package com.lsj.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * class_name: BinTree
 * package: algorithm
 * describe: 二叉树
 *
 * @author liusijia
 * @Date 2019/1/29
 **/

public class BinTree {

    private BinTree lChild;//左孩子
    private BinTree rChild;//右孩子
    private BinTree root;//根节点
    private Object data; //数据域
    private List<BinTree> datas;//存储所有的节点

    public BinTree(BinTree lChild, BinTree rChild, Object data) {
        super();
        this.lChild = lChild;
        this.rChild = rChild;
        this.data = data;
    }

    public BinTree(Object data) {
        this(null, null, data);
    }

    public BinTree() {
        super();
    }

    /**
     * 创建一棵树
     * @param objs
     */
    public void createTree(Object[] objs) {
        datas = new ArrayList<BinTree>();
        for (Object object : objs) {
            datas.add(new BinTree(object));
        }
        //将第一个作为根节点
        root = datas.get(0);
        for (int i = 0; i < objs.length / 2; i++) {
            datas.get(i).lChild = datas.get(i * 2 + 1);
            //避免偶数的时候 下标越界
            if (i * 2 + 2 < datas.size()) {
                datas.get(i).rChild = datas.get(i * 2 + 2);
            }
        }
    }

    //先序遍历
    public void preOrder(BinTree root) {
        if (root != null) {
            visit(root.getData());
            preOrder(root.lChild);
            preOrder(root.rChild);
        }

    }

    public void preOrderStack(BinTree root) {
        Stack<BinTree> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                visit(root.getData());
                if (root.rChild != null) {
                    stack.push(root.rChild);
                }
                if (root.lChild != null) {
                    stack.push(root.lChild);
                }
            }
        }
    }

    //前序遍历非递归的方式
    public void preOrderNonRecursive(BinTree root) {
        Stack<BinTree> stack = new Stack<BinTree>();
        while (true) {
            while (root != null) {
                visit(root.getData());
                stack.push(root);
                root = root.lChild;
            }
            if (stack.isEmpty())
                break;
            root = stack.pop();
            root = root.rChild;
        }
    }

    //中序遍历
    public void inOrder(BinTree root) {
        if (root != null) {
            inOrder(root.lChild);
            visit(root.getData());
            inOrder(root.rChild);
        }

    }

    //中序遍历采用非递归的方式
    public void inOrderNonRecursive(BinTree root) {
        Stack<BinTree> stack = new Stack<BinTree>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.lChild;
            }
            if (stack.isEmpty())
                break;
            root = stack.pop();
            visit(root.getData());
            root = root.rChild;
        }
    }

    //后序遍历
    public void postOrder(BinTree root) {
        if (root != null) {
            postOrder(root.lChild);
            postOrder(root.rChild);
            visit(root.getData());
        }

    }

    public void postOrderStack(BinTree root) {
        List<Object> list = new ArrayList<>();
        Stack<BinTree> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
            while (!stack.isEmpty()) {
                root = stack.pop();
                list.add(root.data);
                if (root.lChild != null) {
                    stack.push(root.lChild);
                }
                if (root.rChild != null) {
                    stack.push(root.rChild);
                }
            }
        }
        Collections.reverse(list);
        System.out.println(list);
    }

    //后序遍历采用非递归的方式
    public void postOrderNonRecursive(BinTree root) {
        Stack<BinTree> stack = new Stack<BinTree>();
        while (true) {
            if (root != null) {
                stack.push(root);
                root = root.lChild;
            } else {
                if (stack.isEmpty())
                    return;

                if (null == stack.lastElement().rChild) {
                    root = stack.pop();
                    visit(root.getData());
                    while (root == stack.lastElement().rChild) {
                        visit(stack.lastElement().getData());
                        root = stack.pop();
                        if (stack.isEmpty()) {
                            break;
                        }
                    }
                }

                if (!stack.isEmpty())
                    root = stack.lastElement().rChild;
                else
                    root = null;
            }
        }
    }

    private void visit(Object obj) {
        System.out.print(obj + " ");
    }

    public Object getData() {
        return data;
    }

    public BinTree getRoot() {
        return root;
    }

}
