package com.takozy.binarySearch.test33_binarySearch;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 示例 1:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 *
 * 示例 2:
 *
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Search {

    public static void main(String[] args) {
        Search o = new Search();
        int search = o.search1(new int[]{5,1,2,3,4}, 1);
        System.out.println(search);
    }

    /**
     * 回溯二分查找
     * @param nums
     * @param target
     * @return
     */
    public int search(int nums[], int target) {
        return step(nums, target, 0, nums.length - 1);
    }

    public int step(int nums[], int target, int l, int r) {
        if (l > r) return -1;
        int i = (l + r) / 2;
        if (nums[i] == target) return i;
        else {
            int res = step(nums, target, l, i - 1);
            if (res != -1) return res;
            return step(nums, target, i + 1, r);
        }
    }

    public int search1(int nums[], int target) {
        if (nums.length == 0) return -1;
        if (target == nums[0]) return 0;
        if (target == nums[nums.length - 1]) return nums.length - 1;
        if (target < nums[0] && target > nums[nums.length - 1]) return -1;

        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int i = (l + r) / 2;
            if (nums[i] > target) {
                if (nums[i] >= nums[l]) {
                    if (target > nums[l]) {
                        r = i - 1;
                    } else {
                        l = i + 1;
                    }
                } else {
                    r = i - 1;
                }
            } else if (nums[i] < target) {
                if (nums[i] >= nums[l]) {
                    l = i + 1;
                } else {
                    if (target > nums[l]) {
                        r = i - 1;
                    } else {
                        l = i + 1;
                    }
                }
            } else return i;
        }
        return -1;
    }
}
