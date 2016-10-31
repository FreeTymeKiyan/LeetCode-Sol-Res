package com.freetymekiyan.algorithms.level.medium;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers
 * out of 1 ... n.
 * <p>
 * For example,
 * If n = 4 and k = 2, a solution is:
 * | [
 * |   [2,4],
 * |   [3,4],
 * |   [2,3],
 * |   [1,2],
 * |   [1,3],
 * |   [1,4],
 * | ]
 * <p>
 * Tags: Backtracking
 */
public class Combinations {

    /**
     * Backtracking.
     * From 1 to n.
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, n, k, 1, new ArrayList<>());
        return res;
    }

    /**
     * Stop when k is 0, meaning that all k numbers are picked.
     * | Add combination to result.
     * For each i from start to n:
     * | Add i to current combination that means picked.
     * | Backtrack to pick k-1 numbers from i + 1 to n.
     * | Reset by removing last picked value from combination.
     */
    private void backtrack(List<List<Integer>> res, int n, int k, int start, List<Integer> comb) {
        if (k == 0) {
            res.add(new ArrayList<>(comb));
            return;
        }

        for (int i = start; i <= n; i++) {
            comb.add(i);
            backtrack(res, n, k - 1, i + 1, comb);
            comb.remove(comb.size() - 1);
        }
    }

    /**
     * Backtracking.
     * From n to 1.
     */
    public List<List<Integer>> combineB(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combineB(n, k, new ArrayList<>(), res);
        return res;
    }

    /**
     * DFS.
     * Base case:
     * If k is 0, add the combination.
     * If n <= k, add all n numbers.
     * Recurrence relation:
     * Final combination consists of two parts:
     * 1. Pick current number, pick k - 1 numbers from n - 1 to 1.
     * 2. Don't pick current number, pick k numbers from n - 1 to 1.
     */
    private void combineB(int n, int k, List<Integer> comb, List<List<Integer>> result) {
        if (k == 0) {
            result.add(comb);
            return;
        }
        if (n <= k) { // Choose all.
            for (int i = n; i > 0; i--) {
                comb.add(i);
            }
            result.add(comb);
            return;
        }
        // With n, choose k-1 from n-1.
        List<Integer> combWithN = new ArrayList<>(comb);
        combWithN.add(n);
        combineB(n - 1, k - 1, combWithN, result);
        // Without n, choose k from n-1.
        combineB(n - 1, k, comb, result);
    }

    @Test
    public void testExamples() {
        List<List<Integer>> lists = combine(4, 2);
        for (List<Integer> l : lists) {
            System.out.println(l.toString());
        }
    }
}
