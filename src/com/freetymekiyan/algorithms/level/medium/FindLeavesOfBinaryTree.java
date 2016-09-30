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
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * Returns [4, 5, 3], [2], [1].
 * <p>
 * Explanation:
 * 1. Removing the leaves [4, 5, 3] would result in this tree:
 * <p>
 * 1
 * /
 * 2
 * 2. Now removing the leaf [2] would result in this tree:
 * <p>
 * 1
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
     * Make use of one property of tree node, its height.
     * Height is the number of edges from the node to the deepest leaf.
     * So leaf node will have height 0.
     * This problem is just aggregating all nodes with same height into a list.
     */
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }

    /**
     * Return the height of a node.
     * height(node) = 1 + max(height(node.left), height(node.right))
     */
    private int height(TreeNode node, List<List<Integer>> res) {
        if (null == node) return -1;
        int level = 1 + Math.max(height(node.left, res), height(node.right, res));
        if (res.size() == level) { // Current level exceeds result list size
            res.add(new ArrayList<>());
        }
        res.get(level).add(node.val);
        // root.left = root.right = null;
        return level;
    }
}
