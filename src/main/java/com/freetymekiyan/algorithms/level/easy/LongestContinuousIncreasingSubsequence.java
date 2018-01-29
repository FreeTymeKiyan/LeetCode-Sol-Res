package com.freetymekiyan.algorithms.level.easy;

/**
 * 674. Longest Continuous Increasing Subsequence
 * <p>
 * Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
 * <p>
 * Example 1:
 * Input: [1,3,5,4,7]
 * Output: 3
 * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
 * Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
 * Example 2:
 * Input: [2,2,2,2,2]
 * Output: 1
 * Explanation: The longest continuous increasing subsequence is [2], its length is 1.
 * Note: Length of the array will not exceed 10,000.
 * <p>
 * Related Topics: Array
 * Similar Questions: (M) Number of Longest Increasing Subsequence, (H) Minimum Window Subsequence
 */
public class LongestContinuousIncreasingSubsequence {

    /**
     * Sliding window.
     * Remember where the current sub-sequence started as start.
     * When a[i-1] >= a[i], i-start+1 is the length of current continuous increasing sub-sequence.
     * Update result if i-start+1 is larger.
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int longest = 0, start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] >= nums[i]) start = i;
            longest = Math.max(longest, i - start + 1);
        }
        return longest;
    }

    /**
     * DP.
     * Current sub-sequence length, len, is incremented when the next number is larger.
     * When len is incremented, check if longest length max should be updated too.
     * len is reset as 1 when the next number is equal or smaller.
     */
    public int findLengthOfLCIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;
        int longest = 1;
        int current = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                current++;
            } else {
                current = 1;
            }
            longest = Math.max(longest, current);
        }
        return longest;
    }
}
