package com.lsj.lineartable;

public class Link {

    public void outNode(ListNode listNode) {
        if (listNode == null) {
            System.out.println("node is null");
            return;
        }
        System.out.println(listNode.val);
        while (listNode.next != null) {
            ListNode nextListNode = listNode.next;
            System.out.println(nextListNode.val);
            listNode = nextListNode;
        }
    }

    public void outNodeValue(ListNode listNode) {
        if (listNode == null) {
            System.out.println("node is null");
            return;
        }
        System.out.println(listNode.val);
    }

    /**
     * @param listNode 原链表
     * @param i    插入的位置 i >= 1
     * @param n    插入的node的值
     * @return
     */
    public ListNode insert(ListNode listNode, int i, int n) {
        ListNode newListNode = new ListNode(n);
        if (i == 0) {
            newListNode.next = listNode;
            return newListNode;
        } else {
//            Node nextNode = node.next;
//            Node curNode = node;
//            while (curNode.next != null && i > 1) {
//                curNode = curNode.next;
//                nextNode = curNode.next;
//                i--;
//            }
//            curNode.next = newNode;
//            newNode.next = nextNode;
            ListNode curListNode = findByIndex(listNode, i-1);
            ListNode nextListNode = curListNode.next;
            curListNode.next = newListNode;
            newListNode.next = nextListNode;
        }
        return listNode;
    }

    /**
     * 查找链表是否包含该值，包含的话返回位置
     *
     * @param listNode
     * @param n    查找的node的值
     * @return
     */
    public int findByValue(ListNode listNode, int n) {
        if (null == listNode) {
            return -1;
        }
        int i = 0;
        while (listNode.next != null && listNode.val != n) {
            listNode = listNode.next;
            i++;
        }
        if (listNode.val == n) {
            return i;
        } else {
            return -1;
        }
    }

    public ListNode findByIndex(ListNode listNode, int i) {
        if (listNode == null) {
            return null;
        }
        while (listNode.next != null && i > 0) {
            listNode = listNode.next;
            i--;
        }
        if (listNode.next == null && i > 0) {
            return null;
        }
        return listNode;
    }

    public ListNode removeNode(ListNode listNode, int i) {
        if (listNode == null) {
            return null;
        }
        if (i == 0) {
            listNode = listNode.next;
            return listNode;
        }
        ListNode preListNode = findByIndex(listNode, i - 1);
        if (preListNode == null) {
            return listNode;
        }
        ListNode curListNode = preListNode.next;
        if (curListNode == null || curListNode.next == null){
            preListNode.next = null;
            return listNode;
        }
        preListNode.next = curListNode.next;
        return listNode;
    }
}
