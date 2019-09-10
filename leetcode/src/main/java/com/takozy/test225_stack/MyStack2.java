package com.takozy.test225_stack;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 一个队列的实现
 *
 * push入队列后 翻转前面的元素 queue.offer(queue.poll())
 *
 */
public class MyStack2 {

    private Queue<Integer> queue;

    public MyStack2() {
        queue = new LinkedList<Integer>();
    }

    public void push(int x) {
        queue.offer(x);
        int size = queue.size();
        while (size > 1) {
            queue.offer(queue.poll());
            size--;
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
