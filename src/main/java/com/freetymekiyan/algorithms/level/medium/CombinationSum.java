package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all
 * unique combinations in C where the candidate numbers sums to T.
 * <p>
 * The same repeated number may be chosen from C <strong>unlimited</strong>
 * number of times.
 * <p>
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order.
 * (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 2,3,6,7 and target 7,
 * A solution set is:
 * [7]
 * [2, 2, 3]
 * <p>
 * Tags: Backtracking
 */
class CombinationSum {

  /**
   * Sort the array
   * [2, 3, 6, 7], 7
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    if (candidates == null || candidates.length == 0) {
      return Collections.emptyList();
    }
    final List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(candidates);
    helper(candidates, target, 0, new ArrayList<>(), res);
    return res;
  }

  /**
   * Backtracking
   */
  private void helper(int[] candidates, int target, int pos, List<Integer> comb, List<List<Integer>> res) {
    if (target == 0) {
      res.add(new ArrayList<>(comb)); // dereference
      return;
    }
    for (int i = pos; i < candidates.length; i++) {
      int newTarget = target - candidates[i];
      if (newTarget >= 0) {
        comb.add(candidates[i]);
        helper(candidates, newTarget, i, comb, res); // note i
        comb.remove(comb.size() - 1);
      } else {
        break; // too big
      }
    }
  }
}
