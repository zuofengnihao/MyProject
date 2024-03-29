package com.takozy.stackHeap.test155_stack;

import java.util.Stack;

/**
 * 利用两个栈 一个压入值 一个压入当前最小值
 *
 * |  0  |    | -3  |
 * |  -3 |    | -3  |
 * |  5 |    |  1 |
 * | 1  |    | 1  |
 * -----    -----
 * 值栈      最小值栈
 *
 *
 */
public class MinStack1 {

    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack1() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int x) {
        if (minStack.empty()) {
            minStack.push(x);
        } else {
            Integer peek = minStack.peek();
            minStack.push(x < peek ? x : peek);
        }
        stack.push(x);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public Integer getMin() {
        return minStack.peek();
    }
}
