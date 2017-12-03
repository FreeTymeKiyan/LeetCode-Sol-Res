package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * Find the sum of all left leaves in a given binary tree.
 * <p>
 * Example:
 * <p>
 * |    3
 * |   / \
 * |  9  20
 * |    /  \
 * |   15   7
 * <p>
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 * Company Tags: Facebook
 * Tags: Tree
 */
public class SumOfLeftLeaves {

    /**
     * DFS. Recursive.
     * Just change the visit of DFS.
     * Recurrence relation:
     * Current left leaf's value + sum of left subtree's leaves + sum to right subtree's leaves.
     * When visit, if current node's left child is not null, and it is a leaf.
     * Add its value to result.
     * Then call sum recursively on right subtree.
     * Base case:
     * If root is null, return 0.
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) { // root.left is a leave.
            sum += root.left.val; // Add directly to result.
        } else { // root.left is a subtree, recurse.
            sum += sumOfLeftLeaves(root.left);
        }
        sum += sumOfLeftLeaves(root.right);
        return sum;
    }

    /**
     * DFS. Recursive.
     * Pass another boolean to indication whether the call is from left.
     */
    public int sumOfLeftLeavesB(TreeNode root) {
        return dfs(root, false);
    }

    /**
     * DFS.
     * Two base cases:
     * 1) root is null, return 0.
     * 2) root not null, root is leaf. If from left, return value. Otherwise return 0.
     * The result is the left leaves sum of left subtree and right subtree.
     */
    private int dfs(TreeNode root, boolean isLeft) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return isLeft ? root.val : 0;
        }
        return dfs(root.left, true) + dfs(root.right, false);
    }

}
