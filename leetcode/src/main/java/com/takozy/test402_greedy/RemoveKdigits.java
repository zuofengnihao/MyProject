package com.takozy.test402_greedy;

import java.util.Stack;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 注意:
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 *
 * 示例 1 :
 *
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 *
 * 示例 2 :
 *
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 *
 * 示例 3 :
 *
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 */
public class RemoveKdigits {

    public static void main(String[] args) {
        String s = removeKdigits("2413219", 3);
        System.out.println(s);
    }

    /**
     * 使用一个栈来处理
     * 遍历num 如果栈为空 直接压入 如果栈不为空
     * 再循环栈对比当前值是否小于栈顶的值 小于栈顶的值
     * 弹出栈顶并且令k-1(表示移除了一个数了)
     * 直到栈顶为空或者栈顶的值大于或等于当前值或者
     * k=0(表示不能再移除了)
     *
     * 外层循环结束后 判断k是否为0(判断是否抛弃完全)
     * 如果k!=0 一次移除栈顶k个元素
     *
     * 最后将栈中的数组组合成string返回
     *
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        char[] chars = num.toCharArray();
        String res = "";
        for(int i=0; i<chars.length; i++) {
            while (stack.size() > 0 && chars[i] < stack.peek() && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(chars[i]);
        }

        if (k > 0) {
            for (int i=0;i<k;i++) {
                stack.pop();
            }
        }

        for (char c : stack) {
            if (res.equals("") && c == '0') continue;
            else res += c;
        }

        if (res.equals("")) return "0";

        return res;
    }
}
