package com.freetymekiyan.algorithms.level.medium;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * <p>
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 * <p>
 * click to show more practice.
 * <p>
 * More practice:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which
 * is more subtle.
 * <p>
 * Company Tags: LinkedIn, Bloomberg, Microsoft
 * Tags: Array, Dynamic Programming, Divide and Conquer
 * Similar Problems: (E) Best Time to Buy and Sell Stock, (M) Maximum Product Subarray
 */
public class MaximumSubarray {


    /**
     * DP.
     * Recurrence Relation:
     * So if we know the max sum ending at i-1. How can we get the max sum ending at i?
     * Since the sub array is contiguous. It must include nums[i].
     * So max sum ending at i is either nums[i] or nums[i] + max sum ending at i-1, whichever is larger.
     * Then we update the overall result with the new max.
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = nums[0];
        int maxEndingHere = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            max = Math.max(max, maxEndingHere);
        }
        return max;
    }

    /**
     * DP, O(n) Time, O(1) Space
     * If A[i] < 0 && currentMax + A[i] < 0, should recalculate max
     * If A[i] < 0 && currentMax + A[i] >= 0, continue
     * currentMax = max(currentMax + A[i], A[i])
     * maxSubArr = max(currentMax, maxSubArr)
     */
    public int maxSubArraySum(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int curMax = A[0];
        int max = A[0];
        for (int i = 1; i < A.length; i++) { // note that i starts from 1
            curMax = Math.max(curMax + A[i], A[i]);
            max = Math.max(curMax, max);
        }
        return max;
    }

    /**
     * DP, O(n) Time, O(n) Space
     */
    public int maxSubArraySumB(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] s = new int[A.length]; // save max sum so far in an array
        s[0] = A[0];
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            s[i] = s[i - 1] > 0 ? (A[i] + s[i - 1]) : A[i];
            max = Math.max(max, s[i]);
        }
        return max;
    }

    /**
     * Not asking sum, but the range
     * If A[i] < 0, current sum + A[i] >= 0, we can continue addition because
     * the positive sum would still contribute to positiveness of the subarray.
     * If A[i] < 0, current sum + A[i] < 0, the current subarray has to end.
     */
    public int[] maxSubArrayB(int[] A) {
        int beginTemp = 0; // save the temporary begining index
        int begin = 0; // begining index
        int end = 0; // ending index
        int maxSoFar = A[0]; // max sum of previous sequence
        int maxEndingHere = A[0]; // max sum of this group

        for (int i = 1; i < A.length; i++) {
            if (maxEndingHere < 0) { // last A[i] is too small
                maxEndingHere = A[i];
                beginTemp = i; // update begin temp
            } else {
                maxEndingHere += A[i];
            }

            if (maxEndingHere >= maxSoFar) { // update max so far
                maxSoFar = maxEndingHere;
                begin = beginTemp; // save index range
                end = i;
            }
        }
        return new int[]{begin, end, maxSoFar};
    }

}
