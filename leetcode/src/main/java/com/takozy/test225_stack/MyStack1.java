package com.takozy.test225_stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列的实现
 *
 * 先push到temp 再将queue全部元素pop出来push到temp
 * 最后另temp和queue翻转temp=queue queue=temp
 *
 */
public class MyStack1 {

    private Queue<Integer> queue;
    private Queue<Integer> temp;

    public MyStack1() {
        queue = new LinkedList<Integer>();
        temp = new LinkedList<Integer>();
    }

    public void push(int x) {
        if (queue.isEmpty()) {
            queue.offer(x);
        } else {
            temp.offer(x);
            while (!queue.isEmpty()) {
                temp.offer(queue.poll());
            }
            Queue<Integer> a = queue;
            queue = temp;
            temp = a;
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
