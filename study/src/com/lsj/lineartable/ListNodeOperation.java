package com.lsj.lineartable;

import com.lsj.tree.TreeNode;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ListNodeOperation {

    // 翻转部分链表的节点后的下一个节点
    private ListNode next;

    /**
     * 翻转单链表 递归
     *
     * @param head
     * @return
     */
    public ListNode reverseListNodeRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 假设递归已经把头结点后面的全部翻转了，但此时，头结点还是指向它的next，所以需要把头结点的next节点指向头结点
        // 然后头结点指向null
        ListNode reverseNode = reverseListNodeRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return reverseNode;
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
        // 每次递归 都要把节点指向前一个节点
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
     * @param head
     * @return
     */
    public ListNode reverseListNodeIterate(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode preListNode = null;
        // 当前节点不为空 一直走下去
        while (head != null) {
            ListNode nextListNode = head.next;
            // 当前节点指针指向前一个节点
            head.next = preListNode;
            preListNode = head;
            // 到达最后一个节点时，node.next == null -》node == null
            head = nextListNode;
        }
        // 因为到最后指针会指向null 然后跳出循环 所以返回前一个节点
        return preListNode;
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) {
            return head.next;
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
        return head;
    }

    /**
     * 翻转输出链表？
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        int[] result = new int[stack.size()];
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            result[i] = stack.pop();
        }
        return result;
    }

    public ListNode deleteDuplication(ListNode listNode) {
        if (listNode == null) {
            return null;
        }
        ListNode dummyNode = new ListNode(0);
        ListNode pre = listNode;
        dummyNode.next = pre;
        ListNode cur = listNode.next;
        while (cur != null) {
            if (cur.val == cur.next.val) {
                while (cur.val == cur.next.val) {
                    cur = cur.next;
                }
                pre.next = cur.next;
                cur = cur.next;
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }
        return dummyNode.next;
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode next = cur.next;
        while (next != null) {
            if (cur.val == next.val) {
                next = next.next;
                cur.next = next;
            } else {
                cur = next;
                next = next.next;
            }
        }
        return head;
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
        if (l1.val >= l2.val) {
            l2.next = mergeNode(l1, l2.next);
            return l2;
        } else {
            l1.next = mergeNode(l1.next, l2);
            return l1;
        }
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
     * @return 快慢指针相遇的节点，如果该值为null，说明没有环，如果有值，说明有环，且该节点在环内
     */
    public ListNode detectCycle(ListNode listNode) {
        if (listNode == null || listNode.next == null || listNode.next.next == null) {
            return null;
        }
        ListNode slow = listNode.next;
        ListNode fast = listNode.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }

    /**
     * 寻找环的入口节点
     *
     * @param head
     * @return
     */
    public ListNode cycleNode(ListNode head) {
        // 找到快慢指针的相遇节点
        ListNode detectCycle = detectCycle(head);
        if (null == detectCycle) {
            return null;
        }
        // 还是双指针，一个从头结点开始，一个从环内的相遇节点开始，他们再次相遇 就是环的入口节点
        ListNode slow = head;
        ListNode fast = detectCycle;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    /**
     * 删除排序好的链表中重复节点
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicateNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        ListNode next = head.next;
        while (next != null) {
            if (cur.val == next.val) {
                next = next.next;
                cur.next = next;
            } else {
                cur = next;
                next = next.next;
            }
        }

        return head;
    }


    /**
     * 重排链表
     * <p>
     * 给定一个单链表L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <p>
     * 示例 1:
     * <p>
     * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     * 示例 2:
     * <p>
     * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reorder-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = findMid(head);
        ListNode listNode = reverseListNodeRecursive(mid.next);
        mid.next = null;
        crossMergeNode(head, listNode);
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 链表交叉合并
     *
     * @param l1
     * @param l2
     */
    private void crossMergeNode(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return;
        }

        ListNode tmp1;
        ListNode tmp2;

        while (l1 != null && l2 != null) {
            tmp1 = l1.next;
            tmp2 = l2.next;

            l1.next = l2;
            l1 = tmp1;

            l2.next = l1;
            l2 = tmp2;
        }
    }


    /**
     * 链表排序
     *
     * @param head
     * @return
     */
    public ListNode orderListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMid(head);
        ListNode next = mid.next;
        mid.next = null;
        return mergeNode(orderListNode(head), orderListNode(next));
    }


    /**
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        // 进位
        int carry = 0;
        while (l1 != null || l2 != null || carry > 0) {
            int val = carry;
            if (l1 != null) {
                val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                val += l2.val;
                l2 = l2.next;
            }
            // 考虑一下 进位
            carry = val / 10;
            val = val % 10;
            p.next = new ListNode(val);
            p = p.next;
        }
        return dummy.next;
    }

    /**
     * 求两个链表的相交节点
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        Set<ListNode> set = new HashSet<>();
//        while (headA != null) {
//            set.add(headA);
//            headA = headA.next;
//        }
//        while (headB != null) {
//            if (set.contains(headB)) {
//                return headB;
//            }
//            headB = headB.next;
//        }

//        if (headA == null || headB == null) {
//            return null;
//        }
//        ListNode PA = headA;
//        ListNode PB = headB;
//        while (PA != headB) {
//            PA = PA == null ? PB : PA.next;
//            PB = PB == null ? PA : PB.next;
//        }
//        return headA;

        // 先把两个链表变成相同长度
        // 如果他们相交 的话，应该是他们不相交的部分长度有差异
        int la = getListNodeLength(headA);
        int lb = getListNodeLength(headB);
        ListNode tb = headB;
        ListNode ta = headA;
        if (la > lb) {
            while (la != lb && ta != null) {
                ta = ta.next;
                la--;
            }
        }
        if (la < lb) {
            while (la != lb && tb != null) {
                tb = tb.next;
                lb--;
            }
        }

        while (ta != tb && ta != null && tb != null) {
            ta = ta.next;
            tb = tb.next;
        }
        return ta;
        // 还有一个思路 让两个链表走两遍，他们的步数是相同的，步数相同的时候就是相遇的时候
    }

    private int getListNodeLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        int res = 0;
        ListNode tmp = head;
        while (tmp != null) {
            res++;
            tmp = tmp.next;
        }
        return res;
    }


    /**
     * 判断一个链表是不是回文链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = head;
        ListNode prepre = null;

        // 快慢指针，同时构造出前半段翻转的链表
        while (fast != null && fast.next != null) {
            // pre 节点对应的是慢节点
            pre = slow;
            // 快慢指针
            slow = slow.next;
            fast = fast.next.next;
            // 翻转
            pre.next = prepre;
            prepre = pre;
        }
        // 如果链表节点数是奇数 多走一个节点，中间的那个节点不用比较
        if (fast != null) {
            slow = slow.next;
        }

        while (pre != null && slow != null) {
            if (pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }


    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 示例:
     * <p>
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * 说明:
     * <p>
     * 必须在原数组上操作，不能拷贝额外的数组。
     * 尽量减少操作次数。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/move-zeroes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        if (length <= 0) {
            return;
        }
        // 前进指针
        int indexNum = 0;
        // 可以存不为0的数据的指针
        int indexNow = 0;
        while (indexNum < length) {
            if (nums[indexNum] != 0) {
                // 遇到不为0的值，把它放到最新可以存放的位置
                nums[indexNow++] = nums[indexNum];
            }
            // 每次前进指针都要走，前进的过程中 遇到0 now指针不用走，那就是有空闲位置可以放大于0的数字
            // 其实题目的意思就是要把前面的0都去掉
            indexNum++;
        }
        for (int i = indexNow; i < length; i++) {
            nums[i] = 0;
        }
    }


    /**
     * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * <p>
     * 你不需要 保留 每个分区中各节点的初始相对位置。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：head = [1,4,3,2,5,2], x = 3
     * 输出：[1,2,2,4,3,5]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/partition-list-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public ListNode partition(ListNode head, int x) {
        // 存放小于 x 的链表的虚拟头结点
        ListNode d1 = new ListNode(0);
        ListNode d2 = new ListNode(0);
        // 负责生成新链表，指针移动
        ListNode p1 = d1;
        ListNode p2 = d2;

        ListNode p = head;
        while (p != null) {
            if (p.val >= x) {
                p2.next = p;
                p2 = p2.next;
            } else {
                p1.next = p;
                p1 = p1.next;
            }
            // 断开原链表中next节点
            ListNode tmp = p.next;
            p.next = null;
            p = tmp;
        }
        // 上面走完之后 把两个链表链接起来
        p1.next = d2.next;
        return d1.next;
    }

    /**
     * 删除倒数第N个节点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟头结点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 删除倒数第 n 个，要先找倒数第 n + 1 个节点
        // 注意这里传入的是虚拟节点，比如说链表总共有 5 个节点，题目就让你删除倒数第 5 个节点，
        // 也就是第一个节点，那按照算法逻辑，应该首先找到倒数第 6 个节点。但第一个节点前面已经没有节点了，这就会出错
        ListNode p = getKthFromEnd(dummy, n + 1);
        p.next = p.next.next;
        return dummy.next;
    }

    ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        for (int i = 0; i < k; i++) {
            if (p1 != null) {
                p1 = p1.next;
            }
        }
        ListNode p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }

    /**
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        // 两个链表长度不同，那就变相的让他们长度相同
        // 遍历的时候 分别走一遍 步数不就相同了吗，当他们步数相同的时候 是不是就是他们相遇的时候了
        while (p1 != p2) {
            if (p1 == null) {
                p1 = headB;
            } else {
                p1 = p1.next;
            }
            if (p2 == null) {
                p2 = headA;
            } else {
                p2 = p2.next;
            }
        }
        return p1;
    }


    /**
     * 移除指定值的节点
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return dummyHead.next;
    }

    /**
     * 给你链表的头节点 head ，每k个节点一组进行翻转，请你返回修改后的链表。
     * <p>
     * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
     * <p>
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/reverse-nodes-in-k-group
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 先翻转前K个，然后在拼接K个，在拼接。。。
        ListNode b = head;
        // 找到以 head 开头的前 k 个 节点
        for (int i = 0; i < k; i++) {
            if (b == null) {
                // 不够 k 个节点了，维持原来的顺序 直接返回
                return head;
            }
            b = b.next;
        }
        // 把 k 个元素翻转了，返回的即是新的头结点，函数实现的区间是[head, b）
        ListNode reverse = reverse(head, b);
        head.next = reverseKGroup(b, k);
        return reverse;
    }

    /**
     * 翻转 head 到 tail 这部分节点
     * 区间是 [head, tail)
     *
     * @param head
     * @param tail
     * @return
     */
    private ListNode reverse(ListNode head, ListNode tail) {
        ListNode pre, cur, next;
        pre = null;
        cur = head;
        while (cur != tail) {
            // 先记住当前节点的下一个节点
            next = cur.next;
            // 指针翻转
            cur.next = pre;
            // 当前变成上一个
            pre = cur;
            // 下一个变成当前
            cur = next;
        }
        // 这个不能返回cur,此时cur=tail
        return pre;
    }


    private void swap(int[] nums, int low, int high) {
        int tmp = nums[low];
        nums[low] = nums[high];
        nums[high] = tmp;
    }

    /**
     * 寻找数组中第K大的数
     * 快速排序的思想
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        // 首先随机打乱数组
        shuffle(nums);
        int lo = 0, hi = nums.length - 1;
        // 转化成「排名第 k 的元素」
        k = nums.length - k;
        while (lo <= hi) {
            // 在 nums[lo..hi] 中选一个分界点
            int p = partition(nums, lo, hi);
            if (p < k) {
                // 第 k 大的元素在 nums[p+1..hi] 中
                lo = p + 1;
            } else if (p > k) {
                // 第 k 大的元素在 nums[lo..p-1] 中
                hi = p - 1;
            } else {
                // 找到第 k 大元素
                return nums[p];
            }
        }
        return -1;
    }

    // 对 nums[lo..hi] 进行切分
    public int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];
        // 关于区间的边界控制需格外小心，稍有不慎就会出错
        // 我这里把 i, j 定义为开区间，同时定义：
        // [lo, i) <= pivot；(j, hi] > pivot
        // 之后都要正确维护这个边界区间的定义
        int i = lo + 1, j = hi;
        // 当 i > j 时结束循环，以保证区间 [lo, hi] 都被覆盖
        while (i <= j) {
            while (i < hi && nums[i] <= pivot) {
                i++;
                // 此 while 结束时恰好 nums[i] > pivot
            }
            while (j > lo && nums[j] > pivot) {
                j--;
                // 此 while 结束时恰好 nums[j] <= pivot
            }
            // 此时 [lo, i) <= pivot && (j, hi] > pivot

            if (i >= j) {
                break;
            }
            swap(nums, i, j);
        }
        // 将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
        swap(nums, lo, j);
        return j;
    }

    // 洗牌算法，将输入的数组随机打乱
    private void shuffle(int[] nums) {
        Random rand = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 生成 [i, n - 1] 的随机数
            int r = i + rand.nextInt(n - i);
            swap(nums, i, r);
        }
    }


    /**
     * 合并K个有序链表
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // 优先级队列 默认小顶堆
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (!pq.isEmpty()) {
            // 队列开头的是最小的
            ListNode poll = pq.poll();
            p.next = poll;
            // 有下一个节点 要立刻拿出来加到队列中
            if (poll.next != null) {
                pq.add(poll.next);
            }
            p = p.next;
        }
        return dummy.next;
    }

    public void reorderList_2(ListNode head) {
        Stack<ListNode> stk = new Stack<>();
        // 先把所有节点装进栈里，得到倒序结果
        ListNode p = head;
        while (p != null) {
            stk.push(p);
            p = p.next;
        }

        p = head;
        while (p != null) {
            // 链表尾部的节点
            ListNode lastNode = stk.pop();
            ListNode next = p.next;
            // lastNode == next || lastNode.next == next 可以理解为两个指针相向而行，他们要相遇了
            // 正序列表只用前半段 栈里面的元素时后半段，所以他们会相遇
            // 列表是奇数时 lastNode == next
            // 列表是偶数时 lastNode.next == next
            if (lastNode == next || lastNode.next == next) {
                // 结束条件，链表节点数为奇数或偶数时均适用
                lastNode.next = null;
                break;
            }
            p.next = lastNode;
            lastNode.next = next;
            p = next;
        }
    }

    /**
     * 下一个排列
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int index = -1;
        int p = -1;
        // 题目的意思可以理解为 这个数组的元素不变，但组成的数字要比当前组成的数字大一点点，这个大要尽可能的小
        // 比如 [1,2,3] 可以换成 [3, 2, 1] 和 [2, 1, 3] 但明显 213要好一些，说明我们尽可能的换后面的数字，而不是把头部变大
        // 从后往前找，找到第一个比右邻居小的数字，那这个数字的前半部分就不变了，换一下这个坐标的右半部分就行
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                index = i;
                p = nums[i];
                break;
            }
        }

        // 如果没有比右邻居小的数字，说明是数组是降序排列的，题目要求返回升序
        if (index == -1) {
            reverseNums(nums, 0, len - 1);
            return;
        }

        // 上面已经找到了比右邻居小的坐标和值，那就在右边找一个比他大的值和他换掉，新生成的结果是不是就只比他大一点点了
        int j = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (nums[i] > p) {
                j = i;
                break;
            }
        }

        // 找到之后 交换两个值
        nums[index] = nums[j];
        nums[j] = p;

        // 再想想，坐标[index,len-1] 这部分的值是不是降序的，因为index的值是第一个比右邻居小的数字啊
        // 那这部分数字翻转过来 是不是生成的结果更小
        // 这思路真的是绝了。
        reverseNums(nums, index + 1, len - 1);

    }

    private void reverseNums(int[] nums, int start, int end) {
        while (start <= end) {
            int t1 = nums[start];
            int t2 = nums[end];
            nums[start] = t2;
            nums[end] = t1;
            start++;
            end--;
        }
    }

    static class LRUCache {

        class Node {
            int key;
            int val;
            Node pre;
            Node next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }


        }

        class DoubleListNode {
            Node head;
            Node tail;
            int size;

            public DoubleListNode() {
                head = new Node(0, 0);
                tail = new Node(0, 0);
                head.next = tail;
                tail.pre = head;
                size = 0;
            }

            public void addLastNode(Node node) {
                node.next = tail;
                node.pre = tail.pre;
                tail.pre.next = node;
                tail.pre = node;
                size++;
            }

            public void remove(Node node) {
                node.pre.next = node.next;
                node.next.pre = node.pre;
                size--;
            }

            public Node removeFirst() {
                if (head.next == tail) {
                    return null;
                }
                Node first = head.next;
                remove(first);
                return first;
            }

            public int getSize() {
                return size;
            }
        }

        int cap;
        Map<Integer, Node> map;
        DoubleListNode cache;

        public LRUCache(int cap) {
            this.cap = cap;
            map = new HashMap<>();
            cache = new DoubleListNode();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            makeRecently(key);
            return node.val;
        }

        public void put(int key, int val) {
            if (map.containsKey(key)) {
                deleteKey(key);
                addRecently(key, val);
                return;
            }

            if (cache.getSize() == cap) {
                removeLeastRecently();
            }
            addRecently(key, val);
        }

        /* 将某个 key 提升为最近使用的 */
        private void makeRecently(int key) {
            Node x = map.get(key);
            // 先从链表中删除这个节点
            cache.remove(x);
            // 重新插到队尾
            cache.addLastNode(x);
        }

        /* 添加最近使用的元素 */
        private void addRecently(int key, int val) {
            Node x = new Node(key, val);
            // 链表尾部就是最近使用的元素
            cache.addLastNode(x);
            // 别忘了在 map 中添加 key 的映射
            map.put(key, x);
        }

        /* 删除某一个 key */
        private void deleteKey(int key) {
            Node x = map.get(key);
            // 从链表中删除
            cache.remove(x);
            // 从 map 中删除
            map.remove(key);
        }

        /* 删除最久未使用的元素 */
        private void removeLeastRecently() {
            // 链表头部的第一个元素就是最久未使用的
            Node deletedNode = cache.removeFirst();
            // 同时别忘了从 map 中删除它的 key
            int deletedKey = deletedNode.key;
            map.remove(deletedKey);
        }

    }

    /**
     * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：head = [1,2,3,4,5], k = 2
     * 输出：[4,5,1,2,3]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/rotate-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int len = 0;
        ListNode p = head;
        // 先计算出原链表的长度
        while (p != null) {
            p = p.next;
            len++;
        }

        // 计算头结点要在原链表上移动几步
        k %= len;
        // n 等于0 说明不用移动 直接返回
        if (k == 0) {
            return head;
        }
        // 快指针从头结点开始走 k 步吧
        ListNode fast = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        // 慢指针开始出发 一直到快指针到末尾
        ListNode slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // 此时快指针在链表末尾，把它接到头结点
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;

        return newHead;
    }

    /**
     * 给定一个奇数位升序，偶数位降序的链表，将其重新排序。
     * <p>
     * 输入: 1->8->3->6->5->4->7->2->NULL
     * 输出: 1->2->3->4->5->6->7->8->NULL
     */
    public ListNode rearrange(ListNode head) {

        // 偶数的起点
        ListNode evenNode = head.next;
        ListNode oddNode = head;
        ListNode even = evenNode;
        // 先把它变成两个链表 奇数一个 偶数一个
        while (even != null && even.next != null) {
            // 奇数指向下一个奇数
            oddNode.next = even.next;
            // 自己变成下一个奇数
            oddNode = oddNode.next;
            even.next = oddNode.next;
            even = even.next;
        }
        // 此时有 oddNode 和 evenNode 两个链表，一个升序 一个降序
        ListNode reverseNode = reverse(evenNode, null);
        // 现在 oddNode 和 reverseNode 都是升序了
        // 合并两个升序链表
        return mergeNode(oddNode, reverseNode);
    }


    public int longestSubstring(String s, int k) {
        int res = 0;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        String split = "";
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0 && count[i] < k) {
                split = String.valueOf(i + 'a');
            }
        }
        if (split.equals("")) {
            // 在s中每个字符的数量都大于 k
            return s.length();
        }
        // 只要包含 split 字符的都不可能满足字符大于k
        String[] split1 = s.split(split);
        for (String s1 : split1) {
            res = Math.max(longestSubstring(s1, k), res);
        }
        return res;
    }

    public boolean searchMatrix(int[][] nums, int target) {
        int row = nums.length;
        int col = nums[0].length;
        int i = 0;
        int j = col - 1;
        while (i < row && j >= 0) {
            if (target == nums[i][j]) {
                return true;
            }
            if (nums[i][j] > target) {
                int low = 0;
                int high = j;
                while (low < high) {
                    int mid = low + (high - low) / 2;
                    if (target == nums[i][mid]) {
                        return true;
                    }
                    if (target > nums[i][mid]) {
                        low = mid + 1;
                    } else {
                        high = mid;
                    }
                }
                j = high - 1;
            } else {
                int low = i;
                int high = row - 1;
                while (low < high) {
                    int mid = low + (high - low) / 2;
                    if (target == nums[mid][j]) {
                        return true;
                    }
                    if (target >= nums[mid][j]) {
                        low = mid;
                    } else {
                        high = mid - 1;
                    }
                }
                i = high + 1;
            }
        }
        return false;
    }

}
