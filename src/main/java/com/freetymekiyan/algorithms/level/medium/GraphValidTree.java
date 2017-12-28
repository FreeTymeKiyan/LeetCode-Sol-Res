package com.freetymekiyan.algorithms.level.medium;

/**
 * 261. Graph Valid Tree
 * <p>
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
     * A quick check on the # of edges lock the graph to either:
     * 1) Connected without cycle. 2) Has cycle, not all connected.
     * So if the # of edges is n-1, we only need to verify that there is no cycle.
     * Union-find can be used to build connected component and check cycle.
     * <p>
     * Implementation:
     * Quick check if # of edges is n-1.
     * Initialize an array of n distinct connected component ids.
     * For each edge in edges:
     * | Find the connected component ids for the two nodes.
     * | If the ids are the same, the two nodes are already connected, cycle found, return false.
     * | Else, union the two nodes by set x's id to y.
     * After checking all edges and finding no cycle, return true.
     */
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) { // Quick check on the number of edges. It requires n - 1 edges to connect n vertices.
            return false;
        }
        // Init CC id array.
        int[] ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }
        // Check cycle.
        for (int[] edge : edges) {
            // Find connected component ids of the two nodes.
            int r1 = find(ids, edge[0]);
            int r2 = find(ids, edge[1]);
            if (r1 == r2) { // If two vertices are already connected, there is a cycle.
                return false;
            }
            // Union the 2 nodes.
            ids[r1] = r2; // Add edges[i][0] to one connected component.
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
    private int find(int ids[], int i) {
        while (i != ids[i]) {
            // Here if we found the child's id are not the same as the parent's.
            // We know the parent can be an intermediate id.
            // So we set parent's id to grand parent's id.
            // Which will dynamically balance the tree thus reducing O(n) to O(1).
            ids[i] = ids[ids[i]];
            i = ids[i];
        }
        return i;
    }
}