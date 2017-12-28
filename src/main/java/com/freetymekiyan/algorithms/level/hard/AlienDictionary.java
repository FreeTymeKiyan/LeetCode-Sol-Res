package com.freetymekiyan.algorithms.level.hard;

import java.util.*;

/**
 * 269. Alien Dictionary
 * <p>
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You
 * receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new
 * language. Derive the order of letters in this language.
 * <p>
 * For example,
 * Given the following words in dictionary,
 * <p>
 * | [
 * |   "wrt",
 * |   "wrf",
 * |   "er",
 * |   "ett",
 * |   "rftt"
 * | ]
 * The correct order is: "wertf".
 * <p>
 * Note:
 * You may assume all letters are in lowercase.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 * <p>
 * Company Tags: Google, Airbnb, Facebook, Twitter, Snapchat, Pocket Gems
 * Tags: Graph, Topological Sort
 * Similar Problems: (M) Course Schedule II
 */
public class AlienDictionary {

    /**
     * Graph. Topological Sort. BFS.
     * Two steps:
     * 1) Build a graph and in-degree from the given words.
     * 2) Do topological sort.
     * <p>
     * Topological sort based on Kahn's algo.
     * It needs a graph and each node's in-degree.
     * Then start with all 0 in-degree nodes, enqueue them.
     * While the queue is not empty, dequeue a node and add to result order.
     * Remove edges start from this node by reducing the in-degree of its neighbors.
     * If those nodes become 0 in-degree as well, enqueue.
     * When queue is empty, it's done.
     * Before return, check if the order is valid by comparing the result length with # of graph nodes.
     */
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";

        Map<Character, Integer> inDegrees = new HashMap<>();
        for (String s : words) { // Init in-degree of all characters given to 0.
            for (char c : s.toCharArray()) {
                inDegrees.put(c, 0);
            }
        }

        Map<Character, Set<Character>> graph = new HashMap<>(); // Use set to avoid duplicate edge.
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            if (w1.startsWith(w2) && w1.length() > w2.length()) { // "abcee" -> "abc", invalid.
                return "";
            }
            for (int j = 0; j < w1.length() && j < w2.length(); j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if (c1 != c2) {
                    if (!graph.containsKey(c1)) {
                        graph.put(c1, new HashSet<>());
                    }
                    if (graph.get(c1).add(c2)) {
                        inDegrees.put(c2, inDegrees.get(c2) + 1);
                    }
                    break; // Should break once one edge is found.
                }
            }
        }

        Queue<Character> queue = new ArrayDeque<>();
        for (Map.Entry<Character, Integer> e : inDegrees.entrySet()) {
            if (e.getValue() == 0) queue.offer(e.getKey());
        }
        StringBuilder order = new StringBuilder();
        while (!queue.isEmpty()) {
            char n = queue.poll();
            order.append(n);
            // Remove edges from graph by updating in degrees of neighbors.
            if (graph.containsKey(n)) { // Avoid NPE.
                for (char neighbor : graph.get(n)) {
                    inDegrees.put(neighbor, inDegrees.get(neighbor) - 1); // Update in degree.
                    if (inDegrees.get(neighbor) == 0) { // Add 0 in degree node to queue.
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return order.length() == inDegrees.size() ? order.toString() : ""; // Check if all nodes are in result.
    }
}