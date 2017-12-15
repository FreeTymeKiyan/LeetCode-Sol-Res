package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.*;

/**
 * 742. Closest Leaf in a Binary Tree
 * <p>
 * Given a binary tree where every node has a unique value, and a target key k, find the value of the closest leaf node
 * to target k in the tree.
 * <p>
 * Here, closest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree.
 * Also, a node is called a leaf if it has no children.
 * <p>
 * In the following examples, the input tree is represented in flattened form row by row. The actual root tree given
 * will be a TreeNode object.
 * <p>
 * Example 1:
 * <p>
 * Input:
 * root = [1, 3, 2], k = 1
 * Diagram of binary tree:
 * |   1
 * |  / \
 * | 3   2
 * <p>
 * Output: 2 (or 3)
 * <p>
 * Explanation: Either 2 or 3 is the closest leaf node to the target of 1.
 * Example 2:
 * <p>
 * Input:
 * root = [1], k = 1
 * Output: 1
 * <p>
 * Explanation: The closest leaf node is the root node itself.
 * Example 3:
 * <p>
 * Input:
 * root = [1,2,3,4,null,null,null,5,null,6], k = 2
 * Diagram of binary tree:
 * |         1
 * |        / \
 * |       2   3
 * |      /
 * |     4
 * |    /
 * |   5
 * |  /
 * | 6
 * <p>
 * Output: 3
 * Explanation: The leaf node with value 3 (and not the leaf node with value 6) is closest to the node with value 2.
 * Note:
 * root represents a binary tree with at least 1 node and at most 1000 nodes.
 * Every node has a unique node.val in range [1, 1000].
 * There exists some node in the given binary tree for which node.val == k.
 * <p>
 * Related Topics: Tree
 */
public class ClosestLeafInABinaryTree {

    /**
     * DFS, BFS.
     * Intuition:
     * The closest leaf of a node is actually the minimum distance from the node to the boundary of the tree.
     * If we treat tree generally as a graph, we can BFS and return when the boundary is hit.
     * One difficulty is tree links are directed, we cannot go backward from a node to its parent.
     * From target node, we can reach any node in its subtree.
     * Adding a map to store the edges backwards from target to root, we can then traverse every parts.
     */
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> backEdges = new HashMap<>();
        // DFS to find the node and generate back edges for later traversal.
        TreeNode target = dfs(root, k, backEdges);
        // BFS to find a leaf.
        Queue<TreeNode> queue = new ArrayDeque<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null && node.right == null) { // Leaf node.
                return node.val;
            }
            // Neighbors are left node, right node, and parent node.
            if (node.left != null && visited.add(node.left)) { // set.add returns true if value doesn't exist yet.
                queue.offer(node.left);
            }
            if (node.right != null && visited.add(node.right)) {
                queue.offer(node.right);
            }
            if (backEdges.containsKey(node) && visited.add(backEdges.get(node))) {
                queue.offer(backEdges.get(node));
            }
        }
        return -1;
    }

    /**
     * Generate edges backward from target to root.
     */
    private TreeNode dfs(TreeNode root, int k, Map<TreeNode, TreeNode> backEdges) {
        if (root == null || root.val == k) return root;
        if (root.left != null) {
            backEdges.put(root.left, root);
            TreeNode left = dfs(root.left, k, backEdges);
            if (left != null) return left;
        }
        if (root.right != null) {
            backEdges.put(root.right, root);
            TreeNode right = dfs(root.right, k, backEdges);
            if (right != null) return right;
        }
        return null;
    }
}