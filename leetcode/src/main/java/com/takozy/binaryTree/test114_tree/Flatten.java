package com.takozy.binaryTree.test114_tree;

import com.takozy.binaryTree.TreeNode;

/**
 * 给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 */
public class Flatten {

    private TreeNode father;

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

    public void flatten(TreeNode root) {
        if(root == null) return;
        if (father != null) {
            father.right = root;
            father.left = null;
        }
        TreeNode right = root.right;
        father = root;
        flatten(root.left);
        flatten(right);
    }
}
