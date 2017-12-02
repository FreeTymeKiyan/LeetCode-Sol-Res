package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import com.freetymekiyan.algorithms.utils.Utils.TreeNode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * <p>
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How
 * would you optimize the kthSmallest routine?
 * <p>
 * Hint:
 * <p>
 * Try to utilize the property of a BST.
 * What if you could modify the BST node's structure?
 * The optimal runtime complexity is O(height of BST).
 * <p>
 * Tags: Binary Search Tree
 * Similar Problems: (M) Binary Tree Inorder Traversal
 * <p>
 * Answers:
 * If the BST is modified often, and I can modify the BST node's structure, I can add the left subtree node count to the
 * each node to know its ranking. Then use binary search to find the target node.
 */
public class KthSmallestElementInABst {

    private KthSmallestElementInABst k;

    private int res;
    private int count;

    /**
     * Recursive solution with in-order traversal helper.
     */
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        traverse(root);
        return res;
    }

    private void traverse(TreeNode node) {
        if (node.left != null) {
            traverse(node.left);
        }
        count--;
        if (count == 0) {
            res = node.val;
            return;
        }
        if (node.right != null) {
            traverse(node.right);
        }
    }

    /**
     * Iterasive solution with stack.
     */
    public int kthSmallestB(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        int count = k;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                count--;
                if (count == 0) {
                    return root.val;
                }
                root = root.right;
            }
        }
        return -1;
    }

    /**
     * Binary search for left subtree node count.
     * * For BST, the # of nodes of left subtree is actually the node's ranking.
     */
    public int kthSmallestC(TreeNode root, int k) {
        int count = countNodes(root.left);
        if (k <= count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k - 1 - count); // 1 is counted as current node
        }
        return root.val;
    }

    /**
     * Count how many nodes in this subtree rooted from n.
     * If we can modify the data structure, we can save the count with each node.
     */
    private int countNodes(TreeNode n) {
        if (n == null) {
            return 0;
        }
        return 1 + countNodes(n.left) + countNodes(n.right);
    }

    @Before
    public void setUp() {
        k = new KthSmallestElementInABst();
    }

    @Test
    public void testExamples() {
        TreeNode root = Utils.buildBinaryTree(new Integer[]{1});
        Assert.assertEquals(1, k.kthSmallest(root, 1));
        Assert.assertEquals(1, k.kthSmallestB(root, 1));
        Assert.assertEquals(1, k.kthSmallestC(root, 1));
        root = Utils.buildBinaryTree(new Integer[]{2, 1});
        Assert.assertEquals(1, k.kthSmallest(root, 1));
        Assert.assertEquals(1, k.kthSmallestB(root, 1));
        Assert.assertEquals(1, k.kthSmallestC(root, 1));
        root = Utils.buildBinaryTree(new Integer[]{1, -1, 2, null, null, null, 3});
        Assert.assertEquals(3, k.kthSmallest(root, 4));
        Assert.assertEquals(3, k.kthSmallestB(root, 4));
        Assert.assertEquals(3, k.kthSmallestC(root, 4));
    }

    @After
    public void tearDown() {
        k = null;
    }

}
