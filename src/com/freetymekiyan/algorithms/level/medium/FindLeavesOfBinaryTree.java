package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until
 * the tree is empty.
 * <p>
 * Example:
 * Given binary tree
 * |     1
 * |    / \
 * |   2   3
 * |  / \
 * | 4   5
 * Returns [4, 5, 3], [2], [1].
 * <p>
 * Explanation:
 * 1. Removing the leaves [4, 5, 3] would result in this tree:
 * <p>
 * |   1
 * |  /
 * | 2
 * 2. Now removing the leaf [2] would result in this tree:
 * <p>
 * | 1
 * 3. Now removing the leaf [1] would result in the empty tree:
 * <p>
 * []
 * Returns [4, 5, 3], [2], [1].
 * <p>
 * Company Tags: LinkedIn
 * Tags: Tree, Depth-first Search
 */
public class FindLeavesOfBinaryTree {

    /**
     * Tree, DFS(Backtracking).
     * Make use of one property of tree node, its getHeight.
     * Height is the number of edges from the node to the deepest leaf.
     * So leaf node will have getHeight 0.
     * This problem is just aggregating all nodes with same getHeight into a list.
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        getHeight(root, res);
        return res;
    }

    /**
     * Return the getHeight of a node.
     * Recurrence relation:
     * getHeight(node) = 1 + max(getHeight(node.left), getHeight(node.right))
     * Base case:
     * If node is null, it's getHeight is -1.
     */
    private int getHeight(TreeNode node, List<List<Integer>> res) {
        if (null == node) {
            return -1;
        }
        int height = 1 + Math.max(getHeight(node.left, res), getHeight(node.right, res));
        if (res.size() == height) { // Current height exceeds result list size.
            res.add(new ArrayList<>());
        }
        res.get(height).add(node.val);
        // root.left = root.right = null;
        return height;
    }
}
