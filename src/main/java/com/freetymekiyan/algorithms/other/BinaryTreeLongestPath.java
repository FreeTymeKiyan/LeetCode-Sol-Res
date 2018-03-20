package com.freetymekiyan.algorithms.other;


import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * Given a binary tree, find the longest path from root to a leaf.
 * The path can be arrow concatenated string like 1->2->3.
 */
public class BinaryTreeLongestPath {

    /**
     * Backtracking.
     * Maintain a max length, a current length and current values.
     * When at a leaf node, compare with existing max to see if the path is longest.
     */
    public String longestPath(TreeNode root) {
        String[] res = new String[1];
        dfs(root, new StringBuilder(), new int[1], 0, res);
        return res[0];
    }

    private void dfs(TreeNode root, StringBuilder sb, int[] max, int current, String[] res) {
        if (root == null) {
            if (current > max[0]) {
                max[0] = current;
                res[0] = sb.delete(sb.length() - 2, sb.length()).toString();
            }
            return;
        }
        sb.append(root.val).append("->");
        int len = sb.length();
        dfs(root.left, sb, max, current + 1, res);
        sb.setLength(len);
        dfs(root.right, sb, max, current + 1, res);
        sb.setLength(len);
    }
}