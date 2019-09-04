package com.takozy.test206;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class ReverseList {

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

        ListNode newList = reverseList(a);
        System.out.println(newList);
    }


    public static ListNode reverseList(ListNode head) {
        ListNode newNode = null;
        while (head != null) {
            ListNode temp = head;
            head = head.next;
            temp.next = newNode;
            newNode = temp;
        }
        return newNode;
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