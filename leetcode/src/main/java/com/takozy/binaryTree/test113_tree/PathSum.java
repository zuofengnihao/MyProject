package com.takozy.binaryTree.test113_tree;

import com.takozy.binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例: 给定如下二叉树，以及目标和 sum = 22，
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 *
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class PathSum {

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 =new TreeNode(4);
        TreeNode t3 =new TreeNode(8);
        t1.left = t2;
        t1.right = t3;
        TreeNode t4 =new TreeNode(11);
        t2.left = t4;
        TreeNode t5 =new TreeNode(7);
        TreeNode t6 =new TreeNode(2);
        t4.left = t5;
        t4.right = t6;
        TreeNode t7 = new TreeNode(13);
        TreeNode t8 = new TreeNode(4);
        t3.left = t7;
        t3.right = t8;
        TreeNode t9 = new TreeNode(5);
        TreeNode t10 = new TreeNode(1);
        t8.left = t9;
        t8.right = t10;

        PathSum pathSum = new PathSum();
        List<List<Integer>> lists = pathSum.pathSum(t1, 22);
        System.out.println(lists);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        int count = 0;
        List<Integer> item = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        countSum(root, sum, count, item, result);
        return result;
    }

    /**
     *               5
     *              / \
     *             4   8
     *            /   / \
     *           11  13  4
     *          /  \    / \
     *         7    2  5   1
     */
    public void countSum(TreeNode node, int sum, int count, List<Integer> item, List<List<Integer>> result) {
        if (node == null) {
            if (count == sum) result.add(item);
            return;
        }
        if (count > sum) return;
        count += node.val;
        item.add(node.val);
        countSum(node.left, sum, count, item, result);
        countSum(node.right, sum, count, item, result);
    }
}
