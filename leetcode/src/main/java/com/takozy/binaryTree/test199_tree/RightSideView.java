package com.takozy.binaryTree.test199_tree;

import com.takozy.binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4,6]
 * 输出: [1, 3, 4, 6]
 * 解释:
 *   1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *  /
 * 6               <---
 */
public class RightSideView {

    public static void main(String args[]) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2; n1.right = n3;
        TreeNode n4 = new TreeNode(4);
        n3.right = n4;
        TreeNode n5 = new TreeNode(5);
        n2.right = n5;
        TreeNode n6 = new TreeNode(6);
        n5.left = n6;

        RightSideView rightSideView = new RightSideView();
        List<Integer> lis = rightSideView.rightSideView(n1);
        System.out.println(lis);
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        LinkedList<TreeNode> q_single = new LinkedList<>();
        LinkedList<TreeNode> q_double = new LinkedList<>();
        q_single.add(root);
        while (q_double.size() > 0 || q_single.size() > 0) {
            while (q_single.size() > 0) {
                TreeNode node = q_single.poll();
                if (q_single.size() == 0) list.add(node.val);
                if (node.left != null) q_double.add(node.left);
                if (node.right != null) q_double.add(node.right);
            }
            while (q_double.size() > 0) {
                TreeNode poll = q_double.poll();
                if (q_double.size() == 0) list.add(poll.val);
                if (poll.left != null) q_single.add(poll.left);
                if (poll.right != null) q_single.add(poll.right);
            }
        }
        return list;
    }
}
