package com.freetymekiyan.algorithms.other;

import com.freetymekiyan.algorithms.utils.Utils.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a graph, return all paths in the graph.
 * Each graph node is like a binary tree node that has at most 2 out links.
 * Note that the graph might have cycles and cycles should be avoided in the result paths.
 * <p>
 * Similar to LC 257 Binary Tree Paths.
 * Company Tags: Facebook
 * Tags: Graph, Depth-first Search
 */
public class GraphPaths {

    /**
     * DFS.
     * Must avoid cycles during traversal.
     * Add a set of visited node to achieve that.
     * If there is a cycle, add a path.
     * Or if there is no more node to traverse, add a path.
     */
    public List<String> graphPaths(TreeNode node) {
        List<String> paths = new ArrayList<>();
        dfs(node, new StringBuilder(), paths, new HashSet<>());
        return paths;
    }

    private void dfs(TreeNode node, StringBuilder path, List<String> paths, Set<TreeNode> visited) {
        if (node == null) return;
        if (node.left == null && node.right == null) { // When there's no more out links, path ends.
            paths.add(path.append(node.val).toString());
            return;
        }
        path.append(node.val).append("->");
        visited.add(node);
        int len = path.length();
        if (node.left != null && !visited.contains(node.left)) {
            dfs(node.left, path, paths, visited);
            path.setLength(len);
        }
        if (node.right != null && !visited.contains(node.right)) {
            if (node.left != null) visited.add(node.left); // Left node can have been visited.
            dfs(node.right, path, paths, visited);
            if (node.left != null) visited.remove(node.left); // Reset visited. Optional?
            path.setLength(len);
        }
        visited.remove(node);
    }
}