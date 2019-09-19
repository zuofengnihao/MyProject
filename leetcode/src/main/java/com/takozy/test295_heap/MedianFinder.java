package com.takozy.test295_heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 *
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * 自己的思路
 * 使用两个 优先级队列
 * 一个最大值队列存放一半较小的数(奇数时此队列多一个数) maxQ
 * 一个最小值队列存放另一半较大的数 minQ
 * 当要取中位数时 如果为奇数 直接区最大值队列的顶值 maxQ.peek()
 * 如果为偶数时 分别去两个队列的顶值 相加 除以 2
 */
public class MedianFinder {

    Queue maxQ = new PriorityQueue<>((Integer x ,Integer y) -> y - x);
    Queue minQ = new PriorityQueue<Integer>();

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());
    }

    /** initialize your data structure here. */
    public MedianFinder() {

    }

    public void addNum(int num) {
        if (maxQ.size() == 0) {
            maxQ.add(num);
        } else {
            if (maxQ.size() == minQ.size()) {
                if (num > (int)minQ.peek()) {
                    maxQ.add(minQ.poll());
                    minQ.add(num);
                } else {
                    maxQ.add(num);
                }
            } else {
                if (num < (int)maxQ.peek()) {
                    minQ.add(maxQ.poll());
                    maxQ.add(num);
                } else {
                    minQ.add(num);
                }
            }
        }
    }

    public double findMedian() {
        if (minQ.size() == maxQ.size()) return ((int) minQ.peek() + (int) maxQ.peek()) / 2.0;
        return (int) maxQ.peek();
    }

}
