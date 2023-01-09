package com.lsj;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {

        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        queue.add(6);
        queue.add(5);
        queue.add(4);
        System.out.println(queue.peek());


//        List<User> userList = new ArrayList<>();
//        User u1 = new User();
//        u1.setAge(10);
//        u1.setName("30");
//        User u2 = new User();
//        u2.setAge(10);
//        u2.setName("30");
//        userList.add(u1);
//        userList.add(u2);
//        Map<Integer, String> collect = userList.stream()
//                .collect(Collectors.toMap(User::getAge, User::getName));
        System.out.println();
    }

    /**
     * 链接：https://www.nowcoder.com/questionTerminal/16409dd00ab24a408ddd0c46e49ddcf8
     * 来源：牛客网
     * <p>
     * 圆环上有 10 个点，编号 0~9 。从 0 出发，每次可以顺时针或逆时针走一格，请问一共走且仅走 n 步回到原点的方法有多少种。
     * <p>
     * 数据范围： 1 \le n \le 10^4 \1≤n≤10
     * 4
     * ，由于答案可能会非常大，所以请对答案对 10^9+7 \10的9次方+7  取模
     */
    public int circle(int n) {
        // write code here
        // dp[i][j] 表示一共走i步，到 j 位置的方法次数
        int[][] dp = new int[n + 1][10];
        dp[0][0] = 1;
        int length = 10;
        int mod = 1000000007;
        // 参考🐸跳台阶，要么从目标位置的左边跳过来 要么从右边调过来
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                // 比如 dp[i][0] 等于从 9 那个位置向前跳，或者 1 位置向后跳
                dp[i][j] = (dp[i - 1][((j - 1) + length) % 10] + dp[i - 1][((j + 1) + length) % 10]) % mod;
            }
        }
        return dp[n][0];
    }

    private void quickSort(int l, int r, int[] nums) {
        int p = findPivot(l, r, nums);
        quickSort(l, p, nums);
        quickSort(p + 1, r, nums);
    }

    private int findPivot(int l, int r, int[] nums) {
        if (l >= r) {
            return l;
        }
        int pivot = nums[l];
        int p = l;

        while (l < r) {
            while (l < r && nums[r] > pivot) {
                r--;
            }
            while (l < r && nums[l] <= pivot) {
                l++;
            }
            if (l > r) {
                break;
            }
            swap(l, r, nums);
        }
        swap(p, r, nums);
        return r;
    }

    private void swap(int i, int j, int[] nums){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
