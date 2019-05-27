package com.freetymekiyan.algorithms.level.medium;

import java.util.Stack;

/**
 * 255. Verify Preorder Sequence in Binary Search Tree
 * <p>
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 * <p>
 * You may assume each number in the sequence is unique.
 * <p>
 * Consider the following binary search tree:
 * <p>
 * 5
 * / \
 * 2   6
 * / \
 * 1   3
 * Example 1:
 * <p>
 * Input: [5,2,6,1,3]
 * Output: false
 * Example 2:
 * <p>
 * Input: [5,2,1,3,6]
 * Output: true
 * Follow up:
 * Could you do it using only constant space complexity?
 * <p>
 * Companies: Uber, Mathworks, Walmart Labs, Zenefits
 * <p>
 * Related Topics: Stack, Tree
 * <p>
 * Similar Questions: (M) Binary Tree Preorder Traversal
 */
public class VerifyPreorderSequenceInBinarySearchTree {

  public boolean verifyPreorder(int[] preorder) {
    int low = Integer.MIN_VALUE;
    Stack<Integer> path = new Stack<>();
    for (int p : preorder) {
      if (p < low)
        return false;
      while (!path.empty() && p > path.peek())
        low = path.pop();
      path.push(p);
    }
    return true;
  }

  public boolean verifyPreorder2(int[] preorder) {
    int low = Integer.MIN_VALUE, i = -1;
    for (int p : preorder) {
      if (p < low)
        return false;
      while (i >= 0 && p > preorder[i])
        low = preorder[i--];
      preorder[++i] = p;
    }
    return true;
  }
}