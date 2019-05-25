package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 145. Binary Tree Postorder Traversal
 * <p>
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * <p>
 * Example:
 * <p>
 * Input: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 * <p>
 * Related Topics: Stack, Tree
 * <p>
 * Similar Questions: Binary Tree Inorder Traversal (M), N-ary Tree Postorder Traversal (E)
 */
public class BinaryTreePostorderTraversal {

  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if (root == null) return res;
    Stack<TreeNode> s = new Stack<>();
    s.push(root);
    TreeNode prev = null;
    TreeNode cur = root;
    while (!s.isEmpty()) {
      cur = s.peek();
      if (prev == null || prev.left == cur || prev.right == cur) {
        if (cur.left != null) s.push(cur.left);
        else if (cur.right != null) s.push(cur.right);
      } else if (cur.left == prev) {
        if (cur.right != null) s.push(cur.right);
      } else {
        res.add(cur.val);
        s.pop();
      }
      prev = cur;
    }
    return res;
  }
}