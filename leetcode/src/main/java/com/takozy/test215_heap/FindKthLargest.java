package com.takozy.test215_heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 *
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 *
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindKthLargest {

    public static void main(String[] args) {
        System.out.println(findKthlargest2(new int[]{3,2,1,5,6,4}, 2));

        //快速排序
        //int[] nums = {43,242,12,65,43,878,1,54,66,29,84};
        //sort(nums);
        //System.out.println(Arrays.toString(nums));
    }

    /**
     * 利用优先队列(顶小堆) 保持优先队列只有k个元素
     * 遍历数组 add 元素并判断是否大k 大于则poll
     * 遍历完成后poll出队列顶元素即为第k大的元素
     * @param nums
     * @param k
     * @return
     */
    public static int findKthlargest(int nums[], int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int i : nums) {
            queue.add(i);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.poll();
    }

    /**
     * 自己思路 与上相同 减少了出入栈操作 提高了效率
     * @param nums
     * @param k
     * @return
     */
    public static int findKthlargest1(int nums[], int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int i : nums) {
            if (queue.size() == k) {
                if (i > queue.peek()) {
                    queue.poll();
                    queue.add(i);
                }
            } else {
                queue.add(i);
            }
        }
        return queue.poll();
    }

    /**
     * 官方 快速选择
     * 使用快速排序思想
     * @param nums
     * @param k
     * @return
     */
    public static int findKthlargest2(int nums[], int k) {
        return find(nums, 0, nums.length-1, k);
    }

    /**
     * 快速排序
     * 随机一个基准点(一般使用下表0)将小于基准点的放在基准点左边
     * 大于基准点的放在基准点右边 再用分治思想递归调用基准点左边部分与右边部分
     *
     * 具体移动思路
     * 设置两个变量i，j 排序开始时 i=0，j=n-1
     * 将i下标点值保存在temp中temp=A[i]
     * 然后循环j-- j向左移动直到找到小于temp的值 将A[i]=A[j]且i向左移动1
     * 再循环i++ i向右移动直到找到大于temp的值 将A[j]=A[i]且j向右移动1
     * 重复两个循环 直到i=j 将A[i]=temp
     * 使用分治思路 将i左边分为一组 右边分为一组 递归以上操作
     *
     * 例子：
     * i->6 4 7 1 2<-j temp=6 j-- 找到小于temp的2 赋予i对应的值并i++
     * 2 i->4 7 1 2<-j i++ 找到大于temp的7 赋予j对应的值并j--
     * 2 4 i->7 1<-j 7 重复以上步骤
     * 2 4 1 i->1<-j 7 此时i=j 令A[i]=temp
     * 2 4 1 i->6<-j 7 分两部分|2 4 1 <-左边部分|6|右边不分-> 7|继续递归调用
     *
     * @param nums
     * @param l
     * @param r
     */
    public static void partition(int nums[], int l, int r) {
        if (l < r) {
            int temp = nums[l], i = l, j = r;
            while (i < j) {
                while (i < j && nums[j] > temp) {
                    j--;
                }
                if (i < j) nums[i++] = nums[j];
                while (i < j && nums[i] <= temp) {
                    i++;
                }
                if (i < j) nums[j--] = nums[i];
            }
            nums[i] = temp;
            partition(nums, l, i - 1);
            partition(nums, i + 1, r);
        }
    }

    public static void sort(int[] nums) {
        partition(nums, 0, nums.length - 1);
    }

    public static int find(int[] nums, int l, int r, int k) {
        if (l < r) {
            int temp = nums[l], i = l, j = r;
            while (i < j) {
                while (i < j && nums[j] > temp) j--;
                if (i < j) nums[i++] = nums[j];
                while (i < j && nums[i] <= temp) i++;
                if (i < j) nums[j--] = nums[i];
            }
            nums[i] = temp;
            if (i == nums.length - k) return nums[i];
            else if (i < nums.length - k) return find(nums, i+1, r, k);
            else return find(nums, 0, i-1, k);
        }
        return 0;
    }
}
