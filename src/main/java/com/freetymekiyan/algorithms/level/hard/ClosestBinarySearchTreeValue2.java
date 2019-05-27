package com.freetymekiyan.algorithms.level.hard;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 272. Closest Binary Search Tree Value II
 * <p>
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 * <p>
 * Note:
 * <p>
 * Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 * Example:
 * <p>
 * Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
 * <p>
 * |     4
 * |    / \
 * |   2   5
 * |  / \
 * | 1   3
 * <p>
 * Output: [4,3]
 * Follow up:
 * Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
 * <p>
 * Companies: LinkedIn, Amazon, ForUsAll, Google
 * <p>
 * Related Topics: Stack, Tree
 * <p>
 * Similar Questions: (M) Binary Tree Inorder Traversal, (E) Closest Binary Search Tree Value
 */
public class ClosestBinarySearchTreeValue2 {
  public List<Integer> closestKValues(TreeNode root, double target, int k) {
    List<Integer> res = new ArrayList<>();

    Stack<Integer> s1 = new Stack<>(); // predecessors
    Stack<Integer> s2 = new Stack<>(); // successors

    inorder(root, target, false, s1);
    inorder(root, target, true, s2);

    while (k-- > 0) {
      if (s1.isEmpty())
        res.add(s2.pop());
      else if (s2.isEmpty())
        res.add(s1.pop());
      else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
        res.add(s1.pop());
      else
        res.add(s2.pop());
    }

    return res;
  }

  // inorder traversal
  private void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack) {
    if (root == null) return;

    inorder(reverse ? root.right : root.left, target, reverse, stack);
    // early terminate, no need to traverse the whole tree
    if ((reverse && root.val <= target) || (!reverse && root.val > target)) return;
    // track the value of current node
    stack.push(root.val);
    inorder(reverse ? root.left : root.right, target, reverse, stack);
  }
}