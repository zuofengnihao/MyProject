package com.takozy.greedy.test55_greedy;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 从位置 0 到 1 跳 1 步, 然后跳 3 步到达最后一个位置。
 *
 *
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class CanJump {

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{3,2,1,0,4}));
    }

    /**
     * 自己的思路：
     * 循环数组(抛开末端元素 因为末端元素不再需要跳跃)
     * 如果数组中没有值为0的即可返回true(没有值为0的即可1步步跳到末端)
     * 如果数组中出现了为0的则判断此节点是否为必经之路
     * 判断是否必经之路即判断0之前的数是否能大于它到0的距离(即可以跳过此点 不为必经)
     * 循环i-1到0 中间出现了能够跳过0节点的节点直接break 如果一直到头节点(下标0)
     * 都没有出现可以跳过此0节点的节点则直接返回false
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        if (nums[0] == 0 && nums.length > 1) return false;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                int cur = 1;
                for (int j=i-1; j>=0; j--) {
                    if (nums[j] > cur++) break;
                    else if (j == 0) return false;
                }
            }
        }
        return true;
    }
}
