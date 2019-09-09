package com.takozy.test25_linkedList;

import javax.xml.soap.Node;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeKLists {

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(5);
        a1.next = a2;a2.next = a3;

        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(3);
        ListNode b3 = new ListNode(4);
        b1.next = b2;b2.next = b3;

        ListNode c1 = new ListNode(2);
        ListNode c2 = new ListNode(6);
        c1.next = c2;

        ListNode listNode = mergeKLists(new ListNode[]{a1, b1, c1});
        System.out.println(listNode);
    }

    /**
     * 自己的思路
     *
     * 1 -> 4 -> 5
     * 1 -> 3 -> 4
     * 2 -> 6
     *
     * ==>
     *
     * 4 -> 5
     * 1 -> 3 -> 4
     * 2 -> 6
     *
     * ==>
     * 
     * 4 -> 5
     * 3 -> 4
     * 2 -> 6
     *
     * ==>
     *
     * 4 -> 5
     * 3 -> 4
     * 6
     *
     * ==>
     *
     * 4->5
     * 4
     * 6
     *
     * ==>
     * 5
     * 4
     * 6
     *
     * ==>
     * 5
     * null
     * 6
     *
     * ==>
     * null
     * null
     * 6
     *
     * ==>
     * null
     * null
     * null
     *
     * 每次循环找到最小值
     * 将最小值的node指针移动至下一位
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode newList = new ListNode(0);
        ListNode current = newList;

        ListNode node;
        int flag;
        while (true) {
            node = null;
            flag = -1;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] == null) continue;
                if (node == null) {
                    node = lists[i];
                    flag = i;
                } else if (node.val > lists[i].val) {
                    node = lists[i];
                    flag = i;
                }
            }
            if (node == null) {
                return newList.next;
            } else {
                lists[flag] = lists[flag].next;
                current.next = node;
                current = current.next;
            }
        }
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x){val = x;}
}