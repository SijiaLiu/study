package com.lsj.sort;

import com.lsj.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 快速排序
 *
 */
public class QuickSort {

    public int[] quickSort(int[] nums) {
        if (nums.length == 0) {
            return nums;
        }
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = findMid(nums, left, right);
        quickSort(nums, left, mid - 1);
        quickSort(nums, mid + 1, right);
    }

    void quick_sort(int s[], int l, int r) {
        if (l < r) {
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = s[l];
            while (i < j) {
                while (i < j && s[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if (i < j)
                    s[i] = s[j];

                while (i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if (i < j)
                    s[j] = s[i];
            }
            s[i] = x;
            quick_sort(s, l, i - 1); // 递归调用
            quick_sort(s, i + 1, r);
        }
    }

    void sort(int[] nums, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int pivot = nums[right];

            while (i < j) {
                while (i < j && nums[i] <= pivot) {
                    i++;
                }
                if (i < j) {
                    nums[j] = nums[i];
                }
                while (i < j && nums[j] > pivot) {
                    j--;
                }
                if (i < j) {
                    nums[i] = nums[j];
                }
            }
            nums[j] = pivot;
            sort(nums, left, j - 1);
            sort(nums, j + 1, right);

        }
    }

    public int findMid(int[] nums, int left, int right) {
        int mid = nums[left];
        left++;
        while (left <= right) {
            if (nums[left] <= mid) {
                left++;
            } else {
                swap(nums, left, right);
                right--;
            }
        }
        swap(nums, left, right);
        return right;
    }

    public void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    /**
     * 正版快排
     *
     * 可以解 https://leetcode.cn/problems/kth-largest-element-in-an-array/submissions/
     *
     * @param low
     * @param hi
     * @param nums
     */
    private void quickSort(int low, int hi, int[] nums) {
        if (low >= hi) {
            return;
        }
        int pivot = findPivot(low, hi, nums);
        quickSort(low, pivot - 1, nums);
        quickSort(pivot + 1, hi, nums);
    }

    private int findPivot(int low, int hi, int[] nums) {
        int p = nums[low];
        int i = low;
        int j = hi;
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



}
