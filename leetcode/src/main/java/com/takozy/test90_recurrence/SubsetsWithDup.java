package com.takozy.test90_recurrence;

import java.util.*;

/**
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * 此题与78思路相同 添加使用HashSet去重的逻辑
 *
 *
 */
public class SubsetsWithDup {

    public static void main(String[] args) {
        List<List<Integer>> lists = subsetsWithDup3(new int[]{1, 2, 2});
        System.out.println(lists);
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();
        lists.add(new ArrayList<>());
        addItem(0, nums, item, lists, set);
        return lists;
    }

    public static void addItem(int index, int[] nums, List<Integer> item, List<List<Integer>> lists, HashSet<List<Integer>> set) {
        if (index >= nums.length) return;
        item.add(nums[index]);
        List<Integer> l = new ArrayList<>();
        l.addAll(item);
        Collections.sort(l);
        if (!set.contains(l)) {
            set.add(l);
            lists.add(l);
        }
        addItem(index + 1, nums, item, lists, set);
        item.remove(item.size() - 1);
        addItem(index + 1, nums, item, lists, set);
    }


    public static List<List<Integer>> subsetsWithDup1(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();
        int size = 1 << nums.length;
        for (int i = 0; i < size; i++) {
            List l = new ArrayList<Integer>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) > 0) {
                    l.add(nums[j]);
                }
            }
            Collections.sort(l);
            if (!set.contains(l)) {
                set.add(l);
                list.add(l);
            }
        }
        return list;
    }

    public static List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        int size = 1 << nums.length;
        for (int i = 0; i < size; i ++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) > 0) {
                    list.add(nums[j]);
                }
            }
            if (!set.contains(list)) {
                set.add(list);
                lists.add(list);
            }
        }
        return lists;
    }

    public static List<List<Integer>> subsetsWithDup3(int[] nums) {
        List<Integer> item = new ArrayList<>();
        List<List<Integer>> lists = new ArrayList<>();
        HashSet<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        lists.add(new ArrayList<>());
        addItem1(0, nums, item, lists, set);
        return lists;
    }

    public static void addItem1(int index, int[] nums, List<Integer> item, List<List<Integer>> lists, HashSet<List<Integer>> set) {
        if (index >= nums.length) return;
        item.add(nums[index]);
        List<Integer> l = new ArrayList<>(item);
        if (!set.contains(l)) {
            lists.add(l);
            set.add(l);
        }
        addItem1(index + 1, nums, item, lists, set);
        item.remove(item.size() - 1);
        addItem1(index + 1, nums, item, lists, set);
    }
}
