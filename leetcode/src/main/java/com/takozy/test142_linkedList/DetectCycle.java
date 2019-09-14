package com.takozy.test142_linkedList;

import java.util.HashSet;

/**
 * 判断链表是否有环并且返回环起点节点
 */
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
     * 循环链表 将所以node放入hashset
     * 当第一次出现重复的node 即hashset.contains(head)时 此节点就是环的起点
     *
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
     *
     *
     * A       B      C
     * #-------#------#
     *         |      |
     *         |      |
     *         |      |
     *         |      |
     *         #------#
     *         E      D(交点)
     *
     *  A->B的长度 = F(非环内节点) | B->C->D的长度 = a | D->E->B的长度 = b | 环的长度则为 a + b
     *
     *  f fast指针走过的长度 s slow指针走过的长度
     *
     *  f = 2s | s = F + a | f = s + n(a + b) ===>  2(F+a) = (F+a) + n(a+b) ===> F = n(a+b) - a ===> F = (n-1)(a+b) + b
     *
     *  推导的结论告诉我们slow指针在相交后 再走过F的距离 则会回到环型的入口节点处
     */
    public static ListNode detectCycle1(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                fast = head;
                break;
            }
        }
        if (fast == null || fast.next == null) return null;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
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