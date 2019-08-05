package com.takozy.test5;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abacab"));
    }

    public static String longestPalindrome(String s) {
<<<<<<< HEAD

=======
        if ("".equals(s)) return "";
        int begin = 0, end = 0;

        for (int i = 0; i < s.length(); i ++) {
            for (int j = s.length() - 1; j > i; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    boolean flag = true;
                    while (i < j) {
                        if (s.charAt(i++) != s.charAt(j--)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {

                    } else {
                        i = begin;
                        j = end;
                    }
                }
            }
        }
        return "".equals(s) ? "" : s.substring(0, 1);
>>>>>>> 2615c0805b57ae1310d3cc55e4678963880f4d3f
    }
}
