package com.takozy.binaryTree.test114_tree;

import com.takozy.binaryTree.TreeNode;

public class Flatten {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        n1.left = n2;n1.right = n5;
        n2.left = n3;n2.right = n4;
        n5.right = n6;

        Flatten flatten = new Flatten();
        flatten.flatten(n1);
        TreeNode current = n1;
        while (current != null) {
            System.out.print(current.val + "\t");
            current = current.right;
        }
    }

    public TreeNode flatten(TreeNode root) {
        TreeNode father = new TreeNode();
        method(root, father);
        return father.right;
    }

    public void method(TreeNode node, TreeNode parent) {
        if (node == null) return;
        parent.right = node;
        TreeNode right = node.right;
        method(node.left, node);
        method(right, node);
    }
}
