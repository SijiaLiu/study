package com.lsj.lineartable;

public class ListNodeOperate {

    /**
     * 翻转单链表
     *
     * @param node
     * @return
     */
    public Node reverseListNode(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        // 假设递归已经把头结点后面的全部翻转了，但此时，头结点还是指向它的next，所以需要把头结点的next节点指向头结点
        // 然后头结点指向null
        Node reverseListNode = reverseListNode(node.next);
        node.next.next = node;
        node.next = null;
        return reverseListNode;
    }

    /**
     * 探测链表是否有环
     *
     * @param node
     * @return
     */
    public boolean detectCycle(Node node) {
        if (node == null || node.next == null || node.next.next == null) {
            return false;
        }
        Node slow = node.next;
        Node fast = node.next.next;
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
