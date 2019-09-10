package com.takozy.test232_queue;

import java.util.Stack;

public class MyQueue2 {
    private Integer top;
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;

    public MyQueue2() {
        inStack = new Stack<Integer>();
        outStack = new Stack<Integer>();
    }

    public void push(int x) {
        inStack.push(x);
        if (inStack.empty()) {
            top = x;
        }
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
