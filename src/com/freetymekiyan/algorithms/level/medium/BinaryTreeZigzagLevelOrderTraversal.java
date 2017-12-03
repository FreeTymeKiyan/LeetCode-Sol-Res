package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then
 * right to left for the next level and alternate between).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * |   3
 * |  / \
 * | 9  20
 * |   /  \
 * |  15   7
 * return its zigzag level order traversal as:
 * | [
 * |   [3],
 * |   [20,9],
 * |   [15,7]
 * | ]
 * Company Tags: LinkedIn, Bloomberg, Microsoft
 * Tags: Tree, Breadth-first Search, Stack
 * Similar Problems: (E) Binary Tree Level Order Traversal
 */
public class BinaryTreeZigzagLevelOrderTraversal {

    /**
     * BFS.
     * Use a boolean to indicate different level order.
     * Toggle it after a level is finished.
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean toggle = false;
        while (!q.isEmpty()) {
            List<Integer> curLevel = new LinkedList<>();
            for (int i = q.size(); i > 0; i--) {
                TreeNode n = q.poll();
                if (!toggle) {
                    curLevel.add(n.val);
                } else {
                    curLevel.add(0, n.val);
                }
                if (n.left != null) {
                    q.add(n.left);
                }
                if (n.right != null) {
                    q.add(n.right);
                }
            }
            toggle = !toggle;
            res.add(curLevel);
        }
        return res;
    }

    /**
     * DFS. Backtrack.
     * For current node, first check whether we have enough levels in result.
     * If not enough, add a new empty list.
     * Then add to the relative level.
     * | If at even level, append to the end.
     * | Else at odd level, insert to the front.
     * Recurse on the left and right subtrees with level + 1.
     */
    public List<List<Integer>> zigzagLevelOrderB(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, 0, root);
        return res;
    }

    private void dfs(List<List<Integer>> res, int level, TreeNode node) {
        if (node == null) {
            return;
        }
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }
        if (level % 2 == 0) {
            res.get(level).add(node.val);
        } else {
            res.get(level).add(0, node.val);
        }
        dfs(res, level + 1, node.left);
        dfs(res, level + 1, node.right);
    }
}
