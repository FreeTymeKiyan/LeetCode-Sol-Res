package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Numbers can be regarded as product of its factors. For example,
 * <p>
 * 8 = 2 x 2 x 2;
 * = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * <p>
 * Note:
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 * <p>
 * Examples:
 * input: 1
 * output:
 * []
 * input: 37
 * output:
 * []
 * input: 12
 * output:
 * |[
 * |  [2, 6],
 * |  [2, 2, 3],
 * |  [3, 4]
 * |]
 * input: 32
 * output:
 * |[
 * |  [2, 16],
 * |  [2, 2, 8],
 * |  [2, 2, 2, 4],
 * |  [2, 2, 2, 2, 2],
 * |  [2, 4, 4],
 * |  [4, 8]
 * |]
 * <p>
 * Company Tags: LinkedIn, Uber
 * Tags: Backtracking
 * Similar Problems: (M) Combination Sum
 */
public class FactorCombinations {

    /**
     * Backtracking.
     * Factors are from 2 to sqrt(n), inclusive.
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> factors = new ArrayList<>();
        backtrack(factors, n, 2, new ArrayList<>());
        return factors;
    }

    /**
     * Stop condition:
     * n is 1, return. If have at least two factors, dereference and add to result.
     * Visit:
     * For each i from start to sqrt(n):
     * | If n is divisible by i:
     * |   Add i to factors.
     * |   backtrack on n / i, starting from i. (12 -> 2, 2, 3)
     * |   Reset.
     * | If n is not divisible by i, skip.
     * Special case:
     * Besides [start, sqrt(n)], n itself can also be a factor.
     */
    private void backtrack(List<List<Integer>> res, int n, int start, List<Integer> factors) {
        if (n == 1) {
            if (factors.size() > 1) { // If n=1, return empty list.
                res.add(new ArrayList<>(factors));
            }
            return;
        }

        for (int i = start; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                factors.add(i);
                backtrack(res, n / i, i, factors);
                factors.remove(factors.size() - 1);
            }
        }
        int i = n; // ===> here, change 2
        factors.add(i);
        backtrack(res, n / i, i, factors);
        factors.remove(factors.size() - 1);
    }
}
