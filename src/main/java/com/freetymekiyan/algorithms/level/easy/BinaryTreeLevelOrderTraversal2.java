package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107. Binary Tree Level Order Traversal II
 * <p>
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level
 * by level from leaf to root).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its bottom-up level order traversal as:
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 * <p>
 * Related Topics: Tree, Breadth-first Search
 * <p>
 * Similar Questions: Binary Tree Level Order Traversal (M), Average of Levels in Binary Tree (E)
 */
public class BinaryTreeLevelOrderTraversal2 {

  public List<List<Integer>> levelOrderBottom(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) return result;

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
      int size = q.size();
      List<Integer> curLevel = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        TreeNode n = q.poll();
        curLevel.add(n.val);
        if (n.left != null) q.add(n.left);
        if (n.right != null) q.add(n.right);
      }
      result.add(0, curLevel);
    }

    return result;
  }
}