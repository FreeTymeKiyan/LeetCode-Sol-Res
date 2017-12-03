package com.freetymekiyan.algorithms.level.medium;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function
 * to check whether these edges make up a valid tree.
 * <p>
 * For example:
 * <p>
 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
 * <p>
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
 * <p>
 * Hint:
 * <p>
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
 * According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are
 * connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same
 * as [1, 0] and thus will not appear together in edges.
 * <p>
 * Company Tags: Google, Facebook, Zenefits
 * Tags: Depth-first Search, Breadth-first Search, Graph, Union Find
 * Similar Problems: (M) Course Schedule, (M) Number of Connected Components in an Undirected Graph
 */
public class GraphValidTree {

    /**
     * Union Find.
     * What is the difference between tree and graph?
     * Tree is a special graph: 1) All connected. 2) No cycle. Connected acyclic graph.
     * Union-find can be used to build connected component and check connection.
     * <p>
     * Implementation:
     * Quick check: It requires n-1 edges to connect n vertices. So if edges.length != n-1, return false.
     * Initialize an array of connected component ids.
     * For each edge in edges:
     * | Find the connected component ids for the two nodes.
     * | If the ids are the same, the two nodes are already connected, return false.
     * | Else, union the two nodes by set x's id to y.
     * After checking all edges, return true.
     */
    public boolean validTree(int n, int[][] edges) {
        // Quick check on the number of edges. It requires n - 1 edges to connect n vertices.
        if (edges.length != n - 1) {
            return false;
        }
        // Init cc id array.
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
        // Check cycle.
        for (int i = 0; i < edges.length; i++) {
            // Find connected component ids of the two nodes.
            int x = find(nums, edges[i][0]);
            int y = find(nums, edges[i][1]);
            if (x == y) { // If two vertices are already connected.
                return false;
            }
            // Union
            nums[x] = y; // Add edges[i][0] to the connected component.
        }
        return true;
    }

    /**
     * Recursive.
     * Find connected component id, or the root id.
     * Check whether child(current index) and parent(the value) are the same.
     * If they are, return the index.
     * If not, set index to the value and check again.
     * Stop till we find the root.
     */
    private int find(int nums[], int i) {
        while (i != nums[i]) {
            // Here if we found the child's id are not the same as the parent's.
            // We know the parent can be an intermediate id.
            // So we set parent's id to grand parent's id.
            // Which will dynamically balance the tree thus reducing O(n) to O(1).
            nums[i] = nums[nums[i]];
            i = nums[i];
        }
        return i;
    }

}
