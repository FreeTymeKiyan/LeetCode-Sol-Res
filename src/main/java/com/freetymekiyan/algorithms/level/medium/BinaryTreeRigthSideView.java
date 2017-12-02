package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see
 * ordered from top to bottom.
 * <p>
 * For example:
 * Given the following binary tree,
 * 1            <---
 * /   \
 * 2     3         <---
 * \     \
 * 5     4       <---
 * You should return [1, 3, 4].
 * <p>
 * Tags: Tree, Depth-first Search, Breadth-first Search
 * Similar: Problems (M) Populating Next Right Pointers in Each Node
 */
public class BinaryTreeRigthSideView {

    /**
     * BFS.
     * Do a level order traversal with queue.
     * For each level, traverse each node and add children to the queue.
     * If it's at the end of current level, add node's value to result.
     * <p>
     * Easy to understand, but queue.poll() and queue.offer() can be slow.
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }

    /**
     * DFS. Backtracking.
     * Base case: if current node is null, just return.
     * Visit: if depth equals result size, means that it's at the correct level, add current node's value to result.
     * Next: recurse down next level with right node first and depth + 1. Then left node.
     * <p>
     * Faster than BFS since there is no enqueue or dequeue.
     */
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root, 0);
        return res;
    }

    private void helper(List<Integer> res, TreeNode node, int depth) {
        if (node == null) {
            return;
        }
        if (depth == res.size()) {
            res.add(node.val);
        }
        helper(res, node.right, depth + 1); // Recurse right node first
        helper(res, node.left, depth + 1);
    }
}
