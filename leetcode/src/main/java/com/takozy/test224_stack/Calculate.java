package com.takozy.test224_stack;

import java.util.Stack;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 *
 * 示例 1:
 *
 * 输入: "1 + 1"
 * 输出: 2
 * 示例 2:
 *
 * 输入: " 2-1 + 2 "
 * 输出: 3
 * 示例 3:
 *
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 * 说明：
 *
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Calculate {

    public static void main(String[] args) {
        int calculate = calculate("2147483647");
        System.out.println(calculate);
    }

    /**
     * 自己的思路 利用两个栈(数据 计算) 数字 左括号 运算符号 直接入数据栈
     * 碰到 右括号时 连续出栈并将出栈的元素压入计算栈 直到碰到栈内的左括号
     * 再将计算栈的全部弹出(正序过程)进行计算后 将结果压入 数据栈
     * 循环结束后 再将数据栈的依次弹出压出计算栈后再依次弹出 计算出最终结果
     * @param s
     * @return
     */
    public static int calculate(String s) {
        Stack<String> dataStack = new Stack<String>();
        Stack<String> numStack = new Stack<String>();
        char[] chars = s.toCharArray();
        String num = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ')') {
                if (!"".equals(num)) {
                    dataStack.push(num);
                    num = "";
                }
                while (!"(".equals(dataStack.peek())) {
                    numStack.push(dataStack.pop());
                }
                dataStack.pop();
                int sum = 0;
                while (!numStack.empty()) {
                    String pop = numStack.pop();
                    if ("+".equals(pop)) {
                        sum += Integer.valueOf(numStack.pop());
                    } else if ("-".equals(pop)) {
                        sum -= Integer.valueOf(numStack.pop());
                    } else {
                        sum += Integer.valueOf(pop);
                    }
                }
                dataStack.push(sum + "");
            } else if(chars[i] >= '0' && chars[i] <= '9') {
                num += chars[i];
            } else if (chars[i] != ' ') {
                if (!"".equals(num)) {
                    dataStack.push(num);
                    num = "";
                }
                dataStack.push(chars[i] + "");
            }
        }
        if (!"".equals(num)) {
            dataStack.push(num);
            num = "";
        }
        while (!dataStack.empty()) {
            numStack.push(dataStack.pop());
        }
        int result = 0;
        while (!numStack.empty()) {
            String flag = numStack.pop();
            if ("+".equals(flag)) {
                result += Integer.valueOf(numStack.pop());
            } else if ("-".equals(flag)) {
                result -= Integer.valueOf(numStack.pop());
            } else {
                result += Integer.valueOf(flag);
            }
        }
        return result;
    }
}
