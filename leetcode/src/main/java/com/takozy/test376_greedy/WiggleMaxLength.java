package com.takozy.test376_greedy;

/**
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 *
 * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 *
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 *
 * 示例 1:
 *
 * 输入: [1,7,4,9,2,5]
 * 输出: 6
 * 解释: 整个序列均为摆动序列。
 *
 * 示例 2:
 *
 * 输入: [1,17,5,10,13,15,10,5,16,8]
 * 输出: 7
 * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为[1,17,10,13,10,16,8]。
 *
 * 示例 3:
 *
 * 输入: [1,2,3,4,5,6,7,8,9]
 * 输出: 2
 *
 * 进阶:
 * 你能否用 O(n) 时间复杂度完成此题?
 */
public class WiggleMaxLength {

    static final int BEGIN = 0;
    static final int UP = 1;
    static final int DOWN = 2;

    /**
     *
     * l=1
     *
     *                    15
     *                13
     *    17      10          10     16
     * 1       5                  5      8
     *
     *                          -------------------------------------       ------------------------
     *                         | 连续的上升或者下降时的最优选择应该为最后 |    |                         |
     *                         | 一个值 此事值最大/小 利于下一次状态切换 |    |                         |
     * 1 UP 17 -> 17 DOWN 5 -> | 5 UP 10 -> 10 UP 13 -> 13 UP 15    | -> | 15 DOWN 10 -> 10 DOWN 5 | -> 5 UP 16 -> 16 DOWN 8
     *                         | 5 UP 13                            |    | 13                      |
     *                         | 5 UP 15                            |    |                         |
     *                         |贪心选择15                           |    |                         |
     *  l+1          l+1       |                                    |    |                         |      l+1          l+1
     *                         -------------------------------------     --------------------------
     *                                       l+1                                   l+1
     *
     * L=7
     *
     * @param nums
     * @return
     */
    public static int wiggleMaxLength(int[] nums) {
        if (nums.length < 2) return nums.length;
        int status = BEGIN, len = 1;
        for (int i = 0; i < nums.length; i++) {
            switch (status) {
                case BEGIN:
                    if (nums[i] < nums[i-1]) {
                        status = DOWN;
                        len++;
                    } else if (nums[i] > nums[i-1]) {
                        status = UP;
                        len++;
                    }
                    break;
                case UP:
                    if (nums[i] < nums[i-1]) {
                        status = DOWN;
                        len++;
                    }
                    break;
                case DOWN:
                    if (nums[i] > nums[i-1]) {
                        status = UP;
                        len++;
                    }
                    break;
            }
        }
        return len;
    }
}
