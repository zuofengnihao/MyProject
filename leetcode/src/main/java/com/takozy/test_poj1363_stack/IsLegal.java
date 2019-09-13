package com.takozy.test_poj1363_stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 已知1-n的数字序列 按顺序入栈 每个数字入栈后即可出栈 也可以停留在栈中 等待后面的数字入栈
 * 该数字再出栈 求该数字序列的出栈序列是否合法
 *
 * 例: 1-2-3-4-5
 * 1-2-3-4-5 合法
 * 5-4-3-2-1 合法
 * 3-2-5-4-1 合法
 * 3-1-2-4-5 不合法
 *
 *
 */
public class IsLegal {

    public static void main(String[] args) {
        boolean legal = isLegal(new int[]{1, 2, 3, 4, 5}, new int[]{3,1,2,4,5});
        System.out.println(legal);
    }


    public static boolean isLegal(int[] before, int[] after) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        for (int i : after) {
            queue.offer(i);
        }

        for (int i = 0; i < before.length; i++) {
            stack.push(before[i]);
            while (!stack.empty()&&queue.peek().equals(stack.peek())) {
                queue.poll();
                stack.pop();
            }
        }

        return queue.isEmpty();
    }
}
