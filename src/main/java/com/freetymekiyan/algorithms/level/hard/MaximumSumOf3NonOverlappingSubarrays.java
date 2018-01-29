package com.freetymekiyan.algorithms.level.hard;

/**
 * 689. Maximum Sum of 3 Non-Overlapping Subarrays
 * <p>
 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
 * <p>
 * Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
 * <p>
 * Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are
 * multiple answers, return the lexicographically smallest one.
 * <p>
 * Example:
 * Input: [1,2,1,2,6,7,5,1], 2
 * Output: [0, 3, 5]
 * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
 * We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
 * Note:
 * nums.length will be between 1 and 20000.
 * nums[i] will be between 1 and 65535.
 * k will be between 1 and floor(nums.length / 3).
 * <p>
 * Related Topics: Array, Dynamic Programming
 * Similar Questions: (H) Best Time to Buy and Sell Stock III
 */
public class MaximumSumOf3NonOverlappingSubarrays {

    /**
     * A: [1,2,1,2,6,7,6,1] -> B: [3,3,3,8,13,13,7]
     * B is the k-interval sum. The index of element in B is the starting index of the subarray in A.
     * It makes things easier since the subarray size is fixed.
     * The only things we care are: 1. the starting index 2. the sum.
     * The problem then becomes: For i, j, k in range, find max(B[i] + B[j] + B[k]). i + K <= j <= k - K
     * Fixed j from [K, A.length - 2K], i from [0, j-K), k from (j+K, A.length - K].
     * While iterating, need to know max k-interval sum of [0, j-k) and (j+K, A.length - K] easily.
     * Can pre-calculate them with DP.
     * One array for i from [K, A.length - 3K].
     * The other for k from [2K, A.length - K].
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        // Calculate k-interval sum.
        int[] intervalSum = new int[nums.length - k + 1]; // Last interval is [n-k, n-k-1...n-1], length is n-k-0+1.
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (i >= k) sum -= nums[i - k]; // Remove earlier numbers out from the window/interval.
            if (i >= k - 1) intervalSum[i - k + 1] = sum;
        }
        // left[i] is the index of first max k-interval sum from 0 to i.
        int[] left = new int[intervalSum.length]; // Actually, i can only be in [0, n-3*k].
        int best = 0;
        for (int i = 0; i < left.length; i++) {
            if (intervalSum[i] > intervalSum[best]) best = i;
            left[i] = best;
        }
        // right[i] is the index of first max k-interval sum from i to nums.length - 1.
        int[] right = new int[intervalSum.length]; // Actually, i can only be in [2k, n-k].
        best = intervalSum.length - 1; // Initialize as rightmost index. The end, not start.
        for (int i = intervalSum.length - 1; i >= 0; i--) {
            if (intervalSum[i] >= intervalSum[best]) best = i; // Note the equal sign to keep lexicographically first.
            right[i] = best;
        }
        int[] indices = new int[]{-1, -1, -1};
        for (int m = k; m < intervalSum.length - k; m++) {
            int l = left[m - k], r = right[m + k];
            if (indices[0] == -1 || intervalSum[l] + intervalSum[m] + intervalSum[r] >
                    intervalSum[indices[0]] + intervalSum[indices[1]] + intervalSum[indices[2]]) {
                indices[0] = l;
                indices[1] = m;
                indices[2] = r;
            }
        }
        return indices;
    }
}