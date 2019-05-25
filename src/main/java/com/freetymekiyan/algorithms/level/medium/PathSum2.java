package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. Path Sum II
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * Related Topics: Tree, Depth-first Search
 *
 * Similar Questions: Path Sum (E), Binary Tree Paths (E), Path Sum III (E), Path Sum IV (M)
 */
public class PathSum2 {

  public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    dfs(root, sum, new ArrayList<>(), res);
    return res;
  }

  public void dfs(TreeNode root, int sum, List<Integer> path, List<List<Integer>> res) {
    if (root == null) return;
    sum -= root.val;

    if (root.left == null && root.right == null && sum == 0) {
      path.add(root.val);
      res.add(new ArrayList<>(path));
      path.remove(path.size() - 1);
      return;
    }
    path.add(root.val);
    dfs(root.left, sum, path, res);
    dfs(root.right, sum, path, res);
    path.remove(path.size() - 1);
  }
}