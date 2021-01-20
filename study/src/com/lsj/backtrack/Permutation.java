package com.lsj.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
 *
 * 示例1:
 *
 *  输入：S = "qqe"
 *  输出：["eqq","qeq","qqe"]
 * 示例2:
 *
 *  输入：S = "ab"
 *  输出：["ab", "ba"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-ii-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Permutation {

    // 有重复值的全排列
    public String[] permutation(String S) {
        List<List<String>> res = new ArrayList<>();
        char[] chars = S.toCharArray();
        // 因为有重复值 先排序
        Arrays.sort(chars);
        helper(res, new ArrayList<>(), chars, new boolean[chars.length]);
        return convert(res);
    }

    /**
     *
     * @param res 结果
     * @param cur 当前值
     * @param cs 初始值
     * @param visited 是否访问过
     */
    private void helper(List<List<String>> res,  List<String> cur, char[] cs, boolean[] visited) {
        if(cur.size() == cs.length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for(int i = 0; i < cs.length; i++) {
            // 当前下标访问过，跳过
            // 遇到了重复值，且重复值的前一个值没有被访问(这是为了解决第一次走完后，再重复取值，比如第一次取了eqqq后，不允许再一次eqqq)
            if(visited[i] || (i > 0 && cs[i] == cs[i-1] && !visited[i-1])){
                continue;
            }
            cur.add(String.valueOf(cs[i]));
            visited[i] = true;
            helper(res, cur, cs, visited);
            cur.remove(cur.size() - 1);
            visited[i] = false;
        }
    }

    private String[] convert(List<List<String>> list) {
        if(list.size() == 0) {
            return new String[]{};
        }
        String[] res = new String[list.size()];
        for(int i = 0; i < list.size(); i++) {
            StringBuilder sb = new StringBuilder();
            List<String> strings = list.get(i);
            for(int j = 0; j < strings.size(); j++) {
                sb.append(strings.get(j));
            }
            res[i] = sb.toString();
        }
        return res;
    }

    int res = 0;

    public int countArrangement(int N) {
        boolean[] flag = new boolean[N + 1];
        helper(flag, N, 1);
        return res;
    }

    //count表示已经排列到第几个数
    private void helper(boolean[] flag, int N, int count) {
        if (count == N + 1) {
            res++;
            return;
        }
        for (int i = 1; i <= N; i++) {
            //如果当前数已经使用过
            if (flag[i]) continue;
            //剪枝条件：如果不能被i或整除i
            if (i % count != 0 && count % i != 0) continue;
            flag[i] = true;
            helper(flag, N, count + 1);
            flag[i] = false;
        }
    }



}
