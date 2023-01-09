package com.lsj.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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


    /**
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     *
     * 你可以按 任何顺序 返回答案。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：n = 4, k = 2
     * 输出：
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/combinations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @param k
     * @return
     */
    List<List<Integer>> combineRes = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        helper(n, k, 1, new LinkedList<>());
        return combineRes;
    }

    private void helper(int n, int k, int start, LinkedList<Integer> cur) {
        // base case 只取这么多
        if (cur.size() == k) {
            combineRes.add(new LinkedList<>(cur));
            return;
        }

        for (int i = start; i <= n; i++) {
            cur.add(i);
            helper(n, k, i + 1, cur);
            cur.removeLast();
        }
    }

    /**
     * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
     *
     * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,2,2]
     * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     * 示例 2：
     *
     * 输入：nums = [0]
     * 输出：[[],[0]]
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/subsets-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    List<List<Integer>> subsetsWithDupRes = new LinkedList<>();
    LinkedList<Integer> subsetsWithDupTrack = new LinkedList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 先排序，让相同的元素靠在一起
        Arrays.sort(nums);
        backtrack(nums, 0);
        return subsetsWithDupRes;
    }

    void backtrack(int[] nums, int start) {
        // 前序位置，每个节点的值都是一个子集
        subsetsWithDupRes.add(new LinkedList<>(subsetsWithDupTrack));

        for (int i = start; i < nums.length; i++) {
            // 剪枝逻辑，值相同的相邻树枝，只遍历第一条
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            subsetsWithDupTrack.addLast(nums[i]);
            backtrack(nums, i + 1);
            subsetsWithDupTrack.removeLast();
        }
    }



    /**
     * 给定一个候选人编号的集合candidates和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates中的每个数字在每个组合中只能使用一次。
     *
     * 注意：解集不能包含重复的组合。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
     * 输出:
     * [
     * [1,1,6],
     * [1,2,5],
     * [1,7],
     * [2,6]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/combination-sum-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> combinationSum2Res = new LinkedList<>();
    LinkedList<Integer> combinationSum2Track = new LinkedList<>();
    int combinationSum2PathSum = 0;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // 先排序，让相同的元素靠在一起
        Arrays.sort(candidates);
        helper(candidates, 0, target);
        return combinationSum2Res;
    }

    private void helper(int[] candidates, int start, int target) {
        if (target == combinationSum2PathSum) {
            combinationSum2Res.add(new ArrayList<>(combinationSum2Track));
            return;
        }
        if (combinationSum2PathSum > target) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // 每个数字在每个组合中只能使用一次
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            combinationSum2Track.add(candidates[i]);
            combinationSum2PathSum += candidates[i];
            // 避免回头 i + 1
            helper(candidates, i + 1, target);
            combinationSum2PathSum -= candidates[i];
            combinationSum2Track.removeLast();
        }
    }

    /**
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [1,1,2]
     * 输出：
     * [[1,1,2],
     *  [1,2,1],
     *  [2,1,1]]
     * 示例 2：
     *
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/permutations-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<List<Integer>> permuteUniqueRes = new LinkedList<>();
    LinkedList<Integer> permuteUniqueTrack = new LinkedList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] permuteUniqueUsed = new boolean[nums.length];
        helper(nums, permuteUniqueUsed);
        return permuteUniqueRes;
    }

    private void helper(int[] nums, boolean[] permuteUniqueUsed) {
        if (permuteUniqueTrack.size() == nums.length) {
            permuteUniqueRes.add(new ArrayList<>(permuteUniqueTrack));
        }

        for (int i = 0; i < nums.length; i++) {
            // 用过的元素不能用
            if (permuteUniqueUsed[i]){
                continue;
            }
            // 遇到重复元素时怎么办？
            // 要维持重复元素的使用顺序 比如[1, 2, 2`], 如果要用的话 必须保持 2, 2`的顺序 不然就会重复选取
            if (i > 0 && nums[i] == nums[i - 1] && !permuteUniqueUsed[i - 1]) {
                continue;
            }
            permuteUniqueUsed[i] = true;
            permuteUniqueTrack.add(nums[i]);
            helper(nums, permuteUniqueUsed);
            permuteUniqueUsed[i] = false;
            permuteUniqueTrack.removeLast();
        }
    }

    /**
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
     *
     * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
     *
     * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：candidates = [2,3,6,7], target = 7
     * 输出：[[2,2,3],[7]]
     * 解释：
     * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
     * 7 也是一个候选， 7 = 7 。
     * 仅有这两种组合。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/combination-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<List<Integer>> combinationSumRes = new LinkedList<>();
    LinkedList<Integer> combinationSumTrack = new LinkedList<>();
    int combinationSumPathSum = 0;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null) {
            return combinationSumRes;
        }
        combinationSumHelper(candidates, 0, target);
        return combinationSumRes;
    }

    private void combinationSumHelper(int[] candidates, int start, int target) {
        if (target == combinationSumPathSum) {
            combinationSumRes.add(new ArrayList<>(combinationSumTrack));
            return;
        }
        if (combinationSumPathSum > target) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            combinationSumPathSum += candidates[i];
            combinationSumTrack.add(candidates[i]);
            // 可以复选 所以 i 不往上加
            combinationSumHelper(candidates, i, target);
            combinationSumPathSum -= candidates[i];
            combinationSumTrack.removeLast();
        }
    }

    /**
     * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     *
     * 只使用数字1到9
     * 每个数字 最多使用一次 
     * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 解释:
     * 1 + 2 + 4 = 7
     * 没有其他符合的组合了。
     * 示例 2:
     *
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     * 解释:
     * 1 + 2 + 6 = 9
     * 1 + 3 + 5 = 9
     * 2 + 3 + 4 = 9
     * 没有其他符合的组合了。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/combination-sum-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param k
     * @param n
     * @return
     */
    List<List<Integer>> combinationSum3Res = new LinkedList<>();
    LinkedList<Integer> combinationSum3Track = new LinkedList<>();
    int combinationSum3PathSum = 0;
    public List<List<Integer>> combinationSum3(int k, int n) {
        combinationSum3Helper(k, n, 1);
        return combinationSum3Res;
    }

    private void combinationSum3Helper(int k, int n, int start) {
        if (combinationSum3Track.size() == k && n == combinationSum3PathSum) {
            combinationSum3Res.add(new ArrayList<>(combinationSum3Track));
            return;
        }
        if (combinationSum3Track.size() >= k || combinationSum3PathSum > n) {
            return;
        }

        // 题目说了 只能用 1-9
        for (int i = start; i < 10; i++) {
            combinationSum3PathSum += i;
            combinationSum3Track.add(i);
            // 每个元素只能用一次 i + 1 向前推进
            combinationSum3Helper(k, n, i + 1);
            combinationSum3PathSum -= i;
            combinationSum3Track.removeLast();
        }
    }
}
