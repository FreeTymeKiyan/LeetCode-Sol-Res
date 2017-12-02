package com.freetymekiyan.algorithms.level.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a
 * function to find the number of connected components in an undirected graph.
 * <p>
 * Example 1:
 * 0          3
 * |          |
 * 1 --- 2    4
 * Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
 * <p>
 * Example 2:
 * 0           4
 * |           |
 * 1 --- 2 --- 3
 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
 * <p>
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as
 * [1, 0] and thus will not appear together in edges.
 */
public class NumberofConnectedComponentsinanUndirectedGraph {

    private int[] id;

    /**
     * Quick union
     */
    public int countComponents(int n, int[][] edges) {
        Set<Integer> set = new HashSet<>();
        // Initialize id
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        // Build connected components
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }
        //
        for (int i = 0; i < n; i++) {
            set.add(root(i)); // O(n^2)
        }
        return set.size();
    }

    private int root(int i) {
        while (i != id[i]) i = id[i];
        return i;
    }

    private void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
}
