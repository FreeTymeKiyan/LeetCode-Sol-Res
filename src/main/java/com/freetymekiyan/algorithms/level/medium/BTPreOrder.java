package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * <p>
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 * \
 * 2
 * /
 * 3
 * return [1,2,3].
 * <p>
 * Note: Recursive solution is trivial, could you do it iteratively?
 * <p>
 * Tags: Tree, Stack
 */
class BTPreOrder {

    public static void main(String[] args) {

    }

    /**
     * Iterative
     * Use a stack
     * Pop current top, and push right first then push left
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode curNode = s.pop();
            res.add(curNode.val); // visit
            if (curNode.right != null) s.push(curNode.right);
            if (curNode.left != null) s.push(curNode.left); // left pop first
        }
        return res;
    }

    /**
     * Recursive
     */
    public List<Integer> preorderTraversalB(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
//        visit();
        preorderTraversalB(root, res);
        return res;
    }

    public void preorderTraversalB(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preorderTraversalB(root.left, res);
        preorderTraversalB(root.right, res);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
