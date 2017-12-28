package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * <p>
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as
 * the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * |
 * |           _______6______
 * |          /              \
 * |      ___2__          ___8__
 * |     /      \        /      \
 * |    0      _4       7       9
 * |          /  \
 * |         3   5
 * |
 * For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6.
 * <p>
 * Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA
 * definition.
 * <p>
 * Company Tags: Amazon, Microsoft, Facebook, Twitter
 * Tags: Tree
 * Similar Problems: (M) Lowest Common Ancestor of a Binary Tree
 */
public class LowestCommonAncestorOfBST {

    /**
     * Iterative.
     * In BST, the lca's value can only be [p, q].
     * And lca is the first from top to bottom that lies in range.
     * If the value is less than both p and q's values, move to right subtree.
     * If the value is more than both p and q's values, move to left subtree.
     * If the value is in between the two nodes, return the node.
     * Otherwise there is no LCA.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null; // Reach null and lca not found.
    }

    /**
     * Recursion.
     * LCA's value must be between [p, q].
     * Recurrent relation:
     * If root's value < p's and q's, LCA is in the right subtree.
     * If root's value > p's and q's, LCA is in the left subtree.
     * Else, root's value in between, LCA is root.
     * Base case:
     * If root is null, return null.
     * Complete task:
     * Deal with base case. Search left, search right. Return result.
     */
    public TreeNode lowestCommonAncestorB(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestorB(root.right, p, q);
        } else if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestorB(root.left, p, q);
        }
        return root; // root's value in between p and q.
    }
}