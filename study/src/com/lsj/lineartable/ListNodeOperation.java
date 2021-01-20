package com.lsj.lineartable;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ListNodeOperation {

    // 翻转部分链表的节点后的下一个节点
    private ListNode next;

    /**
     * 翻转单链表 递归
     *
     * @param listNode
     * @return
     */
    public ListNode reverseListNodeRecursive(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }
        // 假设递归已经把头结点后面的全部翻转了，但此时，头结点还是指向它的next，所以需要把头结点的next节点指向头结点
        // 然后头结点指向null
        ListNode reverseListListNode = reverseListNodeRecursive(listNode.next);
        listNode.next.next = listNode;
        listNode.next = null;
        return reverseListListNode;
    }


    /**
     * 翻转前N个节点
     *
     * @param head
     * @return
     */
    public ListNode reverseNListNodeRecursive(ListNode head, int n) {
        if (n == 1) {
            // 当n=1时 说明找到了最终结果的头结点
            // 当前节点的下一个节点是要拼接的节点
            next = head.next;
            return head;
        }
        ListNode res = reverseNListNodeRecursive(head.next, n - 1);
        head.next.next = head;
        head.next = next;
        return res;
    }

    /**
     * 翻转 M-N 个节点的链表
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseNListNodeRecursive(head, n);
        }
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    /**
     * 翻转单链表 迭代
     *
     * @param listNode
     * @return
     */
    public ListNode reverseListNodeIterate(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }
        ListNode preListNode = null;
        // 当前节点不为空 一直走下去
        while (listNode != null) {
            ListNode nextListNode = listNode.next;
            // 当前节点指针指向前一个节点
            listNode.next = preListNode;
            preListNode = listNode;
            // 到达最后一个节点时，node.next == null -》node == null
            listNode = nextListNode;
        }
        // 因为到最后指针会指向null 然后跳出循环 所以返回前一个节点
        return preListNode;
    }

    public void deleteNode(ListNode head, int val) {
        if (head.val == val) {
            head = head.next;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                break;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
    }


    /**
     * 合并两个有序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeNode(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode resListNode = new ListNode(0);
        if (l1.val >= l2.val) {
            resListNode.val = l2.val;
            resListNode.next = mergeNode(l1, l2.next);
        }
        if (l1.val < l2.val) {
            resListNode.val = l1.val;
            resListNode.next = mergeNode(l1.next, l2);
        }

        return resListNode;
    }

    /**
     * 合并K个链表
     *
     * @param listNodes
     * @return
     */
    public ListNode mergeKNode(ListNode[] listNodes) {
        if (listNodes.length == 0) {
            return null;
        }
        ListNode dummyHead = new ListNode(0);
        ListNode head = dummyHead;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode node : listNodes) {
            if (node == null) {
                continue;
            }
            queue.add(node);
        }
        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            head.next = poll;
            head = head.next;
            if (poll.next != null) {
                queue.add(poll.next);
            }
        }
        return dummyHead.next;
    }

    /**
     * 探测链表是否有环 快慢指针
     *
     * @param listNode
     * @return
     */
    public boolean detectCycle(ListNode listNode) {
        if (listNode == null || listNode.next == null || listNode.next.next == null) {
            return false;
        }
        ListNode slow = listNode.next;
        ListNode fast = listNode.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
