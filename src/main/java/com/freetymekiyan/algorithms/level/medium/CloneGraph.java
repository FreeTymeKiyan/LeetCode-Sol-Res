package com.freetymekiyan.algorithms.level.medium;

import java.util.*;

/**
 * 133. Clone Graph
 * <p>
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
     * DFS.
     * Pass the node and cloned map to its neighbors.
     * Add neighbors backtrack result to its neighbors and return.
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        return dfs(node, map);
    }

    /**
     * Statement: Given a graph node, return the cloned graph node.
     * Sub-problem: Build one node.
     * Complete task: Build current node. Build neighbors. Connect current node with its neighbors.
     * Base case: If current node is null, return null.
     * Implementation:
     * If the cloned map has the node's label, that means the node is built, just return the node from map.
     * If doesn't, create a new cloned node, put it in the map.
     * Then build its graph by dfs its neighbors.
     * Add each neighbor's dfs result to the cloned node.
     */
    private UndirectedGraphNode dfs(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> cloned) {
        if (cloned.containsKey(node.label)) {
            return cloned.get(node.label);
        }
        cloned.put(node.label, new UndirectedGraphNode(node.label));
        for (int i = 0; i < node.neighbors.size(); i++) {
            cloned.get(node.label).neighbors.add(dfs(node.neighbors.get(i), cloned));
        }
        return cloned.get(node.label);
    }

    /**
     * BFS. O(V) Time. O(V) Space.
     * Use map<Integer, UndirectedGraphNode> to store cloned new graph nodes.
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
    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        Queue<UndirectedGraphNode> q = new ArrayDeque<>();
        Map<Integer, UndirectedGraphNode> cloned = new HashMap<>(); // Cloned graph nodes.
        q.offer(node);
        cloned.put(node.label, new UndirectedGraphNode(node.label));
        while (!q.isEmpty()) {
            UndirectedGraphNode cur = q.poll();
            if (!cloned.containsKey(cur.label))
                cloned.put(cur.label, new UndirectedGraphNode(cur.label)); // Put in map to set as visited.
            if (cur.neighbors != null) {
                for (UndirectedGraphNode n : cur.neighbors) {
                    if (!cloned.containsKey(n.label)) { // Add all unvisited neighbors to the queue.
                        q.offer(n);
                        cloned.put(n.label, new UndirectedGraphNode(n.label));
                    }
                    // Connect cloned node with every neighbor. No matter visited or not.
                    cloned.get(cur.label).neighbors.add(cloned.get(n.label));
                }
            }
        }
        return cloned.get(node.label);
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