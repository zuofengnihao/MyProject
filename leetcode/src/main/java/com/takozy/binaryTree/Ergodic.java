package com.takozy.binaryTree;

/**
 * 二叉树遍历的三种方式
 */
public class Ergodic {

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        n1.left = n2;
        TreeNode n3 = new TreeNode(3);
        n2.left = n3;
        TreeNode n4 = new TreeNode(4);
        n2.right = n4;
        TreeNode n5 = new TreeNode(5);
        n1.right = n5;
        TreeNode n6 = new TreeNode(6);
        n5.right = n6;
        /**
         *       1
         *   2      5
         * 3   4  N   6
         *
         */


        Ergodic ergodic = new Ergodic();
        System.out.print("前序遍历:" + "\t");
        ergodic.ergodicFront(n1);
        System.out.print("\n" + "中序遍历:" + "\t");
        ergodic.ergodicMid(n1);
        System.out.print("\n" + "后序遍历:" + "\t");
        ergodic.ergodicBehind(n1);
    }

    /**
     * 前序遍历 先访问node本身
     * @param node
     */
    public void ergodicFront(TreeNode node) {
        if (node == null) return;
        System.out.print(node.val + "\t");
        ergodicFront(node.left);
        ergodicFront(node.right);
    }

    /**
     * 中序遍历 先访问左节点再访问node本身
     * @param node
     */
    public void ergodicMid(TreeNode node) {
        if (node == null) return;
        ergodicMid(node.left);
        System.out.print(node.val + "\t");
        ergodicMid(node.right);
    }

    /**
     * 后续遍历 先访问左右节点 最后访问node本身
     * @param node
     */
    public void ergodicBehind(TreeNode node) {
        if(node == null) return;
        ergodicBehind(node.left);
        ergodicBehind(node.right);
        System.out.print(node.val + "\t");
    }
}
