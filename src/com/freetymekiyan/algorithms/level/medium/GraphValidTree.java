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
     * Tree is a special graph: 1) All connected. 2) No cycle.
     * Union-find can be used to build connected component and check connection.
     */
    public boolean validTree(int n, int[][] edges) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }

        for (int i = 0; i < edges.length; i++) {
            int x = find(nums, edges[i][0]); // Find connected component id for one vertex
            int y = find(nums, edges[i][1]); // Find connected component id for the other
            // Two vertices are already connected
            if (x == y) {
                return false;
            }
            // Union
            nums[x] = y; // Add edges[i][0] to the connected component
        }

        // It requires n - 1 edges to connect n vertices.
        return edges.length == n - 1;
    }

    /**
     * Find connected component id.
     */
    private int find(int nums[], int i) {
        while (i != nums[i]) {
            // Dynamically balance the tree while finding
            nums[i] = nums[nums[i]];
            i = nums[i];
        }
        return i;
    }

}
