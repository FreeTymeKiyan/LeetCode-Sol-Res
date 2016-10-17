package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
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
     * Push all left children into a Stack.
     * Actually these are all roots of left subtrees.
     */
    public BinarySearchTreeIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        pushAll(root);
    }

    /**
     * If the stack is empty, there is no more node left.
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**
     * To get the next smallest value.
     * We pop the leftmost leaf from the stack.
     * Then we add all its right child's left children to stack.
     * Imagine all left children of a node is popped out.
     * The next will be itself.
     * And the next will be its right child's leftmost child.
     */
    public int next() {
        TreeNode n = stack.pop();
        pushAll(n.right); // Push all left subtrees of right child.
        return n.val;
    }

    void pushAll(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}
