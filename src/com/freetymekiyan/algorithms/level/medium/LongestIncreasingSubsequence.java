package com.freetymekiyan.algorithms.level.medium;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one
 * LIS combination, it is only necessary for you to return the length.
 * <p>
 * Your algorithm should run in O(n2) complexity.
 * <p>
 * Follow up: Could you improve it to O(n log n) time complexity?
 * <p>
 * Tags: Dynamic Programming, Binary Search
 * Similar Problems: (M) Increasing Triplet Subsequence, (H) Russian Doll Envelopes
 */
public class LongestIncreasingSubsequence {

    /**
     * DP.
     * Create an array to store the minimum value of a subsequence's maximum at a specific length.
     * How to get the value? Use binary search.
     * Search for the insertion point of current number.
     * Update the number.
     * If the insertion point equal to the current size, it means the array can be extended.
     * Then the length of Longest Increasing Subsequence can increase by 1.
     */
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int l = 0; // Binary search for the insertion point
            int r = size;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (tails[m] < x) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            tails[l] = x; // Update tails, won't break its increasing trend
            if (l == size) { // x is larger than all elements
                size++;
            }
        }
        return size;
    }
}
