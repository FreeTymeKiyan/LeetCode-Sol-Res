package com.freetymekiyan.algorithms.level.hard;

import java.util.*;

/**
 * 301. Remove Invalid Parentheses
 * <p>
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible
 * results.
 * <p>
 * Note: The input string may contain letters other than the parentheses ( and ).
 * <p>
 * Examples:
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 * <p>
 * Company Tags: Facebook
 * Tags: Depth-first Search, Breadth-first Search
 * Similar Problems: (E) Valid Parentheses
 */
public class RemoveInvalidParentheses {

    /**
     * BFS.
     * Generate all possible next strings by removing one paren from current string.
     * First add the original string to a queue and a set to start BFS.
     * While queue is not empty:
     * | Poll a string from the queue.
     * | Check if the polled string is valid, if its valid:
     * |   Set the found flag. We don't need to generate next level.
     * | If found is true:
     * |   Continue to the next string in queue.
     * | If found is false:
     * |   Generate all possible strings for the next level by removing one parentheses.
     * <p>
     * Note that once we found one valid string, we know the minimum number.
     * No need to generate the next possible strings anymore.
     * Use a String set to avoid BFS cycles or duplicate checks.
     */
    public List<String> removeInvalidParentheses(String s) {
        if (s == null) { // "" should return [""].
            return Collections.emptyList();
        }
        List<String> res = new ArrayList<>();
        Queue<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>(); // Store visited strings.
        queue.offer(s);
        visited.add(s);
        boolean found = false; // Flag to stop generating new strings.
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            // Visit.
            if (isValid(cur)) { // First valid string is found.
                res.add(cur);
                found = true;
            }
            if (found) { // No need to generate the more strings.
                continue; // Still check all the strings remain in queue.
            }
            // Generate all possible strings by removing one paren.
            for (int i = 0; i < cur.length(); i++) {
                if (cur.charAt(i) != '(' && cur.charAt(i) != ')') { // Skip chars that are not paren.
                    continue;
                }
                cur = cur.substring(0, i) + cur.substring(i + 1); // Remove i.
                if (!visited.contains(cur)) {
                    queue.add(cur);
                    visited.add(cur);
                }
            }
        }
        return res;
    }

    /**
     * Use an integer counter as a Stack.
     * Make sure there are more left parens than right ones.
     * Increase the counter when there is a left paren.
     * If there is a right paren, check whether there is enough paren to match first.
     * If counter is zero, which means no left paren available, return false;
     * Otherwise decrement counter by 1.
     * Return true if counter is 0 that is fully matched.
     * Otherwise false.
     */
    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }

    /**
     * DFS.
     * https://leetcode.com/discuss/81478/easy-short-concise-and-fast-java-dfs-3-ms-solution
     */
    public List<String> removeInvalidParentheses2(String s) {
        List<String> result = new ArrayList<>();
        remove(s, result, 0, 0, new char[]{'(', ')'});
        return result;
    }

    private void remove(String s, List<String> res, int lastI, int lastJ, char[] par) {
        for (int count = 0, i = lastI; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) {
                count++;
            }
            if (s.charAt(i) == par[1]) {
                count--;
            }
            if (count >= 0) { // More left paren.
                continue;
            }
            for (int j = lastJ; j <= i; j++) {
                if (s.charAt(j) == par[1] && (j == lastJ || s.charAt(j - 1) != par[1])) {
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), res, i, j, par);
                }
            }
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') { // finished left to right
            remove(reversed, res, 0, 0, new char[]{')', '('});
        } else { // finished right to left
            res.add(reversed);
        }
    }
}