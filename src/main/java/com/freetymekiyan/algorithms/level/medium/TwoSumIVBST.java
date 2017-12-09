package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * 653. Two Sum IV - Input is a BST
 * <p>
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their
 * sum is equal to the given target.
 * <p>
 * Example 1:
 * Input:
 * |     5
 * |    / \
 * |   3   6
 * |  / \   \
 * | 2   4   7
 * <p>
 * Target = 9
 * <p>
 * Output: True
 * Example 2:
 * Input:
 * |     5
 * |    / \
 * |   3   6
 * |  / \   \
 * | 2   4   7
 * <p>
 * Target = 28
 * <p>
 * Output: False
 * <p>
 * Related Topics: Tree
 * Similar Questions: (E) Two Sum, (E) Two Sum II - Input array is sorted, (E) Two Sum III - Data structure design
 */
public class TwoSumIVBST {

    /**
     * Set.
     * Traverse the tree and add traversed node values to a set.
     * At every new node, check if target - current node's value exists in the set.
     */
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> visited = new HashSet<>();
        return find(root, k, visited);
    }

    private boolean find(TreeNode root, int t, Set<Integer> visited) {
        if (root == null) return false;
        if (visited.contains(t - root.val)) return true;
        visited.add(root.val);
        return find(root.left, t, visited) || find(root.right, t, visited);
    }

    /**
     * BST, In-Order Traversal, Two Pointers.
     * In-order traversal generates values in ascending order.
     * Then use two pointers to check the target.
     */
    public boolean findTarget2(TreeNode root, int k) {
        List<Integer> values = new ArrayList<>();
        inOrderTraverse(root, values);
        int start = 0, end = values.size() - 1;
        while (start < end) {
            int sum = values.get(start) + values.get(end);
            if (sum == k) return true;
            if (sum > k) end--;
            else if (sum < k) start++;
        }
        return false;
    }

    private void inOrderTraverse(TreeNode root, List<Integer> values) {
        if (root == null) return;
        inOrderTraverse(root.left, values);
        values.add(root.val);
        inOrderTraverse(root.right, values);
    }
}
