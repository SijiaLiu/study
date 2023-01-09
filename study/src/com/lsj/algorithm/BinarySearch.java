package com.lsj.algorithm;

import java.util.Arrays;

public class BinarySearch {

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     *
     * 进阶：
     *
     * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * 示例 2：
     *
     * 输入：nums = [5,7,7,8,8,10], target = 6
     * 输出：[-1,-1]
     * 示例 3：
     *
     * 输入：nums = [], target = 0
     * 输出：[-1,-1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        // 找出 >= target 的第一个点
        int left = searchTarget(nums, target);
        if (left >= nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        // 找出大于 target 的第一个点
        int right = searchTarget(nums, target + 1);
        return new int[] {left, right - 1};
    }

    /**
     * 找出 大于或等于 target 的第一个位置
     * @param nums
     * @param target
     * @return
     */
    private int searchTarget(int[] nums, int target) {
        int l = 0;
        int r = nums.length;

        // l < r 说明搜索的的是[l,r)区间
        // 跳出循环的时候 l >= r
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    /**
     * 二分查找 升序区间 目标值第一个出现的位置
     * @param nums
     * @param target
     * @return
     */
    private int searchTargetLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // 搜索区间[left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 因为要找左边界，所以持续压缩right
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 上面循环的终止条件是left > right
        // 考虑一下 目标值比所有元素小时，left不变，right 比 left 小 1，此时 nums[left] != target
        // 考虑一下 目标值比所有元素大时，right不变，right 比 left 小 1，此时 left >= nums.length
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 二分查找 升序区间 目标值最后一个出现的位置
     * @param nums
     * @param target
     * @return
     */
    private int searchTargetRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // 搜索区间[left, right]
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 因为要找右边界，所以持续压缩left
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 上面循环的终止条件是left > right
        // 考虑一下 目标值比所有元素小时，left不变，right 比 left 小 1，此时 right = -1
        // 考虑一下 目标值比所有元素大时，right不变，right 比 left 小 1，此时 left >= nums.length
        if (right < 0 || nums[right] != target) {
            return -1;
        }
        return right;
    }


    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        int res = nums[0];
        if (length < 2) {
            return res;
        }
        for (int i = 1; i < length - 1; i++) {
            if (nums[i] != nums[i-1] && nums[i] != nums[i+1]) {
                return nums[i];
            }
        }
        return nums[length - 1] == nums[length - 2] ? res : nums[length - 1];
    }


    /**
     * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有piles[i]根香蕉。警卫已经离开了，将在 h 小时后回来。
     *
     * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
     *
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     *
     * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
     *
     *
     *
     * 示例 1：
     *
     * 输入：piles = [3,6,7,11], h = 8
     * 输出：4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/koko-eating-bananas
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1000000000 + 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (getHour(piles, mid) == h) {
                // 题目要求速度的最小值，函数是单调递减的函数，所以压缩检索的有边界
                right = mid;
            } else if (getHour(piles, mid) < h) {
                // 时间小于规定时间，说明速度过快了，那就要减少速度
                right = mid;
            } else if (getHour(piles, mid) > h) {
                // 时间大于规定时间，说明速度过慢了，那就要增加速度
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 计算在吃香蕉需要的时间
     * 速度越快，时间越少
     * @param piles 香蕉数组
     * @param speed 吃香蕉的速度
     * @return 需要的时间
     */
    private int getHour(int[] piles, int speed) {
        int res = 0;
        for (int i = 0; i < piles.length; i++) {
            // 一小时只吃一次，一个小时内吃了一堆就不会再吃另外一堆
            res += piles[i] / speed;
            // 多出来的还是要多花一个小时来吃
            if (piles[i] % speed > 0) {
                res++;
            }
        }
        return res;
    }


    /**
     * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
     *
     * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
     *
     * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：weights = [1,2,3,4,5,6,7,8,9,10], days = 5
     * 输出：15
     * 解释：
     * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
     * 第 1 天：1, 2, 3, 4, 5
     * 第 2 天：6, 7
     * 第 3 天：8
     * 第 4 天：9
     * 第 5 天：10
     *
     * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
     * 示例 2：
     *
     * 输入：weights = [3,2,2,4,1,4], days = 3
     * 输出：6
     * 解释：
     * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
     * 第 1 天：3, 2
     * 第 2 天：2, 4
     * 第 3 天：1, 4
     * 示例 3：
     *
     * 输入：weights = [1,2,3,1,1], days = 4
     * 输出：3
     * 解释：
     * 第 1 天：1
     * 第 2 天：2
     * 第 3 天：3
     * 第 4 天：1, 1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/capacity-to-ship-packages-within-d-days
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param piles
     * @param h
     * @return
     */
    public int shipWithinDays(int[] piles, int h) {
        int left = 0;
        int right = 1;
        for (int weight : piles) {
            left = Math.max(left, weight);
            right += weight;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (getDay(piles, mid) == h) {
                // 题目要求运载能力的最小值，函数是单调递减的函数，所以压缩检索的右边界
                right = mid;
            } else if (getDay(piles, mid) < h) {
                // 时间小于规定时间，说明运载能力过强了，那就要减少运载能力
                right = mid;
            } else if (getDay(piles, mid) > h) {
                // 时间大于规定时间，说明运载能力过小了，那就要增加运载能力
                left = mid + 1;
            }
        }
        return left;
    }


    private int getDay(int[] nums, int weight) {
        int res = 0;
        // 注意 不能i++,i是否前进有cap 决定
        for (int i = 0; i < nums.length;) {
            int cap = weight;
            // 一直装 直到装不小
            while (i < nums.length) {
                if (cap < nums[i])
                    // 此时装不下了，跳出循环，这算一天的，所以下面res++
                    break;
                else cap -= nums[i];
                i++;
            }
            res++;
        }
        return res;
    }


    public int[] exchange(int[] nums) {
        if (nums == null) {
            return null;
        }
        int l = 0, r = nums.length - 1;

        while(l < r) {
            while(l < r && nums[l] % 2 != 0) {
                l ++;
            }
            while(r > l && nums[r]  % 2 == 0) {
                r --;
            }
            if(l != r) {
                int tmp = nums[r];
                nums[r] = nums[l];
                nums[l] = tmp;
                l ++;
                r --;
            }
        }

        return nums;
    }
}
