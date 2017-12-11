package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * 543. Diameter of Binary Tree
 * <p>
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the
 * length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 * <p>
 * Example:
 * Given a binary tree
 * |     1
 * |    / \
 * |   2   3
 * |  / \
 * | 4   5
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * <p>
 * Note: The length of path between two nodes is represented by the number of edges between them.
 * <p>
 * Related Topics: Tree
 */
public class DiameterOfBinaryTree {

    /**
     * Recurrence relation:
     * Diameter of the current tree can be:
     * 1. Cross root, height(left) + height(right)
     * 2. Not cross root, the maximum of diameters of left and right subtrees.
     * Has some duplicate calculations.
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int crossRoot = height(root.left) + height(root.right);
        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);
        return Math.max(Math.max(crossRoot, leftDiameter), rightDiameter);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.left), height(root.right));
    }

    /**
     * Get the diameter of each node then return the maximum.
     * Diameter of each node is left + right.
     */
    public int diameterOfBinaryTree2(TreeNode root) {
        int[] result = new int[1]; // A bit of hack. Pass int array by reference to avoid field variable.
        height(root, result);
        return result[0];
    }

    private int height(TreeNode root, int[] maxD) {
        if (root == null) {
            return 0;
        }
        int l = height(root.left, maxD);
        int r = height(root.right, maxD);
        if (l + r > maxD[0]) maxD[0] = l + r;
        return (l > r ? l : r) + 1;
    }
}
