package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be
 * used and each combination should be a unique set of numbers.
 * <p>
 * Ensure that numbers within the set are sorted in ascending order.
 * <p>
 * Example 1:
 * <p>
 * Input: k = 3, n = 7
 * <p>
 * Output:
 * <p>
 * [[1,2,4]]
 * <p>
 * Example 2:
 * <p>
 * Input: k = 3, n = 9
 * <p>
 * Output:
 * <p>
 * [[1,2,6], [1,3,5], [2,3,4]]
 * <p>
 * Tags: Array, Backtracking
 * Similar Problems: (M) Combination Sum
 */
public class CombinationSum3 {

    public static void main(String[] args) {
        CombinationSum3 cs = new CombinationSum3();
        System.out.println(cs.combinationSum3(3, 7));
        System.out.println(cs.combinationSum3(3, 9));
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList<>(), k, 1, n);
        return ans;
    }

    private void backtrack(List<List<Integer>> ans, List<Integer> comb, int k, int start, int n) {
        if (comb.size() > k) return; // no need to search in k+1 numbers

        if (comb.size() == k && n == 0) { // combination found
            List<Integer> res = new ArrayList<>(comb); // make a copy of the list
            ans.add(res);
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (n - i >= 0) { // n < i can be skipped
                comb.add(i);
                backtrack(ans, comb, k, i + 1, n - i);
                comb.remove(comb.size() - 1);
            }
        }
    }
}
