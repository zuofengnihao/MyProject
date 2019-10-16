package com.takozy.greedy.test232_queue;

import java.util.Stack;


/**
 * 使用两个栈 一个表示 入队列栈 一个表示 出队列栈
 * 当有元素要入队列 直接入 入栈 当入的元素是栈底元素时标记为top
 * 当有元素要出队列 判断出栈是否为空 不为空直接弹栈poll返回 为空则将入栈全部poll出并push至出栈 再poll一个元素返回
 * 当调用peek时（查看队列头元素）判断出栈是否为空 不为空 直接 peek 出栈 为空 返回标记的入栈栈底元素 top
 */
public class MyQueue2 {
    private Integer top;
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public MyQueue2() {
        inStack = new Stack<Integer>();
        outStack = new Stack<Integer>();
    }

    public void push(int x) {
        if (inStack.empty()) {
            top = x;
        }
        inStack.push(x);
    }

    public int pop() {
        if (outStack.empty()) {
            while (!inStack.empty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

    public int peek() {
        if (outStack.empty()) {
            return top;
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.empty() && outStack.empty();
    }

}
