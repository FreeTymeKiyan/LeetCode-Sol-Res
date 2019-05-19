package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such
 * that a + b + c + d = target? Find all unique quadruplets in the array which
 * gives the sum of target.
 * <p>
 * Note:
 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤
 * b ≤ c ≤ d)
 * <p>
 * The solution set must not contain duplicate quadruplets.
 * <p>
 * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
 * A solution set is:
 * (-1,  0, 0, 1)
 * (-2, -1, 1, 2)
 * (-2,  0, 0, 2)
 * <p>
 * Tags: Array, HashTable, Two pointers
 */
class FourSum {

  /**
   * Four pointers, O(n^3) time
   * First pointer i starts from 1 to num.length - 4, 3 indices remain
   * Second pointer j starts from i + 1 to num.length - 3, 2 indices remain
   * Then get newTarget and search from both ends of the remaining numbers
   * Skip duplicate every time
   */
  public List<List<Integer>> fourSum(int[] num, int target) {
    if (num == null || num.length < 4) {
      return Collections.emptyList();
    }
    final List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(num);
    for (int i = 0; i < num.length - 3; i++) { // 3 indices remain
      if (i > 0 && num[i] == num[i - 1]) { // Skip duplicates
        continue;
      }
      for (int j = i + 1; j < num.length - 2; j++) { // 2 indices remain
        if (j > i + 1 && num[j] == num[j - 1]) { // Skip duplicates
          continue;
        }
        int newTarget = target - num[i] - num[j]; // 2 sum
        int lo = j + 1;
        int hi = num.length - 1;
        while (lo < hi) {
          if (lo > j + 1 && num[lo] == num[lo - 1]) { // Skip duplicates
            lo++;
            continue;
          }
          if (hi < num.length - 1 && num[hi] == num[hi + 1]) { // Skip duplicates
            hi--;
            continue;
          }
          int sum = num[lo] + num[hi];
          if (sum < newTarget) {
            lo++;
          } else if (sum > newTarget) {
            hi--;
          } else { // sum == newTarget
            res.add(Arrays.asList(num[i], num[j], num[lo], num[hi]));
            lo++;
            hi--;
          }
        }
      }
    }

    return res;
  }
}
