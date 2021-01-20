package com.lsj.lineartable;

public class Main {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        Link link = new Link();

        System.out.println("--------findByValue--------");
        int i = link.findByValue(listNode1, 6);
        System.out.println(i);

        System.out.println("--------findByIndex--------");
        link.outNodeValue(link.findByIndex(listNode1, 5));

        System.out.println("--------insert before---------");
        link.outNode(listNode1);
        ListNode insert = link.insert(listNode1, 2, 5);
        System.out.println("--------insert after---------");
        link.outNode(insert);

        System.out.println("--------remove before---------");
        link.outNode(listNode1);
        System.out.println("--------remove after---------");
        ListNode listNode = link.removeNode(listNode1, 1);
        link.outNode(listNode);

        System.out.println("-------reverseListNodeIterate--------");
        ListNodeOperation listNodeOperation = new ListNodeOperation();
        ListNode listNode5 = listNodeOperation.reverseListNodeIterate(listNode1);
        link.outNode(listNode5);

        System.out.println("-------reverseListNodeRecursive--------");
        ListNode listNode6 = listNodeOperation.reverseListNodeRecursive(listNode5);
        link.outNode(listNode6);

        System.out.println("-------reverseNListNodeRecursive--------");
        ListNode listNode7 = listNodeOperation.reverseNListNodeRecursive(listNode6, 2);
        link.outNode(listNode7);

        System.out.println("-------reverseMNListNodeRecursive--------");
        ListNode listNode8 = listNodeOperation.reverseBetween(listNode7, 2, 3);
        link.outNode(listNode8);

        listNodeOperation.mergeKNode(new ListNode[]{});
    }
}
