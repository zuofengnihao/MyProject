package com.takozy.test92_linkedList;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseBetween {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        ListNode node = reverseBetween(a, 4, 5);
        System.out.println(node);
    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        int i = 1;
        ListNode reverse = head;
        ListNode preNode = null;
        ListNode nextNode = null;
        ListNode newHead = null;
        ListNode newLast = null;
        while (head != null) {
            ListNode tempNext = head.next;
            if (i == m - 1) {
                preNode = head;
            } else if (i == n + 1) {
                nextNode = head;
            } else if (i >= m && i <= n) {
                head.next = newHead;
                if (i == m) {
                    newLast = head;
                }
                newHead = head;
            }
            head = tempNext;
            i++;
        }
        newLast.next = nextNode;
        if (preNode != null) {
            preNode.next = newHead;
            return reverse;
        } else {
            return newHead;
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        ListNode node = this;
        String result = "";
        while (node != null) {
            result += node.val + " -> ";
            node = node.next;
        }
        return result;
    }
}
