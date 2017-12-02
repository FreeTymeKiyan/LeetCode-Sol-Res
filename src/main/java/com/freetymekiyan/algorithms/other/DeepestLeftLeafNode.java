package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * Given a Binary Tree, find the deepest leaf node that is left child of its
 * parent.
 * <p>
 * Tags: Tree, DFS, Backtracking
 */
class DeepestLeftLeafNode {
    public static void main(String[] args) {

    }

    /**
     *
     */
    public TreeNode deepestLeftLeaf(TreeNode root) {
        TreeNode res = null;
        deepestLeftLeaf(root, 0, 0, false, res);
        return res;
    }

    /**
     * Backtracking
     * If is left child, is leaf node, and level > maxLevel
     * Update result and maxLevel, then return
     */
    public void deepestLeftLeaf(TreeNode root, int level, int maxLevel, boolean isLeft, TreeNode res) {
        if (root == null) return;

        if (isLeft && root.left == null && root.right == null && level > maxLevel) {
            res = root;
            maxLevel = level;
            return;
        }

        deepestLeftLeaf(root.left, level + 1, maxLevel, true, res);
        deepestLeftLeaf(root.right, level + 1, maxLevel, false, res);
    }
}
