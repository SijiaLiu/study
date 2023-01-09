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

        ListNodeOperation listNodeOperation = new ListNodeOperation();
        listNodeOperation.rotateRight(listNode1, 1);
        listNodeOperation.reversePrint(listNode1);


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
        //ListNodeOperation listNodeOperation = new ListNodeOperation();
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

        System.out.println("-------reorderList--------");
        listNodeOperation.reorderList(listNode7);
        link.outNode(listNode7);

        System.out.println("-------orderListNode--------");
        ListNode listNode9 = listNodeOperation.orderListNode(listNode7);
        link.outNode(listNode9);

        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        l1.next = l2;
        ListNode l3 = new ListNode(3);
        l2.next = l3;

        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        l4.next = l5;
        ListNode l6 = new ListNode(4);
        l5.next = l6;

        ListNode listNode10 = listNodeOperation.addTwoNumbers(l1, l2);

        listNodeOperation.partition(new int[]{8, 2, 9, 10, 7, 12}, 0, 5);

        listNodeOperation.nextPermutation(new int[]{1, 3, 2});
        ListNodeOperation.LRUCache lruCache = new ListNodeOperation.LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 3);
        lruCache.put(4, 2);
        System.out.println(lruCache.get(1));

        listNodeOperation.searchMatrix(new int[][]{
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}}, 5);
    }
}
