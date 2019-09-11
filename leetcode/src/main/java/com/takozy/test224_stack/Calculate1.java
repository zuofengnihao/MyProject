package com.takozy.test224_stack;

import java.util.Stack;

public class Calculate1 {

    public static void main(String[] args) {
        calculate("(1+(4+5+2)-3)+(6+8)");
    }

    public static int calculate(String s) {
        Stack<Integer> numStack = new Stack<Integer>();
        Stack<Character> actionStack = new Stack<Character>();
        char[] chars = s.toCharArray();

        String num = "";
        for (int i=0;i<chars.length;i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                num += chars[i];
            } else {
                if ("".equals(num)) {
                    numStack.push(Integer.valueOf(num));
                    num = "";
                }
                if (chars[i] == '+' || chars[i] == '-') {
                    actionStack.push(chars[i]);
                }
            }
        }

        return numStack.pop();
    }
}
