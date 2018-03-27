package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * 572. Subtree of Another Tree
 * <p>
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a
 * subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could
 * also be considered as a subtree of itself.
 * <p>
 * Example 1:
 * Given tree s:
 * <p>
 * |     3
 * |    / \
 * |   4   5
 * |  / \
 * | 1   2
 * Given tree t:
 * |   4
 * |  / \
 * | 1   2
 * Return true, because t has the same structure and node values with a subtree of s.
 * Example 2:
 * Given tree s:
 * <p>
 * |     3
 * |    / \
 * |   4   5
 * |  / \
 * | 1   2
 * |    /
 * |   0
 * Given tree t:
 * |   4
 * |  / \
 * | 1   2
 * Return false.
 * <p>
 * Related Topics: Tree
 * Similar Questions: (M) Count Univalue Subtrees, (M) Most Frequent Subtree Sum
 */
public class SubtreeOfAnotherTree {

    /**
     * Recursive.
     * If s has a subtree like t, t can be:
     * 1. A subtree starting at s.
     * 2. A subtree in s's left subtree.
     * 3. A subtree in s's right subtree.
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return (s.val == t.val && dfs(s, t)) ||
                isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private boolean dfs(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        return s.val == t.val && dfs(s.left, t.left) && dfs(s.right, t.right);
    }
}
