package com.takozy.test78_recurrence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Subsets {

    public static void main(String[] args) {
        List<List<Integer>> subsets = subsets(new int[]{1, 2, 2});
        System.out.println(subsets);
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        lists.add(new ArrayList<Integer>());
        addItem(0, nums, item, lists);
        return lists;
    }


    /**
     * 回溯递归
     * 先走添加此元素的路径 再走不添加此元素的路劲
     *
     * 以 {A,B,C} 为例 走左侧为添加路径 右侧为不添加路径 顺序为:深度->广度
     * r为return ()中为弹出后item中的元素
     * 下标
     *
     * 0[A]                                           A()
     *
     * 1[B]                     A,B(A)                                   B()
     *
     * 2[C]         A,B,C(A,B)              A,C(1)             B,C(2)           C()
     *
     * 3[out]     r            r        r            r     r          r      r         r
     *
     * @param index
     * @param nums
     * @param item
     * @param lists
     */
    public static void addItem(int index, int[] nums, List<Integer> item, List<List<Integer>> lists) {
        if (index >= nums.length) return;
        item.add(nums[index]);
        ArrayList<Integer> l = new ArrayList<>();
        l.addAll(item);
        lists.add(l);
        addItem(index + 1, nums, item, lists);
        item.remove(item.size()-1);
        addItem(index + 1, nums, item, lists);
    }

    /**
     * 位运算
     *
     * 例:{A,B,C} A为001=>1 B为010=>2  C为100=>3 (=> 2进制转10进制)
     *
     * A出现 则2进制中的 1位(第1位) 为1 不出现则为0
     * B出现 则2进制中的 2位(第2位) 为1 不出现则为0
     * C出现 则2进制中的 4位(第3位) 为1 不出现则为0
     *
     * 如下图
     *
     * 0    000    []
     * 1    001    [A]
     * 2    010    [B]
     * 3    011    [A,B]
     * 4    100    [C]
     * 5    101    [A,C]
     * 6    110    [B,C]
     * 7    111    [A,B,C]
     *
     * 由上图规律发现 当数组的size为n时 所有元素组合的个数就为1<<size(3)=8;
     * 此时只需要循环 0->7 即为 所以组合的出现规则都会罗列出来
     * 所以外层使用 for(i=0;i<(1>>size);i++) 来表示每个组合形式
     *
     * 内层循环则直接循环给定的条件集合 for(i=0;i<nums.length;i++)
     * 以{A,B,C}集合为例的话 A是下标0 让1<<0位 即为001
     * B下标是1 让1<<1位 即为010 C下标是2 让1<<2位 即为100
     * 以此数字来表示该元素出现在第几位
     *
     * 外层循环的组合形式 & 上内层循环的该元素的出现位置
     * 如果此值大于0 则表示该元素需要添加至集合
     *
     * 以上为例子 当外层循环i为5时 组合形式是101
     * 内层循环 0 ==> 101 & (1 >> 0) = 101 & 001 = 001 大于0 放入nums[0]=>A
     * 内层循环 1 ==> 101 & (1 >> 1) = 101 & 010 = 000 等于0 不放入nums[1]=>B
     * 内层循环 2 ==> 101 & (1 >> 2) = 101 & 100 = 100 大于0 放入nums[2]=>C
     * 内层循环结束 得到集合 [A,C] 正好与上图吻合
     *
     * @param nums
     */
    public static List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        int size = 1 << nums.length;
        for (int i = 0; i < size; i++) {
            List<Integer> integers = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if ((i & (1 << j)) > 0) {
                    integers.add(nums[j]);
                }
            }
            lists.add(integers);
        }
        return lists;
    }
}
