package com.takozy.binarySearch;

public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public void insert(TreeNode node) {
        if (node.val <= this.val) {
            if (this.left == null) this.left = node;
            else this.left.insert(node);
        } else {
            if (this.right == null) this.right = node;
            else this.right.insert(node);
        }
    }
}
