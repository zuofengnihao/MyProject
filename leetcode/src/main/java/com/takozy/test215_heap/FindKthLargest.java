package com.takozy.test215_heap;

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
        System.out.println(findKthlargest(new int[]{3,2,1,5,6,4}, 2));
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
     * @param nums
     * @param k
     * @return
     */
    public static int findKthlargest2(int nums[], int k) {
        return 0;
    }
}
