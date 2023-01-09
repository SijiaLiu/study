package com.lsj.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SchoolTimetable {

    /**
     * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
     * <p>
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。
     * <p>
     * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     * <p>
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入：numCourses = 2, prerequisites = [[1,0]]
     * 输出：true
     * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/course-schedule
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    // 图是否有环
    boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        // 标识某个节点在路径上
        boolean[] onPath = new boolean[numCourses];
        // 标识某个节点已经访问过
        boolean[] visited = new boolean[numCourses];
        // 遍历图中每一个节点
        for (int i = 0; i < numCourses; i++) {
            dfs(graph, onPath, visited, i);
        }

        return !hasCycle;
    }

    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        List<Integer>[] res = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            // 有多少课程 那就有多少个节点
            res[i] = new LinkedList<>();
        }
        for (int[] prerequisite : prerequisites) {
            // from 表示被依赖的课程
            // 修完被依赖的课程才能修目标课程
            int from = prerequisite[1];
            // to 表示目标课程
            int to = prerequisite[0];
            // 所以这个图的意思就是 修完了from 就可以去修 to
            res[from].add(to);
        }
        return res;
    }

    private void dfs(List<Integer>[] graph, boolean[] onPath, boolean[] visited, int start) {
        // 发现路径上的点重复了 那就是有环
        if (onPath[start]) {
            hasCycle = true;
        }
        // 当前节点被访问过，或者已经有环了 不找了
        if (visited[start] || hasCycle) {
            return;
        }
        onPath[start] = true;
        visited[start] = true;
        // 继续找当前节点周边的
        for (Integer s : graph[start]) {
            dfs(graph, onPath, visited, s);
        }
        // 路径还原
        onPath[start] = false;
    }


    int ways = 0;
    int k;
    int n;

    // 图的遍历
    public int numWays(int n, int[][] relation, int k) {
        // 构建图
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] i : relation) {
            graph[i[0]].add(i[1]);
        }
        this.k = k;
        this.n = n;
        dfs(0, 0, graph);
        return ways;
    }

    private void dfs(int index, int steps, List<Integer>[] graph) {
        if (steps == k) {
            if (index == n - 1) {
                ways++;
            }
            return;
        }
        List<Integer> list = graph[index];
        for (int next : list) {
            dfs(next, steps++, graph);
        }
    }
}
