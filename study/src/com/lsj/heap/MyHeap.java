package com.lsj.heap;

public class MyHeap {

    /**
     * 获取大顶堆的堆顶元素
     *
     * @param nums
     * @return
     */
    public int getMaxHeapTop(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        buildMaxHeap(nums);
        return nums[0];
    }

    /**
     * 构建大顶堆
     *
     * @param nums
     */
    private void buildMaxHeap(int[] nums) {
        int length = nums.length;
        // length / 2 - 1 是最大的非叶子节点
        for (int i = length / 2 - 1; i >= 0; i--) {
            if (2 * i + 1 < length && nums[2 * i + 1] > nums[2 * i]) {
                int temp = nums[2 * i + 1];
                nums[2 * i + 1] = nums[2 * i];
                nums[2 * i] = temp;
                // 当i较小时，有可能 左子树的左子树不满足大顶堆的性质
                if ((2 * (2 * i + 1) + 1 < length && nums[2 * i + 1] < nums[2 * (2 * i + 1) + 1])
                        || (2 * (2 * i + 2) + 1 < length && nums[2 * i + 2] < nums[2 * (2 * i + 2) + 1])) {
                    buildMaxHeap(nums);
                }
            }

            if (2 * i + 2 < length && nums[2 * i + 2] > nums[2 * i]) {
                int temp = nums[2 * i + 2];
                nums[2 * i + 2] = nums[2 * i];
                nums[2 * i] = temp;
                // 当i较小时，有可能 右子树的右子树不满足大顶堆的性质
                if ((2 * (2 * i + 2) + 2 < length && nums[2 * i + 2] < nums[2 * (2 * i + 1) + 2])
                        || (2 * (2 * i + 2) + 2 < length && nums[2 * i + 2] < nums[2 * (2 * i + 2) + 2])) {
                    buildMaxHeap(nums);
                }
            }
        }
    }


    /**
     * 构建小顶堆
     *
     * @param nums
     */
    public void buildMinHeap(int[] nums) {
        int length = nums.length;
        for (int i = length / 2 - 1; i >= 0; i--) {
            slink(nums, i);
        }
    }

    private void slink(int[] nums, int i) {
        while (2 * i + 1 < nums.length) {
            // 找到节点的左子树
            int j = 2 * i + 1;
            // 我们的目的是找到根节点以及左右节点中最小的那个节点，如果左子树大于右子树，我们就只需要比较根节点和右子树的大小
            if (j + 1 < nums.length && nums[j] > nums[j + 1]) {
                j++;
            }
            // 如果根节点大于左右节点中最小的那个，交换节点
            if (nums[i] > nums[j]) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                // 判断完当前节点和当前节点和左右节点的大小后，并且交换了节点，
                // 我们需要判断交换完节点的位置是否满足小顶堆的性质，所以需要交换左边 继续比较
                i = j;
            } else {
                // 没有需要交换的说明当前节点的满足最小堆得性质，无需继续查找，
                // 我们一开始就是从最后一个非叶子节点开始查找的，不存在找着找着下面的节点不满足最小堆
                break;
            }
        }
    }


    public void out(int[] nums) {
        for (int i : nums) {
            System.out.println(i);
        }
    }
}
