package com.takozy.test86;

public class Partition {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(2);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(2);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;



    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode point = null, prePoint = null;
        while (head != null) {
            ListNode tempHead = head.next;
            ListNode tempPreHead = preHead.next;
            if (point == null && head.val >= x) {
                point = head;
                prePoint= preHead;
            } else if (point != null && head.val < x) {
                prePoint.next = head;
                prePoint = head;
                preHead = head.next;
            }
        }

        return null;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode (int x) {
        this.val = x;
    }
}
