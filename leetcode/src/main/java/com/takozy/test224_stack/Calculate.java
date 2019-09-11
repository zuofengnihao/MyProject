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
        char space = ' ';
        System.out.println("空格 : " + Integer.valueOf(space));
        char a = '(';
        char b = ')';
        System.out.println("( ~ ) : " + (a-1+1) + " ~ " + (b-1+1));
        char c0 = '0';
        char c9 = '9';
        System.out.println("0 ~ 9 : " + (c0+1-1)+" ~ "+(c9+1-1));
        char plus = '+';
        char minus = '-';
        char multiply = '*';
        char divide = '/';
        System.out.println("加减乘除(+,-,*,/) : " + (Integer.valueOf(plus) + " " + Integer.valueOf(minus) + " " + Integer.valueOf(multiply) + " " + Integer.valueOf(divide)));

    }

    public int calculate(String s) {
        Stack<String> dataStack = new Stack<String>();
        Stack<String> numStack = new Stack<String>();
        Stack<String> signStack = new Stack<String>();
        return 0;
    }
}
