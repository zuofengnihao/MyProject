package com.takozy.binarySearch.test34_binarySearch;

import java.util.Arrays;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 *
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class SearchRange {

    public static void main(String[] args) {
        SearchRange o = new SearchRange();
        int[] ints = o.searchRange(new int[]{5, 5, 5, 7, 7, 8, 8, 10}, 10);
        System.out.println(Arrays.toString(ints));
    }

    public int[] searchRange(int nums[], int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int index = (l + r) / 2;
            if (nums[index] == target) {
                int a = index, b = index;
                while (a >= 0 && nums[a] == target) a--;
                while (b < nums.length && nums[b] == target) b++;
                return new int[]{a + 1, b - 1};
            } else if (nums[index] > target) {
                r = index - 1;
            } else {
                l = index + 1;
            }
        }
        return new int[]{-1, -1};
    }

    public int[] searchRange1(int nums[], int target) {
        int l = 0, r = nums.length - 1, a = -1, b = -1;
        while (l <= r) {
            int index = (l + r) / 2;
            if (nums[index] == target) {
                if (index == 0 || nums[index - 1] != target) a = index;
                else {

                }
                if (index == nums.length - 1 || nums[index + 1] != target) b = index;
                else {

                }
            } else if (nums[index] > target) {
                r = index - 1;
            } else {
                l = index + 1;
            }
        }
        return new int[]{a, b};
    }
}
