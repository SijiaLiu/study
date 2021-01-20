package com.lsj.stack;

import java.util.Stack;

/**
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        int length = T.length;
        int[] res = new int[length];
        Stack<Integer> stack = new Stack<>();
        // 从后往前找
        for (int i = length - 1; i >= 0 ; i--) {
            // 标识是否找到比当前温度高的值
            boolean exist = false;
            for (int j = i + 1; j < length; j++) {
                // 当天往后找，找到了放入栈中，并设置为找到了，跳出
                if (T[j] > T[i]) {
                    stack.push(j - i);
                    exist = true;
                    break;
                }
            }
            // 没找到
            if (!exist) {
                stack.push(0);
            }
        }
        for (int i = 0; i < length; i++) {
            res[i] = stack.pop();
        }
        return res;
    }

    public int[] dailyTemperatures2(int[] T) {
        int length = T.length;
        int[] res = new int[length];
        Stack<Integer> stack = new Stack<>();
        for (int i = length - 1; i >= 0 ; i--) {
            // 栈不为空，且当天的温度比后一天的高，说明后一天的温度对我没用，出栈，然后比较后N天的温度
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]) {
                stack.pop();
            }
            // 栈为空 说明后面没有比当天高的，设置为0，非空，则出栈，比较距离现在多少天
            res[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            // 当前日期入栈
            stack.push(i);
        }
        return res;
    }
}
