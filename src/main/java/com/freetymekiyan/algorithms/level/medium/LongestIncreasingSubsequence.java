package com.freetymekiyan.algorithms.level.medium;

import java.util.Arrays;

/**
 * 300. Longest Increasing Subsequence
 * <p>
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * <p>
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one
 * LIS combination, it is only necessary for you to return the length.
 * <p>
 * Your algorithm should run in O(n^2) complexity.
 * <p>
 * Follow up: Could you improve it to O(nlogn) time complexity?
 * <p>
 * Tags: Dynamic Programming, Binary Search
 * Similar Problems: (M) Increasing Triplet Subsequence, (H) Russian Doll Envelopes
 */
public class LongestIncreasingSubsequence {

    /**
     * DP. O(nlogn) time. O(n) space.
     * Store the tail of a LIS at a specific length.
     * dp[0] means the tail value of array at length 1.
     * Current number can either replace an existing tail value, or expand the whole LIS because it's the largest.
     * Binary search for the insertion point of current number.
     * Update the number at the position.
     * If the insertion point equal to the current size, it means the number is the largest.
     * Increment the length of LIS by 1.
     */
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int len = 0;
        for (int n : nums) {
            int l = 0; // Binary search for the insertion position.
            int r = len;
            while (l < r) {
                int m = l + (r - l) / 2;
                if (tails[m] < n) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            tails[l] = n; // Update the tail value. l is the insertion position.
            if (l == len) { // n is the largest.
                len++;
            }
        }
        return len;
    }

    /**
     * DP. O(n^2) time, O(n) space.
     * We can store the longest increasing subseqence lengths of [0, i-1].
     * Now at index i, if nums[i] is larger than nums[x], it can form a longer LIS.
     * So dp[i] = max(dp[x] + 1), x in [0, i-1].
     * If nums[i] is smaller or equal to nums[x], then nums[i] cannot form a longer LIS.
     * So dp[i] = 1.
     * The longest is the maximum of the dp array.
     */
    public int lengthOfLIS2(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = nums.length > 0 ? 1 : 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}