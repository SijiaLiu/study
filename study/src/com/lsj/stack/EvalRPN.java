package com.lsj.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 根据逆波兰表示法，求表达式的值。
 *
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 *
 * 说明：
 *
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 *
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 *
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 */
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        Stack<String> stack = new java.util.Stack<>();
        Map<String, Integer> map = new HashMap<>();
        map.put("+", 1);
        map.put("-", 2);
        map.put("*", 3);
        map.put("/", 4);
        for (String s : tokens) {
            stack.push(s);
            // 遇到符号，先把符号出栈 再把栈顶的前两个元素出栈
            if (map.containsKey(s) && !stack.isEmpty()) {
                stack.pop();
                int p1 = Integer.parseInt(stack.pop());
                int p2 = Integer.parseInt(stack.pop());
                int next = 0;
                switch (map.get(s)) {
                    case 1:
                        next = p2 + p1;
                        break;
                    case 2:
                        next = p2 - p1;
                        break;
                    case 3:
                        next = p2 * p1;
                        break;
                    case 4:
                        next = p2 / p1;
                        break;
                    default:
                        break;
                }
                // 把计算后的值入栈
                stack.push(String.valueOf(next));
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
