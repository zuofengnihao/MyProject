package com.takozy.test4;

import java.util.Date;

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
        long t1 = new Date().getTime();
        System.out.println(goodest(new int[]{1,2}, new int[]{3,4}));
        long t2 = new Date().getTime();
        System.out.println(t2 - t1);
        long t3 = new Date().getTime();
        System.out.println(findMedianSortedArrays_1(new int[]{1,2}, new int[]{3,4}));
        long t4 = new Date().getTime();
        System.out.println(t4 - t3);
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
                int mid1;
                if (i == 0) {
                    mid1 = b[j-1];
                } else if (j == 0) {
                    mid1 = a[i-1];
                } else {
                    mid1 = Math.max(a[i-1], b[j-1]);
                }
                if ((a.length + b.length) % 2 == 1) return mid1;

                int mid2;
                if (i == m) {
                    mid2 = b[j];
                } else if (j == n) {
                    mid2 = a[i];
                } else {
                    mid2 = Math.min(a[i], b[j]);
                }
                return ( mid1 + mid2 ) / 2.0D;
            }
        }
        return 0.0;
    }

    /**
     * 设数组a的长度为m 数组b的长度为n
     * 当找到两个数组的中位数时 状态为下图
     *
     *             分割位
     *  [a0 ~ a(i-1) | ai ~ a(m-1)]
     *  [b0 ~ b(j-1) | bj ~ b(n-1)]
     *
     *  a数组分割线左与b数组分割线左的和等于(或大于1)a数组右边b数组右边的和
     *  此时 i+j = (m-i) + (n-j) 如果他们的和是偶数
     *  或者 i+j-1 = (m-i) + (n-j) 如果他们的和是基数
     *  (刻意将多出的元素分配在了左边 是因为当m=0时分割好后 b数组左边至少有1个数 以便简化判断)
     *  从上面的等式推倒出 j = (m + n)/2 - i || j = (m + n + 1)/2 - i;
     *  又因为j不可为负数 当i=m时(即a全部元素分割在左边) (m+n)/2 > m 推导出 n>m j才永远不为负数
     *
     *  由于上面的推导 先另较长的数组为b(长度n要大于m) 再用二分法切割数组a 得到下标i 再算出下标j
     *  如果a(i-1)大于bj则a分割位需往左移动 b则需要往右移(j被计算出来 不需实际操作)
     *  如果b(j-1)大于ai则a分割位需往右移动 b反之(同上)
     *  只有当 a(i-1) < bj && b(j-1) < aj 时即找到中位数max(a(i-1),b(i-1))和min(aj,bj);
     *  偶数时为上面两个数的和/2 基数时则即为max(a(i-1),b(i-1))
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double goodest(int[] nums1, int[] nums2) {
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
            } else if (i > min && b[j] < a[i-1]) {
                max = i - 1;
            } else {
                int left;
                if (i == 0) {
                    left = b[j-1];
                } else if (j == 0) {
                    left = a[i-1];
                } else {
                    left = Math.max(a[i-1],b[j-1]);
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
