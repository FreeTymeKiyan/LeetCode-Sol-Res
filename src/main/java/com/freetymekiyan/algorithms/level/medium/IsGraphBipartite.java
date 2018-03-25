package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 785. Is Graph Bipartite?
 * <p>
 * Given an undirected graph, return true if and only if it is bipartite.
 * <p>
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that
 * every edge in the graph has one node in A and another node in B.
 * <p>
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j
 * exists. Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i]
 * does not contain i, and it doesn't contain any element twice.
 * <p>
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * <p>
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation:
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 * <p>
 * Note:
 * <p>
 * graph will have length in range [1, 100].
 * graph[i] will contain integers in range [0, graph.length - 1].
 * graph[i] will not contain i or duplicate values.
 * The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 * <p>
 * Related Topics: Depth-first Search, Breadth-first Search, Graph
 */
public class IsGraphBipartite {

    /**
     * DFS.
     * Each edge connects nodes from different sets.
     * So if a node is added to one set, all its neighbors are added to another.
     * Then when it comes to the neighbor, its neighbor has to be in the other set.
     * If not, then it's not bipartite.
     * Continue until all nodes are traversed.
     * <p>
     * Also the graph might not be fully connected.
     * Make sure all nodes are traversed by checking whether each node is visited.
     */
    public boolean isBipartite(int[][] graph) {
        Map<Integer, Boolean> visited = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            if (!visited.containsKey(i) && !dfs(graph, i, visited)) {
                return false;
            }
        }
        return true;
    }

    /**
     * DFS, iterative.
     * Check with each visited neighbor to see if the nodes are in same set or not.
     */
    private boolean dfs(int[][] graph, int start, Map<Integer, Boolean> visited) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        visited.put(start, true);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            boolean isOneSet = visited.get(node);
            for (int nei : graph[node]) {
                if (visited.containsKey(nei)) {
                    if (isOneSet == visited.get(nei)) return false;
                } else {
                    stack.push(nei);
                    visited.put(nei, !isOneSet);
                }
            }
        }
        return true;
    }

    /**
     * Each node has only 3 states: not visited, in one set, in the other set.
     * So use int[] to replace hash map to run faster.
     * 0 means not visited. 1 means one set. -1 means the other.
     */
    public boolean isBipartite2(int[][] graph) {
        int n = graph.length;
        int[] sets = new int[n];
        for (int i = 0; i < n; i++) {
            if (sets[i] == 0 && !dfs(graph, sets, 1, i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * DFS, recursive.
     * If the vertex is visited, check whether the set is the same.
     * If not the same, the vertex is assigned to 2 sets, invalid.
     * If the vertex is not visited, set its set.
     * Then recurse on its neighbors.
     * All neighbors should be in the other set, which is current set * -1.
     */
    private boolean dfs(int[][] graph, int[] sets, int set, int vertex) {
        if (sets[vertex] != 0) {
            return sets[vertex] == set;
        }
        sets[vertex] = set;
        for (int nei : graph[vertex]) {
            if (!dfs(graph, sets, set * -1, nei)) {
                return false;
            }
        }
        return true;
    }
}