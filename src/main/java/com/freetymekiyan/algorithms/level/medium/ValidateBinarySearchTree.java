package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * <p>
 * Assume a BST is defined as follows:
 * <p>
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Example 1:
 * |   2
 * |  / \
 * | 1   3
 * Binary tree [2,1,3], return true.
 * <p>
 * Example 2:
 * |   1
 * |  / \
 * | 2   3
 * Binary tree [1,2,3], return false.
 * Company Tags: Amazon, Microsoft, Bloomberg, Facebook
 * Tags: Tree, Depth-first Search
 * Similar Problems: (M) Binary Tree Inorder Traversal
 */
public class ValidateBinarySearchTree {

    TreeNode pred = null;
    private ValidateBinarySearchTree v;

    /**
     * Recursive, in-order traversal.
     * Base case:
     * Check current root, if its null, return true.
     * Recurrence relation:
     * root must be larger than the largest in left subtree,
     * smaller than the smallest in right subtree.
     * Left subtree and right subtree mush also be BST.
     * Implementation:
     * Check left subtree, if its not a BST, return false.
     * Use a pointer to remember the largest node in the left subtree.
     * Compare with current node and update pointer.
     * Check right subtree, if its not a BST, return false.
     * Return true if all tests passed.
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (pred != null && pred.val >= root.val) {
            return false;
        }
        pred = root;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }

    /**
     * Recursive.
     * Build a helper function with range.
     * Check whether root's val is in range.
     * Then check left and right recursively.
     * Will fail if input include Integer MAX and Integer MIN.
     */
    public boolean isValidBSTB(TreeNode root) {
        return isValidBSTB(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // add range of current value and do recursive check
    private boolean isValidBSTB(TreeNode root, int min, int max) {
        return root == null || root.val > min && root.val < max && isValidBSTB(root.left, min, root.val) && isValidBSTB(
            root.right, root.val, max);
    }

    /**
     * Recursive, in-order.
     * Inorder traversal, generate a list.
     * The list should be in increasing order.
     */
    public boolean isValidBSTC(TreeNode root) {
        if (root == null) {
            return true;
        }
        List<Integer> result = new ArrayList<Integer>();
        inOrderList(root, result);
        for (int i = 0; i < result.size() - 1; i++) {
            if (result.get(i) >= result.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private void inOrderList(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inOrderList(root.left, res);
        res.add(root.val);
        inOrderList(root.right, res);
    }

    /**
     * Recursive, pre-order.
     * Check if root.val is bigger than value of rightmost node in left subtree.
     * And smaller than value of leftmost node in right subtree.
     */
    public boolean isValidBSTD(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode temp = null;
        if (root.left != null) {
            temp = root.left;
            while (temp.right != null) {
                temp = temp.right;
            }
            if (temp.val >= root.val) {
                return false;
            }
        }
        if (root.right != null) {
            temp = root.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            if (temp.val <= root.val) {
                return false;
            }
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    @Before
    public void setUp() {
        v = new ValidateBinarySearchTree();
    }

    @Test
    public void testExamples() {
        TreeNode r = new TreeNode(Integer.MAX_VALUE);
        Assert.assertTrue(v.isValidBST(r));
//        Assert.assertTrue(v.isValidBSTB(r));
        Assert.assertTrue(v.isValidBSTC(r));
        Assert.assertTrue(v.isValidBSTD(r));
    }

    @After
    public void tearDown() {
        v = null;
    }
}
