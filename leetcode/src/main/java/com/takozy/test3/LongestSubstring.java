package com.takozy.test3;

import java.util.HashMap;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubstring {
    public static void main(String[] args) {
        System.out.println(test("abcaacde"));
    }

    /**
     * 自己的解法
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int start = 0,end = 1,result = 0,lose = 0;
        String sub;
        for (; end <= s.length(); end ++) {
            sub = s.substring(start, end);
            if (end < s.length()) {
                String current = s.substring(end, end + 1);
                if (sub.contains(current)) {
                    lose += sub.indexOf(current) + 1;
                    start = lose;
                }
            }
            if (result < sub.length()) result = sub.length();
        }
        if (result == 0) result = s.length();
        return result;
    }

    /**
     * leetcode(HashMap)
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring1(String s) {
        //pwwkew
        int ans = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0, j = 0; i < s.length(); i ++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)));
            }
            ans = Math.max(ans, i - j + 1);
            map.put(s.charAt(i), i + 1);
        }
        return ans;
    }

    /**
     * 利用hashmap来判断是否重复字符 key存字符 value存该字符的下标
     * 初始化需要减去的长度flag(即重复字母的下标+1的长度) 初始值是0(不需要减去) 初始化返回值result=0
     * 遍历字符串 判断当前下标i对应的字符有无重复时 无重复 需要减去的长度flag不变
     * 当出现重复时 取出map中该字符的下标+1 并判断这个长度是否比当前需要减去的长度长
     * 如果此值>flag flag取这个值 如果小于flag 说明这两个重复字符之间已经存在其他重复字符 flag不变
     * 将此字符put至map中并保存下标 并判断此时的结果result是否大于(下标 - flag + 1) 大于result不变 反之result = 下标 - flag + 1;
     * @param s
     * @return
     */
    public static int test(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int flag = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                flag = flag > map.get(s.charAt(i)) + 1 ? flag : map.get(s.charAt(i)) + 1;
            }
            map.put(s.charAt(i), i);
            result = result > i - flag + 1 ? result : i - flag + 1;
        }
        return result;
    }
}
