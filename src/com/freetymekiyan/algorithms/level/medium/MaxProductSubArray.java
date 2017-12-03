package com.freetymekiyan.algorithms.level.medium;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * <p>
 * For example, given the array [2,3,-2,4],
 * the contiguous subarray [2,3] has the largest product = 6.
 * <p>
 * Company Tags: LinkedIn
 * Tags: Array, Dynamic Programming
 * Similar Problems: (M) Maximum Subarray, (E) House Robber, (M) Product of Array Except Self
 */
public class MaxProductSubArray {

    /**
     * DP. Bottom-up.
     * We need to track both maximum product and minimum product.
     * In case a minimum product multiply by a negative number and become maximum.
     * f(k): Largest product subarray, from index 0 up to k.
     * g(k): Smallest product subarray, from index 0 up to k.
     * Similar to max sum subarray, the max product candidates are:
     * 1. Build on previous subarray max product: f(k-1) * A[k]
     * 2. Build on previous subarray min product: g(k-1) * A[k]
     * 3. Start from right here: A[k]
     * Recurrence Relation:
     * f(k) = max( f(k-1) * A[k], A[k], g(k-1) * A[k] )
     * g(k) = min( g(k-1) * A[k], A[k], f(k-1) * A[k] )
     * Base:
     * Initialize max, min and final result as the first number.
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int max = nums[0], min = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int preMax = max, preMin = min; // IMPORTANT: max and min need tp be stored.
            max = Math.max(Math.max(nums[i], preMax * nums[i]), preMin * nums[i]);
            min = Math.min(Math.min(nums[i], preMax * nums[i]), preMin * nums[i]);
            res = Math.max(max, res);
        }
        return res;
    }
}
