import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible
 * results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Examples:
 *
 * "()())()" -> ["()()()", "(())()"]
 *
 * "(a)())()" -> ["(a)()()", "(a())()"]
 *
 * ")(" -> [""]
 *
 * Tags: Depth-first Search, Breadth-first Search
 *
 * Similar Problems: (E) Valid Parentheses
 */
public class RemoveInvalidParentheses {

    /**
     * BFS check all possible state of each level
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {
            return res;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.add(s);
        visited.add(s);
        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();

            if (isValid(s)) {
                res.add(s);
                found = true;
            }

            if (found) {
                continue;
            }

            // generate all possible states for next level
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '(' && s.charAt(i) != ')') {
                    continue;
                }

                String t = s.substring(0, i) + s.substring(i + 1);

                if (!visited.contains(t)) {
                    queue.add(t);
                    visited.add(t);
                }
            }
        }
        return res;
    }

    private boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            }
            if (c == ')' && count-- == 0) {
                return false;
            }
        }

        return count == 0;
    }

    /**
     * DFS
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
