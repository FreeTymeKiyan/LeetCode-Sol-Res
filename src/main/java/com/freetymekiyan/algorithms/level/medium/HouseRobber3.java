package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the
 * "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that
 * "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked
 * houses were broken into on the same night.
 * <p>
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 * 3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
 * <p>
 * Tags: Tree, Depth-first Search
 * Similar Problems: (E) House Robber, (M) House Robber II
 */
public class HouseRobber3 {

    private HouseRobber3 h;

    /**
     * If current house is robbed, then subtrees' roots cannot be robbed.
     * The maximum is
     * root.val + rob(root.left.left) + rob(root.left.right) + rob(root.right.left) + rob(root.right.right)
     * If current house is robbed, then subtrees' roots can be robbed.
     * The maximum is
     * rob(root.left) + rob(root.right)
     * The base case is where we don't need to calculate sub problems.
     * When root is null in our case. Just return 0.
     */
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    /**
     * @return An array of two integers, the first one is maximum with current node; the second is without current node.
     */
    private int[] dfs(TreeNode node) {
        if (node == null) return new int[2]; // Base case
        int[] res = new int[2];
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        res[0] = node.val + left[1] + right[1];
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
    }

    @Before
    public void setUp() {
        h = new HouseRobber3();
    }

    @Test
    public void testExamples() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        Assert.assertEquals(7, h.rob(root));

        root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        Assert.assertEquals(9, h.rob(root));

        root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        Assert.assertEquals(7, h.rob(root));
    }

    @After
    public void tearDown() {
        h = null;
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
