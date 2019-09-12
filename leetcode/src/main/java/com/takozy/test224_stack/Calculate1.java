package com.takozy.test224_stack;

import java.util.Stack;

/**
 * 利用两个栈 符号栈 数字栈
 *
 * 12 + ( 7 - 3 ) -> 12入数字栈
 * + ( 7 - 3 ) -> +入符号栈(可以计算)
 * ( 7 - 3 ) -> ( 左括号(无法计算)
 * 7 - 3 ) -> 7压入数字栈
 * - 3 ) -> -压入符号栈(可以计算)
 * 3 ) -> 3压入数字栈 此时可以计算 弹出3 7 - 计算7-3 结果4入数字栈
 * ) -> ) 右括号(可以计算) 弹出 4 12 + 计算12+4 结果16入栈
 * 循环结束 返回 数字栈poll值...
 */
public class Calculate1 {

    public static void main(String[] args) {
        System.out.println(calculate("  2-1 + 2"));
    }

    public static int calculate(String s) {
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<Character> actionStack = new Stack<Character>();
        char[] chars = s.toCharArray();

        String num = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                num += chars[i];
            } else {
                if (!"".equals(num)) {
                    numStack.push(Integer.valueOf(num));
                    num = "";
                    cal(numStack, actionStack);
                }

                if (chars[i] == '+' || chars[i] == '-' || chars[i] == '(') {
                    actionStack.push(chars[i]);
                } else if (chars[i] == ')' && actionStack.peek() == '(') {
                    actionStack.pop();
                    cal(numStack, actionStack);
                }
            }
        }

        if (!"".equals(num)) {
            numStack.push(Integer.valueOf(num));
            num = "";
            cal(numStack, actionStack);
        }

        return numStack.pop();
    }

    public static void cal(Stack<Integer> numStack, Stack<Character> actionStack) {
        if (numStack.size() > 1 && actionStack.size() > 0) {
            Character action = actionStack.peek();
            if (action != '(') {
                int result, factor = numStack.pop(); actionStack.pop();
                result = action == '+' ? numStack.pop() + factor : numStack.pop() - factor;
                numStack.push(result);
            }
        }
    }
}