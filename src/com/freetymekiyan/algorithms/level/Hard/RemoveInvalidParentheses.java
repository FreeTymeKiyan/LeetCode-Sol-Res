package com.freetymekiyan.algorithms.level.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
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
     * Take the string after removing one parentheses as a level.
     * First add the original string given to a queue.
     * Then start BFS, each time poll a string from the queue.
     * Check if the polled string is valid, if its valid, we don't need to generate next level.
     * If it's not, generate all possible strings for the next level by removing one parentheses.
     * <p>
     * Note that once we found one valid string, we know the minimum number.
     * No need to generate the next level anymore.
     * We can also use a String set to avoid duplicate check on strings.
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {
            return res;
        }
        // BFS
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        visited.add(s);
        boolean found = false;
        while (!queue.isEmpty()) {
            String t = queue.poll();

            if (isValid(t)) {
                res.add(t);
                found = true;
            }

            if (found) { // No need to generate the next level.
                continue;
            }

            // Generate all possible states for next level
            for (int i = 0; i < t.length(); i++) {
                if (t.charAt(i) != '(' && t.charAt(i) != ')') { // Skip other chars
                    continue;
                }

                t = t.substring(0, i) + t.substring(i + 1); // If i + 1 == t.length(), t.substring(i + 1) will be ""

                if (!visited.contains(t)) {
                    queue.add(t);
                    visited.add(t);
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
            } else if (c == ')' && count-- == 0) {
                return false;
            }
        }

        return count == 0;
    }

    /**
     * DFS.
     * https://leetcode.com/discuss/81478/easy-short-concise-and-fast-java-dfs-3-ms-solution
     */
    public List<String> removeInvalidParentheses2(String s) {
        List<String> res = new ArrayList<>();
        remove(s, res, 0, 0, new char[]{'(', ')'});
        return res;
    }

    private void remove(String s, List<String> res, int last_i, int last_j, char[] par) {
        for (int counter = 0, i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) {
                counter++;
            }
            if (s.charAt(i) == par[1]) {
                counter--;
            }
            if (counter >= 0) {
                continue;
            }
            for (int j = last_j; j <= i; j++) {
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
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
