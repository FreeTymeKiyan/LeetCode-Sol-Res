package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * <p>
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 * <p>
 * Company Tags: Microsoft, Google, Snapchat
 * Tags: Tree, Binary Search
 * Similar Problems: (M) Count Complete Tree Nodes, (H) Closest Binary Search Tree Value II
 */
public class ClosestBinarySearchTreeValue {

    /**
     * Binary Search. Recursive
     * Get root's value first, a.
     * If target < root's value, the next root will be left child.
     * Else it should be right child.
     * If the next root is null:
     * | Just return root's value.
     * Else get the closest value of next root, b.
     * Compare a and b, return the closer one.
     */
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode child = target < a ? root.left : root.right;
        if (child == null) {
            return a;
        }
        int b = closestValue(child, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }

    /**
     * Binary Search. Iterative.
     * Initialize answer as root's value.
     * While root is not null:
     * | If target is closer to current node's value then result:
     * |   Update result to current node's value.
     * | Otherwise, result remains the same.
     * | If target < current's value, move current node to left child.
     * | Else move current node to right child.
     * Return result.
     */
    public int closestValue2(TreeNode root, double target) {
        TreeNode cur = root;
        int res = root.val;
        while (cur != null) {
            if (Math.abs(target - cur.val) < Math.abs(target - res)) {
                res = cur.val;
            }
            cur = target < cur.val ? cur.left : cur.right;
        }
        return res;
    }

}
