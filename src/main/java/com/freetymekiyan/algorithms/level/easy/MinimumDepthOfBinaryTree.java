package com.freetymekiyan.algorithms.level.easy;

/**
 * 111. Minimum Depth of Binary Tree
 * <p>
 * Given a binary tree, find its minimum depth.
 * <p>
 * The minimum depth is the number of nodes along the shortest path from the
 * root node down to the nearest leaf node.
 * <p>
 * Tags: Tree, DFS
 */
class MinimumDepthOfBinaryTree {

  /**
   * Recursive
   * Get minDepth of left and right subtree
   * If one side is 0, return the other side plus 1
   * Return the smaller one + 1
   */
  public int minDepth(TreeNode root) {
    if (root == null) return 0;
    int left = minDepth(root.left);
    int right = minDepth(root.right);
    if (left == 0) return right + 1;
    if (right == 0) return left + 1;
    return Math.min(left, right) + 1; // plus root
  }

  public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }
}