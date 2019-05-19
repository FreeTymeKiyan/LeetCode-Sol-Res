package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 15. 3Sum
 * <p>
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in
 * the array which gives the sum of zero.
 * <p>
 * Note: The solution set must not contain duplicate triplets.
 * <p>
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * Company Tags: Amazon, Microsoft, Bloomberg, Facebook, Adobe
 * Tags: Array, Two Pointers
 * Similar Problems: (E) Two Sum, (M) 3Sum Closest, (M) 4Sum, (M) 3Sum Smaller
 */
class ThreeSum {

  /**
   * Two Pointers.
   * Sort given array first.
   * Traverse the array with 1 pointer.
   * Use another 2 pointers from both start(i + 1) and end to find the target.
   * How to avoid duplicate? Compare current number with the previous one, if same, skip.
   * How to early pruning? When current number is positive, stop.
   */
  public List<List<Integer>> threeSum(int[] nums) {
    if (nums == null || nums.length < 3) {
      return Collections.emptyList();
    }

    Arrays.sort(nums); // Sort numbers from low to high to move pointers later.
    final List<List<Integer>> res = new ArrayList<>();
    for (int i = 0; i < nums.length - 2; i++) {
      if (nums[i] > 0) { // Pruning, sum cannot be 0 since nums[i] > 0 already.
        break;
      }
      if (i > 0 && nums[i] == nums[i - 1]) { // Skip duplicates.
        continue;
      }
      // Similar to two sum, we are to find 2 numbers that sum to a target, -nums[i].
      int j = i + 1; // Starts from after i.
      int k = nums.length - 1; // Ends at the end of the array.
      while (j < k) {
        if (nums[i] + nums[j] > 0) { // Pruning. Already > 0.
          break;
        }
        if (j > i + 1 && nums[j] == nums[j - 1]) { // Skip duplicates.
          j++;
          continue;
        }

        if (nums[i] + nums[j] + nums[k] < 0) {
          j++;
        } else if (nums[i] + nums[j] + nums[k] > 0) {
          k--;
        } else { // Sum is 0.
          res.add(Arrays.asList(nums[i], nums[j], nums[k])); // In Java 9, res.add(List.of(nums[i], nums[j], nums[k]));
          j++; // Note to update pointers!
          k--;
        }
      }
    }

    return res;
  }

  /**
   * Two pointers. More concise.
   * Compare first, then move pointer to skip all duplicates.
   * Note that it compares current position with the next position.
   * So one more move is needed after that.
   */
  public List<List<Integer>> threeSum2(int[] nums) {
    final List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 2; i++) {
      if (nums[i] > 0) { // Pruning.
        break;
      }
      if (i > 0 && nums[i] == nums[i - 1]) { // Skip duplicates.
        continue;
      }
      // Two sum search.
      int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
      if (nums[i] + nums[lo] > 0) { // Pruning.
        break;
      }
      while (lo < hi) {
        if (nums[lo] + nums[hi] == sum) {
          res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
          // Skip duplicates.
          while (lo < hi && nums[lo] == nums[lo + 1]) {
            lo++;
          }
          while (lo < hi && nums[hi] == nums[hi - 1]) {
            hi--;
          }
          // Update pointers. Must happen after duplicates are all skipped.
          lo++;
          hi--;
        } else if (nums[lo] + nums[hi] < sum) {
          while (lo < hi && nums[lo] == nums[lo + 1]) { // Move lo to next different number.
            lo++;
          }
          lo++;
        } else {
          while (lo < hi && nums[hi] == nums[hi - 1]) { // Move hi to previous different number.
            hi--;
          }
          hi--;
        }
      }
    }
    return res;
  }
}