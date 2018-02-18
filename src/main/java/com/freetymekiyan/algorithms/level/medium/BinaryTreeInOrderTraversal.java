package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.*;

/**
 * 94. Binary Tree Inorder Traversal
 * <p>
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * For example:
 * Given binary tree [1,null,2,3],
 * |  1
 * |   \
 * |   2
 * |  /
 * | 3
 * return [1,3,2].
 * <p>
 * Note: Recursive solution is trivial, could you do it iteratively?
 * <p>
 * Related Topics: Hash Table, Stack, Tree
 * Similar Questions: (M) Validate Binary Search Tree, (M) Binary Tree Preorder Traversal, (H) Binary Tree Postorder
 * Traversal, (M) Binary Search Tree Iterator, (M) Kth Smallest Element in a BST, (H) Closest Binary Search Tree Value
 * II, (M) Inorder Successor in BST
 */
public class BinaryTreeInOrderTraversal {

    /**
     * Iterative.
     * Must move current node pointer correctly. How?
     * If cur is not null, push it to stack and move it to its left.
     * If cur is null, 2 cases:
     * 1. cur is a left child, pop from stack its parent to visit. Then visit its parent's right.
     * 2. cur is a right child, then the right subtree is done.
     * | So, pop from stack this subtree root's parent to visit. Then visit its parent's right.
     * The 2 cases can be combined to one.
     * When cur is null, point it to stack's pop result. Visit, then move to its right.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> values = new ArrayList<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                values.add(cur.val);
                cur = cur.right;
            }
        }
        return values;
    }
}