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
        System.out.println(longestPalindrome_2("cababa"));
    }

    /**
     * 暴力解题
     * @param s
     * @return
     */
    public static String longestPalindrome_1(String s) {
        String asn = "";
        for (int i = 0; i < s.length(); i ++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String test = s.substring(i, j);
                if (isPalindrome(test)) {
                    asn = test.length() > asn.length() ? test : asn;
                }
            }
        }
        return asn;
    }
    public static boolean isPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(s.length()-1-i)) {
                return false;
            }
        }
        return true;
    }



    public static String longestPalindrome_2(String s) {
        String _s = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            _s += s.charAt(i);
        }
        return null;
    }
}
