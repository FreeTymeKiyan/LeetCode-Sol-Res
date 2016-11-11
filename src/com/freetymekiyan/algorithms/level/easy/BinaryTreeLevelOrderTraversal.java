package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by
 * level).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * |   3
 * |  / \
 * | 9  20
 * |   /  \
 * |  15   7
 * return its level order traversal as:
 * | [
 * |   [3],
 * |   [9,20],
 * |   [15,7]
 * | ]
 * Company Tags: LinkedIn, Facebook, Amazon, Microsoft, Apple, Bloomberg
 * Tags: Tree, Breadth-first Search
 * Similar Problems: (M) Binary Tree Zigzag Level Order Traversal, (E) Binary Tree Level Order Traversal II, (E)
 * Minimum Depth of Binary Tree, (M) Binary Tree Vertical Order Traversal
 */
public class BinaryTreeLevelOrderTraversal {

    /**
     * BFS.
     * Instead of regular BFS, visit one level at each iteration.
     * By getting the size of the queue, we know how many nodes in current level.
     */
    private List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> curLevel = new LinkedList<>();

        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                TreeNode n = queue.poll();
                curLevel.add(n.val);
                if (n.left != null) {
                    queue.add(n.left);
                }
                if (n.right != null) {
                    queue.add(n.right);
                }
            }
            res.add(new ArrayList<>(curLevel));
            curLevel.clear();
        }

        return res;
    }

    /**
     * DFS.
     * Root is level 0, pass level + 1 to its children during DFS.
     * Add the node to its level list in the result.
     * Stop when reach null node.
     */
    public List<List<Integer>> levelOrderB(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    public void dfs(TreeNode root, int level, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        // Visit.
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        res.get(level).add(root.val);
        // Recurse to left and right child.
        dfs(root.left, level + 1, res);
        dfs(root.right, level + 1, res);
    }
}
