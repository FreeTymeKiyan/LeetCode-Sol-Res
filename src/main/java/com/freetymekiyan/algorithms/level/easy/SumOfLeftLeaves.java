package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 404. Sum of Left Leaves
 * <p>
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
 * Related Topics: Tree
 */
public class SumOfLeftLeaves {

    /**
     * DFS. Recursive.
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
        if (root.left != null && root.left.left == null && root.left.right == null) { // root.left is a leaf.
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
    public int sumOfLeftLeaves2(TreeNode root) {
        return dfs(root, false);
    }

    /**
     * DFS.
     * Base case:
     * 1) root is null, return 0.
     * 2) If current node is a leaf, and it's from left, return it's value.
     * The result is the sum of left leaves sum of left subtree and right subtree.
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

    /**
     * Pre-order traversal with a stack.
     * For each node, check if it has a left leaf.
     * If so, add the value to sum.
     */
    public int sumOfLeftLeaves3(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int sum = 0; // Avoid overflow with long? sum should be within integer range.
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                if (node.left.left == null && node.left.right == null) {
                    sum += node.left.val;
                }
                stack.push(node.left);
            }
        }
        return sum;
    }

    /**
     * Level-order traversal or BFS.
     */
    public int sumOfLeftLeaves4(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                if (node.left.left == null && node.left.right == null) {
                    sum += node.left.val;
                }
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return sum;
    }
}