package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 * <p>
 * OJ's undirected graph serialization:
 * Nodes are labeled uniquely.
 * <p>
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 * <p>
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 * <p>
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 * Visually, the graph looks like the following:
 * <p>
 * |    1
 * |   / \
 * |  /   \
 * | 0 --- 2
 * |      / \
 * |      \_/
 * <p>
 * Company Tags: Pocket, Gems, Google, Uber, Facebook
 * Tags: Depth-first Search, Breadth-first Search, Graph
 * Similar Problems: (H) Copy List with Random Pointer
 */
public class CloneGraph {

    /**
     * BFS. O(V) Time. O(V) Space.
     * Use map<Integer, UndirectedGraphNode> to represent the new graph and a visited set.
     * For each visit, connect the node with neighboring nodes.
     * If neighboring nodes not in new graph yet, need to create them.
     * Visit:
     * Check whether current label is in new graph:
     * | If not, create a new node with current label and put in map.
     * If neighbors exist:
     * | For each neighbor:
     * |   If its not visited, add it to queue and create a new node.
     * |   Connect current node with this neighbor.
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        Queue<UndirectedGraphNode> q = new ArrayDeque<>();
        Map<Integer, UndirectedGraphNode> graph = new HashMap<>(); // New graph, also a visited set.
        q.offer(node);
        graph.put(node.label, new UndirectedGraphNode(node.label));
        while (!q.isEmpty()) {
            UndirectedGraphNode cur = q.poll();
            if (cur.neighbors != null) {
                for (UndirectedGraphNode n : cur.neighbors) {
                    // Add all unvisited neighbors to the queue.
                    if (!graph.containsKey(n.label)) {
                        q.offer(n);
                        graph.put(n.label, new UndirectedGraphNode(n.label));
                    }
                    // Connect new node with its neighbor.
                    graph.get(cur.label).neighbors.add(graph.get(n.label));
                }
            }
        }
        return graph.get(node.label);
    }

    /**
     * DFS. Backtracking.
     * Pass the node and map to its neighbors.
     * Add neighbors backtrack result to its neighbors and return.
     */
    public UndirectedGraphNode cloneGraphB(UndirectedGraphNode node) {
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        return dfs(node, map);
    }

    private UndirectedGraphNode dfs(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> map) {
        if (node == null) {
            return null;
        }
        if (!map.containsKey(node.label)) { // Not visited.
            map.put(node.label, new UndirectedGraphNode(node.label)); // Add to new graph.
        }
        UndirectedGraphNode clone = map.get(node.label);
        for (UndirectedGraphNode n : node.neighbors) {
            if (!map.containsKey(n.label)) { // Only DFS unvisited neighbors.
                clone.neighbors.add(dfs(n, map));
            } else { // Add visited neighbors from map directly.
                clone.neighbors.add(map.get(n.label));
            }
        }
        return clone;
    }

    class UndirectedGraphNode {

        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }
}
