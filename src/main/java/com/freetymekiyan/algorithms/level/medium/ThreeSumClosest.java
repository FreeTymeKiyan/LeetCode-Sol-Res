package com.freetymekiyan.algorithms.level.medium;

import java.util.Arrays;

/**
 * Given an array S of n integers, find three integers in S such that the
 * sum is closest to a given number, target. Return the sum of the three
 * integers. You may assume that each input would have exactly one solution.
 * <p>
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * <p>
 * Tags: Arrays, Two pointers
 */
class ThreeSumClosest {

  /**
   * Sort and initialize min
   * use two pointers to manipulate sums
   * update min when closer
   * return when min equals to target or all done
   */
  public int threeSumClosest(int[] num, int target) {
    int closest = 0;
    if (num == null) { // Handle null input array
      return closest;
    }
    Arrays.sort(num);
    // Get first sum
    for (int i = 0; i < num.length && i < 3; i++) {
      closest += num[i];
    }
    if (num.length < 3) { // Handle arrays with < 3 numbers
      return closest;
    }
    // Search for closet sum with 2 pointers
    for (int i = 0; i < num.length - 2; i++) {
      int lo = i + 1;
      int hi = num.length - 1;
      while (lo < hi) {
        int sum = num[i] + num[lo] + num[hi];
        if (Math.abs(target - sum) < Math.abs(target - closest)) {
          closest = sum; // Current sum is even closer
          if (closest == target) return closest; // sum is exactly target
        }
        if (sum > target) hi--;
        else lo++;
      }
      // Done with num[i]
    }
    return closest;
  }
}
