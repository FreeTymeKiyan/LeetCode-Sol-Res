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
     * Recursive. DFS.
     * Get the diameter of each node then return the maximum.
     * Diameter = depth(left subtree) + depth(right subtree).
     * Maintain an 1-element array to hold the reference of maximum diameter.
     */
    public int diameterOfBinaryTree2(TreeNode root) {
        int[] diameter = new int[1]; // A bit of hack. Pass int array hold reference.
        dfs(root, diameter);
        return diameter[0];
    }

    /**
     * Post-order traversal.
     * Modifies get depth of tree. root's depth is 1, but root's height is 0.
     * The diameter of a node is left depth + right depth.
     * So just get the depths of left and right subtrees.
     * Then add them to see if the diameter is larger.
     */
    private int dfs(TreeNode root, int[] d) {
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left, d);
        int r = dfs(root.right, d);
        if (l + r > d[0]) d[0] = l + r;
        return (l > r ? l : r) + 1;
    }

    /**
     * Recursive.
     * Recurrence relation:
     * Diameter is the # of edges of the longest path between 2 nodes.
     * If two nodes are in the same tree, the diameter is the larger the the two subtrees' diameters.
     * If not, the diameter is the depth(left) + depth(right).
     * Has some duplicate calculations.
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int crossRoot = getDepth(root.left) + getDepth(root.right);
        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);
        return Math.max(Math.max(crossRoot, leftDiameter), rightDiameter);
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
    }
}