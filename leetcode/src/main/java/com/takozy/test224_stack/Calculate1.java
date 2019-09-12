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
        System.out.println(calculate1("  2-1 + 2"));
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

    /**
     * 整体思路同上 字符串处理使用“有限状态自动机”思想处理
     * @param s
     * @return
     */

    public final static int BEGIN = 0; //开始状态
    public final static int NUMBER = 1; //数字状态
    public final static int OPERATION = 2; //操作状态

    public static int calculate1(String s) {
        boolean flag = false;
        Stack<Integer> numberStack = new Stack<>();
        Stack<Character> operationStack = new Stack<>();
        char[] chars = s.toCharArray();
        int number = 0;
        int status = BEGIN;
        for (int i = 0; i < s.length(); i++) {
            if (chars[i] == ' ') continue;
            switch (status) {
                case BEGIN: //开始状态
                    if (chars[i] >= '0' && chars[i] <= '9') {
                        status = NUMBER; //为数字 进入NUMBER状态 i退格
                    } else {
                        status = OPERATION; //不为数字(实际上不为数字只能为'(') 进入OPERATION状态
                    }
                    i--;//i 退格
                    break;
                case NUMBER: //数字状态
                    if (chars[i] >= '0' && chars[i] <= '9') {
                        number = number * 10 + chars[i] - '0'; //为数字时处理数字字符串
                    } else {
                        //不为数字时切换状态到 操作状态并尝试计算
                        numberStack.push(number); // 将数字压入栈
                        number = 0; // 归零number
                        status = OPERATION; //切换状态
                        i--; //i退格

                        //计算
                        if (flag) {
                            compute(numberStack, operationStack);
                        }
                    }
                    break;
                case OPERATION: //符号状态
                    if (chars[i] == '+' || chars[i] == '-') {
                        operationStack.push(chars[i]); //为符号时压入符号栈
                        flag = true; // 并将 可计算flag标为true
                    } else if (chars[i] == '(') {
                        flag = false; // 遇到左括号'('时 可计算flag标为false
                    } else if (chars[i] >= '0' && chars[i] <= '9') {
                        //为数字时切换状态
                        status = OPERATION;
                        i--;
                    } else if (chars[i] == ')') { //如果为')'计算
                        //计算
                        if (flag) {
                            compute(numberStack, operationStack);
                        }
                    }
                    break;
            }
        }

        if (number != 0) {
            numberStack.push(number);
            compute(numberStack, operationStack);
        }

        return numberStack.pop();
    }

    public static void compute(Stack<Integer> numberStack, Stack<Character> operationStack) {
        Character pop = operationStack.pop();
        int num = numberStack.pop();
        num = pop == '+' ? numberStack.pop() + num : numberStack.pop() - num;
        numberStack.push(num);
    }
}