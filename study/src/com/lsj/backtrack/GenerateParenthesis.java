package com.lsj.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成有效的括号
 */
public class GenerateParenthesis {


    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：n = 3
     * 输出：["((()))","(()())","(())()","()(())","()()()"]
     * 示例 2：
     *
     * 输入：n = 1
     * 输出：["()"]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/generate-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        gen(0, 0, n, stringBuilder, res);
        helper(n, n, n, new StringBuilder());
        return helperRes;
    }

    private static void gen(int left, int right, int n, StringBuilder str, List<String> res) {
        // 长度够了说明已经找到解了 跳出递归
        if (str.length() == n * 2) {
            res.add(str.toString());
            return;
        }
        // 只要左括号没放完 就一直放
        if (left < n) {
            str.append('(');
            gen(left + 1, right, n, str, res);
            // 这个操作很重要 因为当走到这一步时，说明前面的递归已经完成了，已经找到了一个解，要找其他的解，得先把末尾元素移除掉
            str.deleteCharAt(str.length() -1);
        }
        if (right < left) {
            str.append(')');
            gen(left, right + 1, n, str, res);
            // 这个操作很重要 因为当走到这一步时，说明前面的递归已经完成了，已经找到了一个解，要找其他的解，得先把末尾元素移除掉
            str.deleteCharAt(str.length() -1);
        }
    }


    static List<String>  helperRes = new ArrayList<>();
    private static void helper(int left, int right, int n, StringBuilder sb) {
        if (left < 0 || right < 0) {
            return;
        }
        // 在括号生成的过程中，右括号不能多于左括号
        if (left > right) {
            return;
        }
        // 左右括号全部用完 即是一种结果
        if (sb.toString().length() == 2 * n && left == 0 && right == 0) {
            helperRes.add(sb.toString());
            return;
        }


        sb.append("(");
        helper(left - 1, right, n, sb);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(")");
        helper(left, right - 1, n, sb);
        sb.deleteCharAt(sb.length() - 1);

    }

}
