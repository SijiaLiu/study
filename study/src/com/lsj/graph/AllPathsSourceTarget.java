package com.lsj.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AllPathsSourceTarget {

    /**
     * 给定一个有n个节点的有向无环图，用二维数组graph表示，请找到所有从0到n-1的路径并输出（不要求按顺序）。
     * <p>
     * graph 的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些结点
     * （译者注：有向图是有方向的，即规定了 a→b 你就不能从 b→a ），若为空，就是没有下一个节点了。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * <p>
     * 输入：graph = [[1,2],[3],[3],[]]
     * 输出：[[0,1,3],[0,2,3]]
     * 解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/bP4bmD
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        dfs(graph, 0, new LinkedList<>());
        return res;
    }

    private void dfs(int[][] graph, int cur, LinkedList<Integer> path) {
        // 访问的每一个节点都属于路径
        path.add(cur);
        // 当前节点是目标节点 加到结果里面去
        if (cur == graph.length - 1) {
            res.add(new LinkedList<>(path));
            // 这里可以直接return 但要removeLast 不然影响回溯
            path.removeLast();
            return;
        }
        // 查找cur的邻居节点
        for (int s : graph[cur]) {
            dfs(graph, s, path);
        }
        // 撤销选择
        path.removeLast();
    }
}
