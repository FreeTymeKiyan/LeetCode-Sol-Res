package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
     * BFS, O(n) Time, O(n) Space
     * Use map<Integer, UndirectedGraphNode> find a node with its label.
     * BFS to find all neighbors.
     * Then find all neighbors' neighbors.
     * Return first node.
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        q.add(node);
        while (!q.isEmpty()) {
            UndirectedGraphNode cur = q.poll();
            if (!map.containsKey(cur.label)) { // Not in the new graph yet
                map.put(cur.label, new UndirectedGraphNode(cur.label));
            }
            if (cur.neighbors != null) {
                for (UndirectedGraphNode n : cur.neighbors) {
                    // Add all unvisited neighbors to the queue
                    if (!map.containsKey(n.label)) {
                        q.add(n);
                        map.put(n.label, new UndirectedGraphNode(n.label));
                    }
                    // Connect new node with new neighbors
                    map.get(cur.label).neighbors.add(map.get(n.label));
                }
            }
        }
        return map.get(node.label);
    }

    /**
     * DFS.
     * Pass the node and map to its neighbors.
     * Add neighbors dfs result to its neighbors and return.
     */
    public UndirectedGraphNode cloneGraphB(UndirectedGraphNode node) {
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
        return dfs(node, map);
    }

    private UndirectedGraphNode dfs(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> map) {
        if (node == null) { // Base case
            return null;
        }
        if (map.containsKey(node.label)) { // Already in graph map
            return map.get(node.label);
        }
        map.put(node.label, new UndirectedGraphNode(node.label)); // Create new node in map
        for (int i = 0; i < node.neighbors.size(); i++) { // Visit all neighbors and add to clone
            map.get(node.label).neighbors.add(dfs(node.neighbors.get(i), map));
        }
        return map.get(node.label);
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
