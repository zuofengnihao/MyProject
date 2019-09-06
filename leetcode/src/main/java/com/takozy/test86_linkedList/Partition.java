package com.takozy.test86_linkedList;

/**
 * 分割链表
 *
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 *
 * 示例:
 *
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Partition {

    public static void main(String[] args) {
        ListNode a = new ListNode(4);
        ListNode b = new ListNode(3);
        ListNode c = new ListNode(2);
        ListNode d = new ListNode(5);
        ListNode e = new ListNode(2);
        ListNode f = new ListNode(1);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;

        ListNode listNode = partition1(a, 4);
        System.out.println(listNode);

    }

    /**
     * 自己的解法 运用4个指针
     * 找到第一个大于等于x的值 指针为 point 并将 prePoint指向point前一个node
     * point存在的情况下往下找到值小于x的node(head指针)和其前面一个node(preHead指针)
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null) return null;
        ListNode newHead = new ListNode(0),
                 preHead = newHead,
                 point = null,
                 prePoint = null;
        newHead.next = head;
        while (head != null) {
            if (point == null && head.val >= x) {
                point = head;
                prePoint= preHead;
                preHead = preHead.next;
                head = head.next;
            } else if (point != null && head.val < x) {
                ListNode temp = head.next;
                prePoint.next = head;
                preHead.next = head.next;
                head.next = point;
                prePoint = head;
                head = temp;
            } else {
                preHead = preHead.next;
                head = head.next;
            }
        }
        return newHead.next;
    }

    /**
     * 官方思路 遍历 小于->before 大等->after
     * @param head
     * @param x
     * @return
     */
    static ListNode partition1(ListNode head, int x) {
        ListNode beforeHead = new ListNode(0);
        ListNode afterHead = new ListNode(0);
        ListNode before = beforeHead, after = afterHead;
        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }
        after.next = null;
        before.next = afterHead.next;
        return beforeHead.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode (int x) {
        this.val = x;
    }
}
