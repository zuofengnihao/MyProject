package com.takozy.binaryTree.test236_tree;

import com.takozy.binaryTree.TreeNode;

import java.util.List;

public class LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p,TreeNode q) {

    }

    public void getTargetPath(TreeNode node, TreeNode p, TreeNode q, List<TreeNode> current, List<TreeNode> path_p, List<TreeNode> path_q) {
        if (node == null) return;
        
        getTargetPath(node.left, p, q, current, path_p, path_q);
        getTargetPath(node.right, p, q, current, path_p, path_q);

    }

}
