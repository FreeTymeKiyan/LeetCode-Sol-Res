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
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * <p>
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as
 * the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 * <p>
 *        _______3______
 *       /              \
 *   ___5__          ___1__
 *  /      \        /      \
 * 6      _2       0       8
 *       /  \
 *      7   4
 * For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5,
 * since a node can be a descendant of itself according to the LCA definition.
 * <p>
 * Tags: Tree
 * Similar Problems: (E) Lowest Common Ancestor of a Binary Search Tree
 */
public class LowestCommonAncestorOfABinaryTree {


    private LowestCommonAncestorOfABinaryTree l;

    /**
     * Recursive.
     * Base case: if root is null, return null; if root is p or q, return root.
     * Recurrence relation: try to find LCA in left and right subtree.
     * If both are found, it means the two nodes are in different subtrees, root should be their LCA.
     * If one of them is null, it means not possible LCA found for p or q.
     * Then the one that is not null should be their LCA.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    /**
     * Iterative. Two stacks.
     * First, DFS for p or q.
     * If we found anyone of them, copy current stack to a new stack, which keeps all its ancestors.
     * Then we try to find the other one.
     * Every time when we pop from the stack for DFS, we check whether the node is the found node's ancestor.
     * If it is, pop the top node from ancestor and update lca to it.
     * If it's not, do nothing.
     * When we find the other node, return lca.
     */
    public TreeNode lowestCommonAncestorB(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        Deque<TreeNode> ancestors = null;
        TreeNode lca = null;
        TreeNode next = null;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                // Visit
                if (lca == null) {
                    if (root == p || root == q) {
                        ancestors = new ArrayDeque<>(stack);
                        lca = root;
                        next = lca == p ? q : p;
                    }
                } else {
                    // Update lca when root is lca's ancestor
                    if (!ancestors.isEmpty() && ancestors.peek() == root) {
                        lca = ancestors.pop();
                    }
                    if (root == next) {
                        break;
                    }
                }
                root = root.right;
            }
        }
        return lca;
    }

    @Before
    public void setUp() {
        l = new LowestCommonAncestorOfABinaryTree();
    }

    /**
     * Iterative solution cannot pass this test the binary tree is built in Utils.
     * There is no good way to get reference to the nodes inside.
     * Only comparing the value is not enough.
     */
    @Test
    public void testExamples() {
        TreeNode root = Utils.buildBinaryTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);
        TreeNode res = l.lowestCommonAncestor(root, p, q);
        Assert.assertNotNull(res);
        Assert.assertEquals(3, res.val);
        root =
            Utils.buildBinaryTree(
                new Integer[]{37, -34, -48, null, -100, -100, 48, null, null, null, null, -54, null, -71, -22, null,
                              null, null, 8});
        p = new TreeNode(-100);
        q = new TreeNode(-71);
        res = l.lowestCommonAncestor(root, p, q);
        Assert.assertNotNull(res);
        Assert.assertEquals(-48, res.val);
    }

    @After
    public void tearDown() {
        l = null;
    }

}
