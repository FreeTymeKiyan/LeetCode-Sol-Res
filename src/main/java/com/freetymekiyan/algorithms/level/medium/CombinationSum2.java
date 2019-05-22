package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find
 * all unique combinations in C where the candidate numbers sums to T.
 * <p>
 * Each number in C may only be used <strong>once</strong> in the combination.
 * <p>
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order.
 * (ie, a1 ≤ a2 ≤ … ≤ ak).
 * <p>
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 * A solution set is:
 * [1, 7]
 * [1, 2, 5]
 * [2, 6]
 * [1, 1, 6]
 * <p>
 * Tags: Array, Backtracking
 */
class CombinationSum2 {
  public static void main(String[] args) {
    int[] candidates = {10, 1, 2, 7, 6, 1, 5};
    int tar = 8;
    List<List<Integer>> solution = new CombinationSum2().dfs(candidates, tar);
    for (List<Integer> l : solution) System.out.println(l.toString());
  }

  public List<List<Integer>> dfs(int[] num, int target) {
    if (num == null || num.length == 0) {
      return Collections.emptyList();
    }
    final List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(num);
    dfs(num, target, 0, new ArrayList<>(), res);
    return res;
  }

  /**
   * Skip duplicates after new target is generated
   */
  public void dfs(int[] num, int target, int index, List<Integer> comb, List<List<Integer>> result) {
    if (target == 0) {
      result.add(new ArrayList<>(comb));
      return;
    }

    for (int i = index; i < num.length; i++) {
      int newTarget = target - num[i];
      if (newTarget >= 0) {
        comb.add(num[i]);
        dfs(num, newTarget, i + 1, comb, result);
        comb.remove(comb.size() - 1);
      } else {
        break;
      }
      // Skip duplicates
      while (i < num.length - 1 && num[i] == num[i + 1]) {
        i++; // Move i to the end of the duplicates
      }
      // i++ of the for loop will move i to next number that is not a duplicate
    }
  }
}
