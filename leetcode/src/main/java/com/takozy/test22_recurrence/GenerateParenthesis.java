package com.takozy.test22_recurrence;

import java.util.ArrayList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 *
 * 例如，给出 n = 3，生成结果为：
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */
public class GenerateParenthesis {

    public static void main (String args[]) {
        System.out.println(generateParenthesis(3));
    }

    /**
     * 回溯
     * 有n对括号 说明有2n个字符 '('n个 ')'n个
     * 递归生成String 每次拼接一个'('或者')'
     * 因为要保证括号的顺序有效
     * 即 '('必须优先于')'出现
     * 当l为左括号的个数n r为右括号的个数n时
     * 从s为""空字符串开始循环
     * 当 l和r 都为0时 添加至集合并返回
     * 当 l>0 时 递归调用 +'(' 并 l-1;
     * 当 r>0 且 r>l 时 递归点用 +')' 并 r-1;
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList();
        generate(n, n, "", list);
        return list;
    }

    public static void generate(int l, int r, String s, List<String> list) {
        if (l + r == 0) {
            list.add(s);
            return;
        }
        if (l > 0) generate(l - 1, r, s + '(', list);
        if (r > 0 && r > l) generate(l, r - 1, s + ')', list);
    }
}
