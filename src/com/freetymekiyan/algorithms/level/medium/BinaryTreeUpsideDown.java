package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same
 * parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left
 * leaf nodes. Return the new root.
 * <p>
 * For example:
 * Given a binary tree {1,2,3,4,5},
 * |     1
 * |    / \
 * |   2   3
 * |  / \
 * | 4   5
 * return the root of the binary tree [4,5,2,#,#,3,1].
 * |   4
 * |  / \
 * | 5   2
 * |    / \
 * |   3   1
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * <p>
 * Company Tags: LinkedIn
 * Tags: Tree
 * Similar Problems: (E) Reverse Linked List
 */
public class BinaryTreeUpsideDown {

    /**
     * Recursive.
     * Observations:
     * Leftmost child becomes the new root, suppose it's parent is p.
     * p's right child becomes the new root's left.
     * p itself becomes the new root's right.
     * Then set p to a leaf.
     * Return new root.
     * <p>
     * Implementation:
     * Recurse down to the leftmost leaf, which is the new root.
     * From its parent p, we can set new root's left to p's right.
     * Then new root's right to p.
     * Finally disconnect p from both its children.
     * Move on to the previous parent.
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return newRoot;
    }

    /**
     * Iterative.
     * All right subtrees only have 1 node.
     * Move down along the left children.
     * While current node is not null:
     * | Store the next left child.
     * | Set current node's left to previous right.
     * | Update right to current right.
     * | Set current node's right to previous.
     * | Move on by updating prev to curr, curr to next.
     * Return prev.
     */
    public TreeNode upsideDownBinaryTreeB(TreeNode root) {
        TreeNode curr = root;
        TreeNode prev = null; // Previous root.
        TreeNode next = null; // Next node to flip.
        TreeNode right = null; // Previous right child.

        while (curr != null) {
            // Store next.
            next = curr.left;
            // Swap nodes.
            curr.left = right; // Current left is previous right.
            right = curr.right; // Update right.
            curr.right = prev; // Current right is previous root.
            // Move on.
            prev = curr;
            curr = next;
        }
        return prev;
    }

}
