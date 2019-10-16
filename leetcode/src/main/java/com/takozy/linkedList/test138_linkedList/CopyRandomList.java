package com.takozy.linkedList.test138_linkedList;

import java.util.HashMap;

/**
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 *
 * 要求返回这个链表的深拷贝。 
 *
 *  
 *
 * 示例：
 *
 *
 *
 * 输入：
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 *
 * 解释：
 * 节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
 * 节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 *  
 *
 * 提示：
 *
 * 你必须返回给定头的拷贝作为对克隆列表的引用。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/copy-list-with-random-pointer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CopyRandomList {

    public static void main(String[] args) {
        Node a = new Node();
        a.val = 1;
        Node b = new Node();
        b.val = 2;
        Node c = new Node();
        c.val = 3;
        Node d = new Node();
        d.val = 4;
        a.next = b;
        b.next = c;
        c.next = d;
        a.random = b;
        c.random = c;
        d.random = a;
        Node temp = a;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }

        Node node = copyRandomList1(a);

        System.out.println("=================================");

        while (node != null) {
            System.out.println(node);
            node = node.next;
        }

    }

    /**
     * 自己的解法 O(n)
     *
     * 使用两个HashMap一个储存需要被复制链表head的节点Node(已遍历) key为node对象 value为标识值(此处我用的Integer)
     * 另外一个map存储复制的head’ 中已新建的Node key为标识值 value为新建的Node对象
     *
     * 遍历head 判断当前节点cur是否已存在于map1中 不存在则将此node加入map1 并且新建node并加入map2 此时的标识值必须一致
     * 存在则根据node获取map1中的标识值 再根据标识值获取map2中的新建节点作为当前节点
     * 再获取当前节点的random节点 判断是否存在于map1中 不存在则将此node的random节点加入map1 并且新建node 让上一步中新建的node
     * 的random指向此新建节点node 并将这个新建的节点也放入map2(保证标识值一致) 循环直至遍历结束 返回结果
     *
     * @param head
     * @return
     */
    public static Node copyRandomList(Node head) {
        if (head == null) return null;
        HashMap<Node, Integer> nodes = new HashMap<Node, Integer>();
        HashMap<Integer, Node> list = new HashMap<Integer, Node>();
        Node result = new Node();
        Node cur = result;
        int i = 0;
        while (head != null) {
            Node node;
            if (nodes.containsKey(head)) {
                Integer index = nodes.get(head);
                node = list.get(index);
                cur.next = node;
            } else {
                node = new Node();
                cur.next = node;
                node.val = head.val;
                nodes.put(head, i);
                list.put(i, node);
                i++;
            }
            if (head.random != null) {
                if (nodes.containsKey(head.random)) {
                    Integer index = nodes.get(head.random);
                    Node random = list.get(index);
                    node.random = random;
                } else {
                    Node random = new Node();
                    random.val = head.random.val;
                    node.random = random;
                    nodes.put(head.random, i);
                    list.put(i, random);
                    i++;
                }
            }
            head = head.next;
            cur = cur.next;
        }
        return result.next;
    }

    /**
     * 官方解法 O(3n)
     *
     * 第一次循环 复制 将 链表 A-> B-> C ==> A->A'->B->B'->C->C'
     * 第二次循环 链接random 获取每个非复制节点的random 让复制节点random'指向random.next
     * 第三次循环 拆分链表为 A'->B'->C'
     */
    public static Node copyRandomList1(Node head) {
        if (head == null) return null;
        Node cur = head;
        while (cur != null) {
            Node node = new Node();
            node.val = cur.val;
            node.next = cur.next;
            cur.next = node;
            cur = cur.next.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        cur = head;
        Node temp = new Node();
        Node node = temp;
        while (cur != null) {
            node.next = cur.next;
            cur.next = cur.next.next;
            cur = cur.next;
            node = node.next;
        }
        return temp.next;
    }
}

class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int val, Node next, Node random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }

    @Override
    public String toString() {
        String random = this.random == null ? "null" : this.random.val + "";
        String next = this.next == null ? "null" : this.next.val + "";
        return "val:" + this.val + " next:" + next + " random:" + random;
    }
}
