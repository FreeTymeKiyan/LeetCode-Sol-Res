package com.freetymekiyan.algorithms.level.hard;

import java.util.*;

/**
 * 126. Word Ladder II
 * <p>
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
 * from beginWord to endWord, such that:
 * <p>
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * For example,
 * <p>
 * Given:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * Return
 * [
 * ["hit","hot","dot","dog","cog"],
 * ["hit","hot","lot","log","cog"]
 * ]
 * Note:
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * UPDATE (2017/1/20):
 * The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code
 * definition to get the latest changes.
 * <p>
 * Related Topics: Array, String, Backtracking, Breadth-first Search
 * Similar Questions: (M) Word Ladder
 */
public class WordLadder2 {

    /**
     * BFS, DFS.
     * The solution cannot be easily derived from Word Ladder 1 since we need to generate all possible paths.
     * BFS by nature can find the shortest path, but there is no way to generate paths along with BFS.
     * To track the paths we must record we've traversed. This is something DFS or backtracking is good at.
     * But DFS along won't be able find the shortest path directly.
     * So this comes to a combination of BFS and DFS.
     * We use BFS to generate a shortest path graph.
     * Then use DFS to traverse/backtrack this graph to get shortest paths.
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> distances = new HashMap<>();

        bfs(beginWord, endWord, dict, graph, distances);
        List<List<String>> paths = new ArrayList<>();
        dfs(beginWord, endWord, graph, distances, new ArrayList<>(), paths);
        return paths;
    }

    /**
     * This BFS is unlike word ladder 1, which we only care about the path length.
     * This one cares about the graph structure, so we connect a string with it's neighbor words.
     * Why not use level traversal?
     * Level traversal requires a set to mark words as visited. But a word can be used multiple times.
     * If a word is used by a path, later paths won't be able to use it.
     * If we don't mark word as visited, there can be a loop instead.
     * So this one uses a distance map to record the word's transformation distance to the begin word.
     * Combining with BFS, if a word is already in distance map, it means:
     * 1. The word is traversed before.
     * 2. That is the word's shortest distance.
     * So we add all possible words to a word's neighbor, which gives us all possible paths when traversing.
     * When the distance of a word doesn't exist, we enqueue the word since it's not been traversed yet.
     */
    private void bfs(String beginWord, String endWord, Set<String> dict, Map<String, List<String>> graph, Map<String, Integer> distances) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        distances.put(beginWord, 0);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            graph.putIfAbsent(cur, new ArrayList<>());
            int curDistance = distances.get(cur);
            List<String> neighbors = getNeighbors(cur, dict);

            for (String n : neighbors) {
                graph.get(cur).add(n);
                if (!distances.containsKey(n)) {
                    distances.put(n, curDistance + 1);
                    queue.offer(n);
                }
            }

        }
    }

    /**
     * Same as Word Ladder 1.
     * Transform a word by replacing each character with a different character, one by one.
     * If the transformed word is in given dictionary, add it to result.
     */
    private List<String> getNeighbors(String cur, Set<String> dict) {
        List<String> neighbors = new ArrayList<>();
        for (int i = 0; i < cur.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                char[] chars = cur.toCharArray();
                if (chars[i] == c) {
                    continue;
                }
                chars[i] = c;
                String next = new String(chars);
                if (dict.contains(next)) {
                    neighbors.add(next);
                }
            }
        }
        return neighbors;
    }

    /**
     * Backtrack the graph with distance map to generate all shortest paths.
     * The graph provides all possible transformation of a word.
     * The distance map makes sure that the word is the next word.
     * At each iteration, add beginWord to path first.
     * Then get neighbors with distance + 1 and backtrack them.
     * After all neighbors are done, remove the beginWord from path.
     */
    private void dfs(String beginWord, String endWord, Map<String, List<String>> graph, Map<String, Integer> distance, ArrayList<String> p, List<List<String>> paths) {
        if (beginWord.equals(endWord)) {
            List<String> path = new ArrayList<>(p);
            path.add(beginWord);
            paths.add(path);
            return;
        }

        int d = distance.get(beginWord);
        p.add(beginWord);
        for (String next : graph.get(beginWord)) {
            if (distance.get(next) == d + 1) {
                dfs(next, endWord, graph, distance, p, paths);
            }
        }
        p.remove(p.size() - 1);
    }
}