package com.takozy.binarySearch.test34_binarySearch;

import java.lang.annotation.Target;
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
        int[] ints = o.searchRange1(new int[]{0,0,0,0,0,1,1,2,2,3,4,4,5,5,5,5,6,7}, 0);
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 只使用了二分查找target
     * @param nums
     * @param target
     * @return
     */
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

    /**
     * 使用一个循环找到minIndex,maxIndex
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange1(int[] nums, int target) {
        int l = 0, r = nums.length - 1, a = -1, b = -1, flagL = -1, flagR = -1;
        while (l <= r) {
            int i = (l + r) / 2;
            if (nums[i] > target) r = i - 1;
            else if (nums[i] < target) l = i + 1;
            else {
                if (i == 0 || nums[i - 1] != target) a = i;
                if (i == nums.length - 1 || nums[i + 1] != target) b = i;
                if (a != -1 && b != -1) break;
                else if (b != -1) r = i - 1;
                else if (a != -1) {
                    if (flagL != -1 && flagR != -1) {
                        l = flagL;
                        flagL = -1;
                        r = flagR;
                        flagR = -1;
                    } else {
                        l = i + 1;
                    }
                }
                else {
                    flagL = flagL == -1 ? i + 1 : flagL;
                    flagR = flagR == -1 ? r : flagR;
                    r = i - 1;
                }
            }
        }
        return new int[]{a, b};
    }
}
