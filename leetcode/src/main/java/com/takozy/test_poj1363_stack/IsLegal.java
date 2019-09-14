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
 * 将改变后的list(需要判断的序列)全部元素入队列 再循环改变前的list逐个入栈（外循环） 每次入栈一个元素后
 * 循环判断入栈的top元素与队列的top元素是否相等（内循环） 如果相等 这两个元素同时出栈/出队列
 * 直到不相等或者栈为空 如此直到外循环全部结束 如果此时队列（或者栈）为空 则表示该序列合法
 *
 * 例：1 2 3 4 5 | 3 2 5 4 1
 *
 *     ---------        ---------
 *  <- 3 2 5 4 1        1 2 3 4 5
 *     ---------  |   | ---------
 *                ----
 *
 *     ---------         ---------
 *  <- 3 2 5 4 1         2 3 4 5
 *     ---------  | 1 | ---------
 *                ----
 *
 *     ---------        ---------
 *  <- 3 2 5 4 1        3 4 5
 *     ---------  | 2 | ---------
 *                | 1 |
 *                ----
 *     ---------        ---------
 *  <- 3 2 5 4 1        4 5
 *     ---------  | 3 | ---------
 *                | 2 |
 *                | 1 |
 *                ----
 *   3 = 3 出 2 = 2 也出
 *
 *     ---------        ---------
 *  <- 5 4 1            4 5
 *     ---------  | 1 | ---------
 *                |   |
 *                |   |
 *                ----
 *     ---------        ---------
 *  <- 5 4 1            5
 *     ---------  | 4 | ---------
 *                | 1 |
 *                |   |
 *                ----
 *     ---------        ---------
 *  <- 5 4 1
 *     ---------  | 5 | ---------
 *                | 4 |
 *                | 1 |
 *                ----
 *  5 = 5 出 | 4 = 4 出 | 1 = 1 出
 *
 *     ---------        ---------
 *  <-
 *     ---------  |  | ---------
 *                |  |
 *                |  |
 *                ----
 *  合法
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
