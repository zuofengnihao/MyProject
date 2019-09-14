package com.takozy.test141_linkedList;

import java.util.HashSet;

/**
 * 判断链表是否有环
 */
public class HasCycle {

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
        e.next = b;

        System.out.println(hasCycle1(a));

    }

    /**
     * HashSet t = O(n) s = O(n)
     * 暴力解法 使用hashset 循环链表放入hashset 判断是否已经包含 如果有(重复)则证明有环
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> listNodes = new HashSet<ListNode>();
        while (head != null) {
            if (listNodes.contains(head)) return true;
            listNodes.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * 快慢指针(双指针) t = O(n)
     *
     * 快慢指针同时从第一个节点出发 慢指针step=1 快指针step=2
     * 当快指针指向null时证明没有环
     *
     * 有环快指针与慢指针定会交于环中
     *
     * @param head
     * @return
     */
    public static boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head, fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) return false;
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}