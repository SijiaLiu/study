package com.lsj.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。
 *
 * 每个 LED 代表一个 0 或 1，最低位在右侧。
 *
 * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-watch
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class ReadBinaryWatch {

    private List<String> result = new ArrayList<>();
    private int[] nums = new int[]{8,4,2,1,32,16,8,4,2,1};

    public List<String> readBinaryWatch(int turnedOn) {
        backTrack(turnedOn, 0, 0, 0);
        return result;
    }

    private void backTrack(int num, int start, int hour, int minutes) {
        if (num == 0) {
            if (hour > 11 || minutes > 59) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(hour);
            sb.append(":");
            if (minutes < 10) {
                sb.append("0");
            }
            sb.append(minutes);
            result.add(sb.toString());
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 做选择，要么选小时，要么选分钟，因为是在循环内，所以每次小时和分钟都会选一次
            if (i < 4) {
                hour += nums[i];
            } else {
                minutes += nums[i];
            }
            backTrack(num - 1, i + 1, hour, minutes);
            // 选完之后撤销选择，因为在循环内
            if (i < 4) {
                hour -= nums[i];
            } else {
                minutes -= nums[i];
            }
        }
    }
}
