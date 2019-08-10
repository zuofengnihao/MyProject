package com.takozy.test4;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays_1(new int[]{2,3}, new int[]{1}));
    }

    /**
     * 自己的思路 对比获取中位数 时间复杂度 O((m+n)/2)
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int midPre = 0, midNext = 0;
        for (int i = 0, j = 0, k = 0; k <= len / 2; k ++) {
            midPre = midNext;
            if (i >= nums1.length) midNext = nums2[j++];
            else if (j >= nums2.length) midNext = nums1[i++];
            else if (nums1[i] < nums2[j]) midNext = nums1[i++];
            else midNext = nums2[j++];
        }
        return len % 2 == 0 ? (midPre + midNext) / 2.0 : midNext;
    }

    public static double findMedianSortedArrays_1(int[] nums1, int[] nums2) {
        int m,n;
        int[] a,b;
        if (nums1.length > nums2.length) {
            m = nums2.length; n = nums1.length;
            a = nums2; b = nums1;
        } else {
            m = nums1.length; n = nums2.length;
            a = nums1; b = nums2;
        }
        int i , j, min = 0, max = m;
        while (min <= max) {
            i = (max + min) / 2;
            j = (m + n + 1) / 2 - i;
            if(i < max && a[i] < b[j-1]) {
                min = i + 1;
            } else if (i > 0 && b[j] < a[i-1]) {
                max = i - 1;
            } else {
                int left;
                if (i == 0) {
                    left = b[j-1];
                } else if (j == 0) {
                    left = a[i-1];
                } else {
                    left = Math.max(a[i-1],  b[j-1]);
                }
                if ((m + n) % 2 == 1) return left;

                int right;
                if (i == m) {
                    right = b[j];
                } else if (j == n) {
                    right = a[i];
                } else {
                    right = Math.min(a[i], b[j]);
                }
                return (left + right) / 2.0;
            }
        }
        return 0.0;
    }
}
