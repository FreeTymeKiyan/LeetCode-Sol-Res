package com.freetymekiyan.algorithms.level.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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
     * Graph, Topological Sort.
     * Two steps:
     * 1) Build a graph from the given words.
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
        String result = "";
        if (words == null || words.length == 0) {
            return result;
        }

        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> degree = new HashMap<>();
        // Init degree
        for (String s : words) {
            for (char c : s.toCharArray()) {
                degree.put(c, 0);
            }
        }
        // Build map
        for (int i = 0; i < words.length - 1; i++) {
            String cur = words[i];
            String next = words[i + 1];
            // Special case: when "abcee" is put before "abc"
            if (cur.length() > next.length() && cur.startsWith(next)) {
                return "";
            }
            // Find the first different character
            for (int j = 0; j < Math.min(cur.length(), next.length()); j++) {
                char c1 = cur.charAt(j);
                char c2 = next.charAt(j);
                if (c1 != c2) { // Create an edge from c1 -> c2
                    Set<Character> set = map.containsKey(c1) ? map.get(c1) : new HashSet<>();
                    if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        degree.put(c2, degree.get(c2) + 1); // Update degree
                    }
                    break;
                }
            }
        }
        // Topological Sort according to Kahn's Algo, BFS
        Queue<Character> q = new LinkedList<>();
        // First add all nodes with 0 degree to queue
        for (char c : degree.keySet()) {
            if (degree.get(c) == 0) {
                q.add(c);
            }
        }
        while (!q.isEmpty()) {
            char c = q.poll();
            result += c;
            // Check the rest of the node and update in-degree
            if (map.containsKey(c)) {
                for (char c2 : map.get(c)) {
                    degree.put(c2, degree.get(c2) - 1);
                    if (degree.get(c2) == 0) {
                        q.add(c2);
                    }
                }
            }
        }
        if (result.length() != degree.size()) { // Check if all nodes are in result
            return "";
        }
        return result;

    }

}
