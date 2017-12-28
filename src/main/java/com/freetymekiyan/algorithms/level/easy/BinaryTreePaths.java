package com.freetymekiyan.algorithms.level.easy;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 257. Binary Tree Paths
 * <p>
 * Given a binary tree, return all root-to-leaf paths.
 * <p>
 * For example, given the following binary tree:
 * <p>
 * |    1
 * |  /   \
 * | 2     3
 * |  \
 * |   5
 * All root-to-leaf paths are:
 * <p>
 * ["1->2->5", "1->3"]
 * <p>
 * Company Tags: Google, Apple, Facebook
 * Tags: Tree, Depth-first Search
 * Similar Problems: (M) Path Sum II
 */
public class BinaryTreePaths {

    /**
     * DFS.
     * The path from root to leaf can be obtained by backtracking.
     * While backtrack we maintain a current path, and a list of result paths.
     * To traverse the tree, we maintain a current tree's root node.
     * At each node:
     * | If it is a leaf, append the value, then add the path to results.
     * | If not, append the value and an arrow, then backtrack left subtree, reset, backtrack right subtree, reset.
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        backtrack(root, new StringBuilder(), paths);
        return paths;
    }

    private void backtrack(TreeNode root, StringBuilder path, List<String> paths) {
        if (root == null) return;
        if (root.left == null && root.right == null) { // A leaf.
            paths.add(path.append(root.val).toString());
            return;
        }
        path.append(root.val).append("->"); // Arrow should be appended before reaching leaf.
        int len = path.length();
        backtrack(root.left, path, paths);
        path.setLength(len); // Reset path.
        backtrack(root.right, path, paths);
        path.setLength(len);
    }

    /**
     * DFS.
     * Recurrence relation:
     * The paths consist of root + all subtrees paths.
     * Base case:
     * If root == null, the resukt list would be empty.
     * If root is a leaf node, then its value would be in list.
     * <p>
     * Just concatenate root's value with those paths returned from subtrees.
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<String> paths = new ArrayList<>();
        if (root.left == null && root.right == null) {
            paths.add(Integer.toString(root.val));
            return paths;
        }
        // Paths from left subtree.
        for (String path : binaryTreePaths2(root.left)) {
            paths.add(root.val + "->" + path); // Concat root with each path.
        }
        // Paths from right subtree.
        for (String path : binaryTreePaths2(root.right)) {
            paths.add(root.val + "->" + path);
        }
        return paths;
    }
}