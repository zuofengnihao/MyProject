package com.takozy.test2;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode listNode = addTwoNumbers(l1, l2);
        while (listNode != null) {
            System.out.print(listNode.val);
            listNode = listNode.next;
        }
    }

    /**
     * 自己的解法
     * @param l1
     * @param l2
     * @return
     *
     * 声明一个进位值sum=0 声明一个新链表node 并让 cur = node
     * 同时循环两个链表 条件为任意链表不为空 或 进位值不为0
     * 取出当前链表的两个值与进位值3个数字相加并取莫10的余数即（(a + b + sum) % 10）赋予当前node
     * 并将3个值的和除以10的值赋予进位值sum = (a + b + sum) / 10
     * 如果其中有一个链表已为空 或者两个链表都为空 直接赋值0即可
     * 循环结束 返回新链表的next
     *
     *
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = 0;
        ListNode node = new ListNode(0);
        ListNode result = node;
        while (l1 != null || l2 != null || sum > 0) {
            int num1 = 0;
            int num2 = 0;
            if (l1 != null) {
                num1 = l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                num2 = l2.val;
                l2 = l2.next;
            }
            node.next = new ListNode((num1 + num2 + sum) % 10);
            node = node.next;
            sum = (num1 + num2 + sum) / 10;
        }
        return result.next;
    }

    /**
     * Leetcode官方解法
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }
}