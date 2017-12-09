package com.freetymekiyan.algorithms.level.medium;

import java.util.Arrays;

/**
 * 673. Number of Longest Increasing Subsequence
 * <p>
 * Given an unsorted array of integers, find the number of longest increasing subsequence.
 * <p>
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1,
 * so output 5.
 * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 * <p>
 * Related Topics: Dynamic Programming
 * Similar Questions: (M) Longest Increasing Subsequence, (E) Longest Continuous Increasing Subsequence
 */
public class NumberOfLongestIncreasingSubsequence {

    /**
     * DP.
     * Two states:
     * 1. Longest increasing sub-sequence length ends with nums[i], lengths[i]
     * 2. # of longest increasing sub-sequence ends with nums[i], counts[i]
     * Relationship:
     * For i, 0 <= j < i, if nums[i] <= nums[j], nums[i] is not included in any longest increasing sub-sequence.
     * else, nums[i] > nums[j], nums[i] is included, and can be added to any longest increasing sub-sequence that ends
     * with j. lengths[i] should be lengths[j] + 1.
     * | Then if lengths[i] < lengths[j] + 1, set lengths[i] to lengths[j] + 1. counts[i] should be the same as
     * | counts[j].
     * | If lengths[i] == lengths[j] + 1 already, not need to set, add counts[j] to counts[i].
     */
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] lengths = new int[nums.length];
        int[] counts = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            lengths[i] = counts[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (lengths[i] == lengths[j] + 1) {
                        counts[i] += counts[j];
                    } else if (lengths[i] < lengths[j] + 1) {
                        lengths[i] = lengths[j] + 1;
                        counts[i] = counts[j];
                    }
                }
            }
        }

        int longest = Arrays.stream(lengths).max().getAsInt();
        int result = 0;
        for (int i = 0; i < lengths.length; i++) {
            if (lengths[i] == longest) {
                result += counts[i];
            }
        }
        return result;
    }

    /**
     * Segment Tree. TBA.
     */
    public int findNumberOfLIS2(int[] nums) {
        // TODO: 12/7/17 Learn segment tree's implementation.
        return -1;
    }
}