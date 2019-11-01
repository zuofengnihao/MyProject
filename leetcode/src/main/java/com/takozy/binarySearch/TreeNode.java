package com.takozy.binarySearch;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public static void insert(TreeNode root, TreeNode newNode) {
        if (root.val > newNode.val) {
            if (root.left != null) {
                insert(root.left, newNode);
            } else {
                root.left = newNode;
            }
        } else {
            if (root.right != null) {
                insert(root.right, newNode);
            } else {
                root.right = newNode;
            }
        }
    }
}
