package com.freetymekiyan.algorithms.level.easy;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * Example:
 *
 * Given nums = [-2, 0, 3, -5, 2, -1]
 *
 * sumRange(0, 2) -> 1
 *
 * sumRange(2, 5) -> -1
 *
 * sumRange(0, 5) -> -3
 *
 * Note:
 *
 * You may assume that the array does not change.
 *
 * There are many calls to sumRange function.
 *
 * Tags: Dynamic Programming
 *
 * Similar Problems: (M) Range Sum Query 2D - Immutable, (M) Range Sum Query - Mutable, (E) Maximum Size Subarray Sum
 * Equals k
 */
public class RangeSumQueryImmutable {

    class NumArray {

        private int[] nums;

        public NumArray(int[] nums) {
            for (int i = 1; i < nums.length; i++) {
                nums[i] += nums[i - 1];
            }
            this.nums = nums;
        }

        public int sumRange(int i, int j) {
            return i == 0 ? nums[j] : nums[j] - nums[i - 1];
        }

    }

}
