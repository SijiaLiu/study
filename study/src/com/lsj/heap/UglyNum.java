package com.lsj.heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 *  
 *
 * 示例:
 *
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chou-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UglyNum {


    public int nthUglyNumber(int n) {

        // 假如x是丑数，那 2x,3x,5x 也是丑数
        int[] nums = new int[]{2, 3, 5};

        // 小顶堆
        PriorityQueue<Long> miniHeap = new PriorityQueue<>();
        // 每次产生的丑数可能会重复，用set去重
        Set<Long> set = new HashSet<>();
        // 堆中的初始元素
        miniHeap.add(1L);
        set.add(1L);

        long ugly = 0;

        for (int i = 0; i < n; i++) {
            // 第n次出堆得就是第n大的丑数
            ugly = miniHeap.poll();

            for (int num : nums) {
                if (set.add(ugly * num)) {
                    miniHeap.add(ugly * num);
                }
            }
        }


        return (int) ugly;
    }

}
