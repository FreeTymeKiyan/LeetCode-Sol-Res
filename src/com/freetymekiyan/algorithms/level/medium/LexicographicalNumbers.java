package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return 1 - n in lexicographical order.
 * <p>
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 * <p>
 * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 */
public class LexicographicalNumbers {

    /**
     * DFS.
     * Search for the next possible number to add to result.
     *         1
     *     /       \
     *   10 ...    19
     *   /       /    \
     * 100 ...  190...199
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>(n);
        for (int i = 1; i < 10; i++) {
            dfs(i, n, res);
        }
        return res;
    }

    private void dfs(int curr, int n, List<Integer> res) {
        if (curr > n) {
            return;
        }
        res.add(curr);
        for (int i = 0; i < 10; i++) {
            int next = 10 * curr + i;
            if (next > n) { // Early prune
                return;
            }
            dfs(next, n, res);
        }
    }

}
