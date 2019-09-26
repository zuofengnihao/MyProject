package com.takozy.test78_recurrence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Subsets {

    public static void main(String[] args) {
        List<List<Integer>> subsets = subsets(new int[]{1, 2, 3, 4});
        System.out.println(subsets);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        lists.add(new ArrayList<Integer>());
        method(0, nums, item, lists);
        return lists;
    }

    public static void method(int index, int[] nums, List<Integer> item, List<List<Integer>> lists) {
        if (index >= nums.length) return;
        item.add(nums[index]);
        ArrayList<Integer> l = new ArrayList<>();
        l.addAll(item);
        lists.add(l);
        method(index + 1, nums, item, lists);
        item.remove(item.size()-1);
        method(index + 1, nums, item, lists);
    }
}
