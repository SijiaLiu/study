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

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        gen(0, 0, n, stringBuilder, res);
        return res;
    }

    private static void gen(int left, int right, int n, StringBuilder str, List<String> res) {
        // 长度够了说明已经找到解了 跳出递归
        if (str.length() == n * 2) {
            res.add(str.toString());
            return;
        }
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
}
