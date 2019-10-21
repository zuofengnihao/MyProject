package com.takozy.binaryTree.test236_tree;

import com.takozy.binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestor {

    public static void main(String[] args) {
        TreeNode t0 = new TreeNode(3);
        TreeNode t1 =new TreeNode(5);
        TreeNode t2 =new TreeNode(1);
        TreeNode t3 =new TreeNode(6);
        TreeNode t4 =new TreeNode(2);
        TreeNode t5 =new TreeNode(0);
        TreeNode t6 =new TreeNode(8);
        TreeNode t7 =new TreeNode(7);
        TreeNode t8 =new TreeNode(4);
        t0.left = t1; t0.right = t2;
        t1.left = t3; t1.right= t4;
        t4.left = t7;t4.right = t8;
        t2.left = t5; t2.right = t6;

        LowestCommonAncestor ancestor = new LowestCommonAncestor();
        TreeNode node = ancestor.lowestCommonAncestor(t0, t1, t8);
        System.out.println(node);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p,TreeNode q) {
        List<TreeNode> p_p = new ArrayList();
        List<TreeNode> p_q = new ArrayList();
        getTargetPath(root, p, q, new ArrayList<>(), p_p, p_q);
        for (int i = p_p.size() - 1; i >= 0; i--) {
            for (int j = p_q.size() - 1; j >= 0; j--) {
                if (p_p.get(i) == p_q.get(j)) return p_p.get(i);
            }
        }
        return null;
    }

    public void getTargetPath(TreeNode node, TreeNode p, TreeNode q, List<TreeNode> current, List<TreeNode> path_p, List<TreeNode> path_q) {
        if (node == null || (path_p.size() > 0 && path_q.size() > 0)) return;
        current.add(node);
        if (node == p) {
            path_p.addAll(current);
        } else if (node == q) {
            path_q.addAll(current);
        }
        getTargetPath(node.left, p, q, current, path_p, path_q);
        getTargetPath(node.right, p, q, current, path_p, path_q);
        current.remove(current.size()-1);
    }

}
