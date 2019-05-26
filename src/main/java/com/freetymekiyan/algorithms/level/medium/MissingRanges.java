package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 163. Missing Ranges
 * <p>
 * Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its
 * missing ranges.
 * <p>
 * Example:
 * <p>
 * Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
 * Output: ["2", "4->49", "51->74", "76->99"]
 * <p>
 * Companies: Google, Facebook, Amazon, Bloomberg, Two Sigma, Oracle
 * <p>
 * Related Topics: Array
 * <p>
 * Similar Questions: (M) Summary Ranges
 */
public class MissingRanges {

  public List<String> findMissingRanges(int[] nums, int lower, int upper) {
    final List<String> res = new ArrayList<>();
    if (nums == null || nums.length == 0) {
      res.add(rangeString(lower, upper));
      return res;
    }
    int nextMissing = lower;
    for (int num : nums) {
      if (nextMissing == num - 1) {
        res.add(rangeString(nextMissing, nextMissing));
      } else if (nextMissing < num) {
        res.add(rangeString(nextMissing, num - 1));
      }
      if (num == Integer.MAX_VALUE) return res; // Avoid Integer out of range
      nextMissing = num + 1;
    }
    if (nextMissing == upper) {
      res.add(rangeString(nextMissing, nextMissing));
    } else if (nextMissing < upper) {
      res.add(rangeString(nextMissing, upper));
    }
    return res;
  }

  private String rangeString(final int lower, final int upper) {
    if (lower == upper) {
      return Integer.toString(lower);
    } else {
      return lower + "->" + upper;
    }
  }
}