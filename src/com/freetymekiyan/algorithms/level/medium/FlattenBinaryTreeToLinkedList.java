package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * <p>
 * For example,
 * Given
 * <p>
 * |     1
 * |    / \
 * |   2   5
 * |  / \   \
 * | 3   4   6
 * The flattened tree should look like:
 * | 1
 * |  \
 * |   2
 * |    \
 * |     3
 * |      \
 * |       4
 * |        \
 * |         5
 * |          \
 * |           6
 * Hints:
 * If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order
 * traversal.
 * <p>
 * Company Tags: Microsoft
 * Tags: Tree, Depth-first Search
 */
public class FlattenBinaryTreeToLinkedList {

    /**
     * Iteration.
     * For current node, if left subtree is not null:
     * | Find the right most node of left subtree.
     * | Connect it with root.right.
     * | Set root.right to root.left.
     * Move root to root.right, which was root.left.
     * Repeat until root is null.
     */
    public void flatten(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                TreeNode n = root.left;
                while (n.right != null) {
                    n = n.right;
                }
                n.right = root.right;
                root.right = root.left;
                root.left = null;
            }
            root = root.right;
        }
    }

    /**
     * Recursion.
     * Suppose flatten already works.
     * The result is flatten the left subtree, then the right subtree.
     * Then set root.right to left subtree and connect the tail with right subtree.
     * Remember to set root.left to null.
     */
    public void flattenB(TreeNode root) {
        if (root == null) {
            return;
        }
        flattenB(root.left);
        flattenB(root.right);
        TreeNode n = root.right;
        root.right = root.left;
        root.left = null; // IMPORTANT! Set left child to null.
        TreeNode cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.right = n;
    }
}
