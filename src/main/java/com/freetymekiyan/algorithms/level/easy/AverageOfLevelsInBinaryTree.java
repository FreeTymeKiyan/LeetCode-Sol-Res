package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 637. Average of Levels in Binary Tree
 * <p>
 * Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 * <p>
 * Example 1:
 * Input:
 * |   3
 * |  / \
 * | 9  20
 * |   /  \
 * |  15   7
 * Output: [3, 14.5, 11]
 * Explanation:
 * The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 * Note:
 * The range of node's value is in the range of 32-bit signed integer.
 * <p>
 * Related Topics: Tree
 * Similar Questions: (M) Binary Tree Level Order Traversal, (E) Binary Tree Level Order Traversal II
 */
public class AverageOfLevelsInBinaryTree {

    /**
     * BFS.
     * Traverse each level of the binary tree.
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> averages = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            averages.add(sum / size);
        }
        return averages;
    }

    /**
     * DFS.
     */
    public List<Double> averageOfLevels2(TreeNode root) {
        List<List<TreeNode>> nodes = new ArrayList<>();
        helper(root, 0, nodes);
        List<Double> averages = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            double sum = 0;
            List<TreeNode> level = nodes.get(i);
            for (TreeNode node : level) {
                sum += node.val;
            }
            averages.add(sum / level.size());
        }
        return averages;
    }

    private void helper(TreeNode node, int level, List<List<TreeNode>> nodes) {
        if (node == null) {
            return;
        }
        if (level >= nodes.size()) {
            nodes.add(new ArrayList<>());
        }
        nodes.get(level).add(node);
        helper(node.left, level + 1, nodes);
        helper(node.right, level + 1, nodes);
    }
}