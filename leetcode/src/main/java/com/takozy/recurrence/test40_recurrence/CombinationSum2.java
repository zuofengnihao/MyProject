package com.takozy.recurrence.test40_recurrence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1:
 *
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 示例 2:
 *
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class CombinationSum2 {

    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        System.out.println(lists);
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> lists = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();
        addItem(0, candidates, target, 0, new ArrayList<Integer>(), lists, set);
        return lists;
    }

    /**
     * 回溯剪支
     * @param index
     * @param candidates
     * @param target
     * @param sum
     * @param item
     * @param lists
     * @param set
     */
    public static void addItem(int index, int[] candidates, int target, int sum, List<Integer> item,
                               List<List<Integer>> lists, HashSet<List<Integer>> set) {
        if (index >= candidates.length || candidates[index] > target || sum > target) return;
        item.add(candidates[index]);
        sum += candidates[index];
        if (sum == target && !set.contains(item)) {
            lists.add(new ArrayList<>(item));
            set.add(item);
        }
        addItem(index + 1, candidates, target, sum, item, lists, set);
        item.remove(item.size() - 1);
        sum -= candidates[index];
        addItem(index + 1, candidates, target, sum, item, lists, set);
    }
}
