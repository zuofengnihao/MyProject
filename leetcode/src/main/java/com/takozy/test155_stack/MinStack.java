package com.takozy.test155_stack;

import java.util.Stack;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * 暴力思路
 * 初始化mini指针 每次push时判断是否mini 小于则mini指向新push的值
 * 每次pop时判断 是否pop的是mini 如果pop的是mini 则循环栈内所以元素找到mini
 */
public class MinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());

    }

    private Integer mini;
    private Stack<Integer> stack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if (mini == null) mini = x;
        else mini = x < mini ? x : mini;
    }

    public void pop() {
        Integer pop = stack.pop();
        if (pop.equals(mini)) {
            mini = null;
            for (Integer i: stack) {
                if (mini == null) mini = i;
                else mini = i < mini ? i : mini;
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public Integer getMin() {
        return mini;
    }
}
