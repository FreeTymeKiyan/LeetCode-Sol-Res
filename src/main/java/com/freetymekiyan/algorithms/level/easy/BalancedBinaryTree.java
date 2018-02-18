package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * 110. Balanced Binary Tree
 * <p>
 * Given a binary tree, determine if it is height-balanced.
 * <p>
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * <p>
 * Tags: Tree, DFS
 */
class BalancedBinaryTree {

    private boolean isBalanced(TreeNode root) {
        return maxDepth(root) != -1;
    }

    /**
     * Recursive.
     * By definition we should compare the height of each node.
     * Tree's height range from -1 to h. -1 is null, 0 is root's height.
     * So instead of height, use depth since it starts from 0, which is null.
     * Then -1 is used to indicate an imbalance tree.
     * Recurrence relation:
     * Tree's depth = max depth of left and right subtree + 1
     * OR
     * Tree's depth is -1 if subtree is imbalance or itself is imbalance.
     * That is left height = -1 OR right height = -1 OR |left - right| > 1.
     * Base case:
     * If null, the depth is 0.
     */
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }
}