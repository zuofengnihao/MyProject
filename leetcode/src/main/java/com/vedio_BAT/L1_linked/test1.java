package com.vedio_BAT.L1_linked;

/**
 * 链表逆序
 */
public class test1 {

    public static void main(String[] args) {
        Node a = new Node(5);
        Node b = new Node(4);
        Node c = new Node(3);
        Node d = new Node(2);
        Node e = new Node(1);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        //a.printLinked();

        Node reverse = reverse(a);
        reverse.printLinked();

    }

    public static Node reverse(Node node) {
        Node newNode = null;
        while (node != null) {
            Node temp = node;
            node = node.next;
            temp.next = newNode;
            newNode = temp;
        }
        return newNode;
    }

}

class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return this.val + " ->";
    }

    public void printLinked() {
        Node node = this;
        while (node != null) {
            System.out.println(node);
            node = node.next;
        }
    }
}
