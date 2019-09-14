package com.takozy.test232_queue;

import java.util.Stack;

/**
 * 使用栈实现队列的下列操作：
 *
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 * 示例:
 *
 * MyQueue queue = new MyQueue();
 *
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 * 说明:
 *
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MyQueue {

    private Stack<Integer> stack1;
    private Stack<Integer> stack2;


    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    /** Push element x to the back of queue. */
    /**
     * 使用两个栈实现队列 改变入栈规则（思路是保持新入栈的元素在栈底）
     * 当有元素入队列时 判断栈1是否为空 为空直接压入栈1
     * 如果不为空 则先将栈1的元素全部弹出并压入栈2 再将新元素压入栈1（此时新入栈的元素就在栈底）
     * 最后又将栈2的元素还原至栈1 即 弹出栈2并压入栈1
     * @param x
     */
    public void push(int x) {
        if (stack1.empty()) {
            stack1.push(x);
        } else {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
            stack1.push(x);//stack2.push(x);
            while (!stack2.empty()) {
                stack1.push(stack2.pop());
            }
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack1.pop();
    }

    /** Get the front element. */
    public int peek() {
        return stack1.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.empty();
    }

}
