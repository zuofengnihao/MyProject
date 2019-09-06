package com.takozy.test138_linkedList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        b.random = d;
        c.random = c;
        d.random = c;

        Node node = copyRandomList(a);

    }

    public static Node copyRandomList(Node head) {
        HashMap<Node, Integer> map = new HashMap<Node, Integer>();
        ArrayList<Node> list = new ArrayList<Node>();
        int i = 0;
        Node flag = head;
        while (head != null) {
            Node node = new Node();
            node.val = head.val;
            list.add(node);
            map.put(head, i);
            i++;
        }
        head = flag;
        int j = 0;
        while (head != null) {
            Integer index = map.get(head.random);
            Node node = list.get(j);
            node.next = list.get(j+1);
            node.random = list.get(index);
            j++;
        }
        return list.get(0);
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
}
