package com.takozy.test142;

import java.util.HashSet;

public class DetectCycle {

    public static void main(String[] args) {
        ListNode a = new ListNode(3);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(0);
        ListNode d = new ListNode(-4);
        //ListNode e = new ListNode(5);
        //ListNode f = new ListNode(6);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = b;
        //d.next = e;
        //e.next = f;
        //f.next = d;

        System.out.println(detectCycle1(a));
    }

    /**
     * hashset暴力解法
     * @param head
     * @return
     */
    public static ListNode detectCycle(ListNode head) {
        HashSet<ListNode> listNodes = new HashSet<ListNode>();
        while (head != null) {
            if (listNodes.contains(head)) return head;
            listNodes.add(head);
            head = head.next;
        }
        return null;
    }

    /**
     * 双指针(快慢指针)
     * @param head
     * @return
     */
    public static ListNode detectCycle1(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode fast = head.next, slow = head;
        while (fast != slow) {
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
        }
        return fast;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        return this.val + "";
    }
}