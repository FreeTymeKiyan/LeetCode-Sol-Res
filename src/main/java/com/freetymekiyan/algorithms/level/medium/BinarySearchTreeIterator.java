package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 173. Binary Search Tree Iterator
 * <p>
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a
 * BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 * <p>
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * <p>
 * Company Tags: LinkedIn, Google, Facebook, Microsoft
 * Tags: Tree, Stack, Design
 * Similar Problems: (M) Binary Tree Inorder Traversal, (M) Flatten 2D Vector, (M) Zigzag Iterator, (M) Peeking
 * Iterator, (M) Inorder Successor in BST
 */
public class BinarySearchTreeIterator {

    Deque<TreeNode> stack;

    /**
     * Simulate in-order traversal.
     * Push all left children into a Stack to get prepared.
     */
    public BinarySearchTreeIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        pushAllLeft(root);
    }

    /**
     * If the stack is empty, there is no more node left.
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * Imagine all left subtree of a node is popped out.
     * The next will be itself.
     * And then the next will be its right subtree.
     * The right subtree repeats the pattern of pushing all left children into a stack.
     */
    public int next() {
        TreeNode n = stack.pop();
        pushAllLeft(n.right); // Left subtree and root is done. Repeat on right subtree.
        return n.val;
    }

    private void pushAllLeft(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}