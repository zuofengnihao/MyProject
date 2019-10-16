package com.takozy.linkedList.test21_linkedList;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(4);
        ListNode d = new ListNode(7);
        ListNode e = new ListNode(1);
        ListNode f = new ListNode(3);
        ListNode g = new ListNode(4);

        a.next = b;
        b.next = c;
        c.next = d;
        e.next = f;
        f.next = g;

        ListNode node = mergeTwoLists(a, e);

        System.out.println(node);
    }

    /**
     * 自己的思路 O(m+n)
     *
     * 创建新链表 循环两个链表找到最小的 放在新链表后面
     * 循环结束 返回新链表.next
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists (ListNode l1, ListNode l2) {
        ListNode listNode = new ListNode(0);
        ListNode cn = listNode;
        while (l1 != null || l2 != null) {
             if (l1 == null) {
                cn.next = l2;
                break;
             } else if (l2 == null) {
                cn.next = l1;
                break;
             } else {
                 if (l1.val < l2.val) {
                     cn.next = l1;
                     l1 = l1.next;
                     cn = cn.next;
                 } else if (l2.val < l1.val) {
                     cn.next = l2;
                     l2 = l2.next;
                     cn = cn.next;
                 } else  {
                     cn.next = l1;
                     l1 = l1.next;
                     cn.next.next = l2;
                     l2 = l2.next;
                     cn = cn.next.next;
                 }
             }
        }
        return listNode.next;
    }
}
