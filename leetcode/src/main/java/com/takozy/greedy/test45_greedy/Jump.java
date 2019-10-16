package com.takozy.greedy.test45_greedy;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 *
 * 示例:
 *
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 *
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Jump {

    public static void main(String[] args) {
        System.out.println(jump1(new int[]{2,3,1,1,4,4,2,3,4,2,0,1,3}));
    }

    /**
     * 自己的思路1:两层循环
     * 从终点开始cur=nums.length-1;往前找找到最远能跳到cur节点的节点
     * 找到后count++(次数)如果此节点已经为0(即起点)跳出 否则将此节点
     * 下标赋予cur继续找最远节点
     *
     * 外层循环cur(当前节点) cur为0(开始节点)跳出循环
     * 内层循环往cur节点前找最远能跳到cur的节点(即最小下标) 一直到开始节点
     * @param nums
     * @return
     */
    public static int jump(int nums[]) {
        int count = 0, cur = nums.length - 1;
        while (cur > 0) {
            int flag = 1, min = cur;
            for (int i = cur - 1; i >= 0 ; i--) {
                if (nums[i] >= flag++) {
                    min = Math.min(min, i);
                }
            }
            if (min < cur) {
                cur = min;
                count++;
            } else {
                return 0;
            }
        }
        return count;
    }

    /**
     * 自己的思路2:
     * 贪心获取局部最优解
     *
     * 外层循环cur从0出发 如果 cur小于nums.length-1表示cur不为终点(终点时跳出循环)
     * 进入外循环步数+1 ++count 此时cur可跳跃的最远下标是 max=cur+nums[cur]
     * 如果max的值已经超过或者等于终点节点(nums.length-1)的下标
     * 表示cur可以直接跳到终点了 直接返回步数count 如果max未到终点
     * 开起内层循环从cur+1 -> max 贪心获取cur最优策略 判断cur可跳到的
     * 节点中 谁能为下次跳跃提供最远距离 找到此节点并赋给cur 重复外层循环
     *
     * @param nums
     * @return
     */
    public static int jump1(int nums[]) {
        int count = 0, cur = 0;
        while (cur < nums.length - 1) {
            ++count;
            int max = cur + nums[cur];
            if (max >= nums.length - 1) return count;
            int flag = 0;
            for (int i = cur + 1; i <= max; i++) {
                if (nums[i] + i > flag) {
                    flag = nums[i] + i;
                    cur = i;
                }
            }
        }
        return count;
    }
}
