package com.takozy.test315_recurrence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 输入: [5,2,6,1]
 * 输出: [2,1,1,0]
 * 解释:
 * 5 的右侧有 2 个更小的元素 (2 和 1).
 * 2 的右侧仅有 1 个更小的元素 (1).
 * 6 的右侧有 1 个更小的元素 (1).
 * 1 的右侧有 0 个更小的元素.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountSmaller {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 2, 6, 1, 3};
        CountSmaller countSmaller = new CountSmaller();
        countSmaller.sort(nums);
        System.out.println(Arrays.toString(nums));
        /*List<Integer> list = countSmaller.countSmaller(nums);
        System.out.println(list);*/


    }

    /**
     * 暴力解题 O(n2)
     * @param nums
     * @return
     */
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> counts = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] > nums[j]) count++;
            }
            counts.add(count);
        }
        return counts;
    }

    public int[] countSmaller1(int[] nums) {
        int[] result = new int[nums.length];
        int[] positions = new int[nums.length];
        for(int i = 0; i < positions.length; i++) {
            positions[i] = i;
        }
        return result;
    }

    public void countFen(int[] nums, int s, int e, int[] result, int[] positions) {
        if (s == e) return;
        int m = (s + e) / 2;
        countFen(nums, s, m, result, positions);
        countFen(nums, m + 1, e, result, positions);
        countHe(nums, s, m, e, result, positions);
    }

    public void countHe(int[] nums, int s, int m, int e, int[] result, int[] positions) {
        int[] temp = nums.clone();
        int[] tempPos = positions.clone();
        int[] tempRes = result.clone();
        int i = s, j = m + 1, k = s, count = 0;
        while (k <= e) {
            if (i > m) {
                nums[k] = temp[j];
                positions[k] = tempPos[j];
                result[k] = tempRes[j] + count;
                j++;
            } else if (j > e) {
                nums[k] = temp[i];
                positions[k] = tempPos[i];
                result[k] = tempRes[i] + count;
                i++;
            } else {
                if (temp[j] < temp[i]) {
                    count++;
                    nums[k] = temp[j];
                    positions[k] = tempPos[j];
                    result[k] = tempRes[j] + count;
                    j++;
                } else {
                    nums[k] = temp[i];
                    positions[k] = tempPos[i];
                    result[k] = tempRes[i] + count;
                }
            }
            k++;
        }
    }

    /**
     * 归并排序
     * @param nums
     */
    public void sort(int[] nums) {
        fen(nums, 0, nums.length - 1);
    }

    /**
     * 拆分数组
     * @param nums
     * @param s
     * @param e
     */
    public void fen(int[] nums, int s, int e) {
        if (s == e) return;
        int p = (s + e) / 2;
        fen(nums, s, p);
        fen(nums, p + 1, e);
        he(nums, s, p, p + 1, e);
    }

    /**
     * 排序数组
     * @param nums
     * @param s1
     * @param e1
     * @param s2
     * @param e2
     */
    public void he(int[] nums, int s1, int e1, int s2, int e2) {
        int[] temp = nums.clone();
        int p1 = s1,p2 = s2,p = s1;
        while (p <= e2) {
            if (p1 > e1) {
                nums[p++] = temp[p2++];
            } else if (p2 > e2) {
                nums[p++] = temp[p1++];
            }
            else {
                if (temp[p1] > temp[p2]) {
                    nums[p++] = temp[p2++];
                } else {
                    nums[p++] = temp[p1++];
                }
            }
        }
    }
}

