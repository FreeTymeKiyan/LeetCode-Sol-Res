package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * <p>
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * <p>
 * |     1
 * |    / \
 * |   2   2
 * |  / \ / \
 * | 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 * |   1
 * |  / \
 * | 2   2
 * |  \   \
 * |  3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 * <p>
 * Company Tags: LinkedIn, Bloomberg, Microsoft
 * Tags: Tree, Depth-first Search, Breadth-first Search
 */
public class SymmetricTree {

    /**
     * Iterative. Stack. DFS.
     * Skip root since it's definitely symmetric.
     * Push root's children onto a stack if they both exists.
     * While stack is not empty:
     * | Pop two nodes n1 and n2 from stack to compare.
     * | If they are both null, continue.
     * | If only one of them is null, or they are not the same value:
     * |   Not symmetric, return false.
     * | Push their children onto stack for the next iteration:
     * |   In a symmetric way, n1.left and n2.right, then n1.right and n2.left.
     */
    private boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return root.left == root.right;
        }
        Deque<TreeNode> s = new ArrayDeque<>();
        s.push(root.left);
        s.push(root.right);
        while (!s.isEmpty()) {
            TreeNode n1 = s.pop();
            TreeNode n2 = s.pop();
            if (n1.val != n2.val) {
                return false;
            }
            if (n1.left != null && n2.right != null) { // Both are not null.
                s.push(n1.left);
                s.push(n2.right);
            } else if (n1.left != null || n2.right != null) { // Only one of them is null.
                return false;
            }
            if (n1.right != null && n2.left != null) {
                s.push(n1.right);
                s.push(n2.left);
            } else if (n1.right != null || n2.left != null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Recursive. DFS.
     * Depth first search both subtrees, n1 and n2.
     * If at least one node is null:
     * | Return true if they are both null, otherwise false.
     * Then check both current nodes values.
     * Check n1.left and n2.right.
     * Check n1.right and n2.left.
     */
    private boolean isSymmetricRec(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode n1, TreeNode n2) {
        if (n1 == null || n2 == null) {
            return n1 == n2;
        }
        return n1.val == n2.val && dfs(n1.left, n2.right) && dfs(n1.right, n2.left);
    }

}