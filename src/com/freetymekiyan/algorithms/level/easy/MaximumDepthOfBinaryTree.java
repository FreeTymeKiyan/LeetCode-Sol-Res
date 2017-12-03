package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <p>
 * Company Tags: LinkedIn, Uber, Apple, Yahoo
 * Tags: Tree, Depth-first Search
 * Similar Problems: (E) Balanced Binary Tree (E) Minimum Depth of Binary Tree
 */
public class MaximumDepthOfBinaryTree {

    /**
     * Recursive. DFS. O(n) Time.
     * Base case:
     * If root is null, return 0.
     * Recurrence relation:
     * Depth of current node is the maximum of left depth and right depth + 1.
     */
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * Iterative. DFS. Stack.
     * One stack for DFS. Another for depth.
     * Push root onto stack to start.
     * While stack is not empty:
     * | Pop node from stack. Pop depth from the other stack.
     * | Update max value.
     * | Add left and right children to stack.
     * Return max depth.
     */
    private int maxDepthB(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<Integer> depths = new ArrayDeque<>();
        stack.push(root);
        depths.push(1);
        int maxDepth = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int d = depths.pop();
            maxDepth = Math.max(d, maxDepth);
            if (node.left != null) {
                stack.push(node.left);
                depths.push(d + 1);
            }
            if (node.right != null) {
                stack.push(node.right);
                depths.push(d + 1);
            }
        }
        return maxDepth;
    }

    /**
     * BFS. Level Order Traversal.
     * Increase depth for each level.
     */
    public int maxDepthC(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }
}