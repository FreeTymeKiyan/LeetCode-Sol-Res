package com.freetymekiyan.algorithms.level.hard;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
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
     * Then first add all 0 in-degree nodes in a queue.
     * While the queue is not empty, remove node from the queue and add to result order.
     * Remove the node from graph as well by reducing the in-degree of connected nodes.
     * If those nodes become 0 in-degree as well, add them into queue.
     * When its done, check whether the result length is the same as nodes.
     */
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        // Init in-degree map.
        Map<Character, Integer> inDeg = new HashMap<>();
        for (String s : words) { // Init in-degree of all characters given to 0.
            for (char c : s.toCharArray()) {
                inDeg.put(c, 0);
            }
        }
        // Build the graph and in-degree from words array.
        Map<Character, Set<Character>> graph = new HashMap<>(); // Use set to avoid duplicates.
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i]; // Compare current word and the next word.
            String next = words[i + 1];
            // Special case: when "abcee" is put before "abc".
            // This case there won't be a DAG.
            if (cur.length() > next.length() && cur.startsWith(next)) {
                return "";
            }
            // Find the first different character.
            for (int j = 0; j < Math.min(cur.length(), next.length()); j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) { // Create an edge from c1 -> c2.
                    Set<Character> set = graph.containsKey(c1) ? graph.get(c1) : new HashSet<>();
                    if (!set.contains(c2)) {
                        set.add(c2);
                        graph.put(c1, set); // Update graph.
                        inDeg.put(c2, inDeg.get(c2) + 1); // Update degree. Set makes sure in-degree count is correct.
                    }
                    break; // IMPORTANT! No need to continue.
                }
            }
        }
        // Topological Sort according to Kahn's Algo, BFS
        Queue<Character> q = new ArrayDeque<>();
        // First add all nodes with 0 degree to queue
        for (char c : inDeg.keySet()) {
            if (inDeg.get(c) == 0) {
                q.offer(c);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!q.isEmpty()) {
            char c = q.poll();
            res.append(c);
            // Check the rest of the node and update in-degree
            if (graph.containsKey(c)) {
                for (char n : graph.get(c)) {
                    inDeg.put(n, inDeg.get(n) - 1);
                    if (inDeg.get(n) == 0) {
                        q.offer(n);
                    }
                }
            }
        }
        return res.length() == inDeg.size() ? res.toString() : ""; // Check if all nodes are in result.
    }

}
