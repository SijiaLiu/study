package com.lsj.dfs;

import com.lsj.tree.TreeNode;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int[][] grid = new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}};
        Land land = new Land();
        //System.out.println(land.numLands(grid));
        System.out.println("岛屿最大面积：" + land.maxAreaOfIsland(grid));


        FindTargetSumWays findTargetSumWays = new FindTargetSumWays();
        System.out.println(findTargetSumWays.findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3));

        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(1);  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer
        list.add((int) 2.0);

        list.getClass().getMethod("add", Object.class).invoke(list, 2.0);

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        Map<Key, Integer> map = new HashMap<>();
        map.put(new Key("1"), 1);
        map.put(new Key("2"), 2);
        map.put(new Key("3"), 2);
        Integer integer = map.get(new Key("2"));
        System.out.println(integer);

        System.out.println(subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));
    }

    class DiffNumber {

        // diff[i] 表示nums[i] 于 nums[i-1] 的差值
        int[] diff;
        public DiffNumber(int[] nums) {
            int len = nums.length;
            diff = new int[len];
            diff[0] = nums[0];
            for (int i = 1; i < len; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }

        /**
         * i j 部分的数字都 加上 val
         *
         * @param i
         * @param j
         * @param val
         */
        public void increment(int i, int j, int val) {
            // 因为 res[i] = res[i-1] + diff[i]
            // 只要diff[i] 改动了，res[i] 后面的值就全改动了
            diff[i] += val;
            // j + 1如果 >= diff.length 那就是全都改了
            if (j + 1 < diff.length) {
                // 因为要只改到 j 所以 j 之后的位置要改为原来的
                diff[j + 1] -= val;
            }
        }

        public int[] result() {
            int[] res = new int[diff.length];
            res[0] = diff[0];
            for (int i = 1; i < diff.length; i++) {
                res[i] = res[i - 1] + diff[i];
            }
            return res;
        }
    }

    public static int subarraysDivByK(int[] nums, int k) {
        // 前缀和的思想 用一个变量或数组记录 p[i] = nums[0] + nums[1] +...+ nums[i-1]
        // 利用同余定理，简单的说，就是如果两个数和某个除数k取余结果一致，那么这两个数的差可以被k整除。
        // 放在这个题目中即是，如果sums[i] 和 sums[j] 与k 取余的结果一致(sums就是前缀和数组)，那么sums[j] - sums[i] = rk (r为某个常数)
        // 也就是说 [i:j]之间的子数组的 和可以被k整除
        // map来记录每种余数出现的次数
        Map<Integer, Integer> map = new HashMap<>();
        // 如果一个数直接能被K整除，那它的本身就是一个合法的子数组
        map.put(0, 1);
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // 计算当前前缀数组和 除以 k 的余数
            int record = (sum % k + k) % k;;
            int same = map.getOrDefault(record, 0);
            // 如果 same 有值，说明之前有个前缀和与当前前缀和 与 k的余数相同
            res += same;
            map.put(record, same + 1);
        }

        return res;
    }

    private void quickSort(int low, int hi, int[] nums) {
        if (low >= hi) {
            return;
        }
        new Random().nextInt(2);
        int pivot = findPivot(low, hi, nums);
        quickSort(low, pivot - 1, nums);
        quickSort(pivot + 1, hi, nums);
    }

    private int findPivot(int low, int hi, int[] nums) {
        int i = low;
        int j = hi;
        int p = nums[i];
        while (i <= j) {
            while (i < j && nums[j] > p) {
                j--;
            }
            while (i < j && nums[i] <= p) {
                i++;
            }
            if (i >= j) {
                break;
            }
            swap(i, j, nums);
        }
        swap(low, j, nums);
        return j;
    }

    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    int[] tmp;
    private void mergeSort(int low, int hi, int[] nums) {
        if (low >= hi) {
            return;
        }
        int mid = low + (hi - low) / 2;
        mergeSort(low, mid, nums);
        mergeSort(mid + 1, hi, nums);
        merge(low, mid, hi, nums);
    }

    private void merge(int low, int mid, int hi, int[] nums) {
        for (int k = low; k <= hi; k++) {
            tmp[k] = nums[k];
        }
        int i = low;
        int j = hi;
        int p = low;
        while (p <= hi) {
            if (i > mid) {
                nums[p] = tmp[j--];
            } else if (j < mid) {
                nums[p] = tmp[i++];
            } else if(tmp[i] < tmp[j]) {
                nums[p] = tmp[i++];
            } else {
                nums[p] = tmp[j--];
            }
            p++;
        }
    }
}
