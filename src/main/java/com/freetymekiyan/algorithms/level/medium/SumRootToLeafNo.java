package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path
 * could represent a number.
 * <p>
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * <p>
 * Find the total sum of all root-to-leaf numbers.
 * <p>
 * For example,
 * 1
 * / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 * <p>
 * Tags: Tree, DFS
 */
class SumRootToLeafNo {
    public static void main(String[] args) {

    }

    public static int sumNumbers(TreeNode root) {
        int res = 0;
        if (root == null) return res;
        return helper(root, 0);
    }

    /**
     * Recursive, DFS
     * Build a helper function to pass cur result
     * If its leaf node, just return the val
     * Otherwise, goes to left root first then right root with current value
     */
    public static int helper(TreeNode root, int x) {
        if (root.right == null && root.left == null) return 10 * x + root.val;

        int val = 0;
        if (root.left != null) val += helper(root.left, 10 * x + root.val);
        if (root.right != null) val += helper(root.right, 10 * x + root.val);
        return val;
    }
}
