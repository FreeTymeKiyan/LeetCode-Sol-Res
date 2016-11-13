package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * Given a root of BST and a node's value. Delete the node with that value from the tree.
 * Return new root.
 * <p>
 * Tags: Tree
 */
public class DeleteANodeFromBinarySearchTree {

    /**
     * Tree. BST. Recursive. The Hibbard deletion.
     * Two stages: Search for the node while tracking the parent.
     * If the node is found, delete.
     * Recursive delete will take the root and return new root.
     * So that the subtree can be connected to its parent.
     * Base case:
     * When root is null, return null.
     * Recurrence relation:
     * If key < root.val, key is in left subtree.
     * | Recurse on the left subtree and update root.left with the result.
     * If key > root.val, key is in right subtree.
     * | Recurse on the right subtree and update root.right with the result.
     * Else key == root.val, the node is found, start to delete.
     * | If root is a leaf, return null.
     * | If root has one child, return that child.
     * | That combines to:
     * |   If root.left == null, return root.right.
     * |   Else if root.right == null, return root.left.
     * | Now root has two children. Find the in-order successor or predecessor.
     * | First replace root's value with the minimum value of right subtree.
     * | Then delete the value from right subtree with recursive call.
     * Return root.
     * http://quiz.geeksforgeeks.org/binary-search-tree-set-2-delete/
     */
    public TreeNode delete(int key, TreeNode root) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = delete(key, root.left);
        } else if (key > root.val) {
            root.right = delete(key, root.right);
        } else { // Key is same as current root's val.
            // Node with only one child or no child.
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Node with two children: Get the inorder successor.
            root.val = getMin(root.right);
            // Delete the inorder successor.
            root.right = delete(root.val, root.right);
        }
        return root;
    }

    /**
     * Find the minimum value of BST.
     */
    private int getMin(TreeNode root) {
        int min = root.val;
        while (root.left != null) {
            min = root.left.val;
            root = root.left;
        }
        return min;
    }
}
