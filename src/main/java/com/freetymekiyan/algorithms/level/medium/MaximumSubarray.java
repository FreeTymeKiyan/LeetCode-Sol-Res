package com.freetymekiyan.algorithms.level.medium;

/**
 * 53. Maximum Subarray
 * <p>
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * <p>
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 * the contiguous subarray [4,-1,2,1] has the largest sum = 6.
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
     * DP. O(n) Time, O(1) Space.
     * State: the max subarray sum ending at i.
     * Recurrence Relation:
     * Since the sub array is contiguous, nums[i] must be included.
     * So the max subarray sum ending at i is either nums[i] or nums[i] + max sum ending at i-1, whichever is larger.
     * The overall maximum is the max of all maxEndingHere's.
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

    /**
     * Divide and conquer.
     * Recurrence:
     * The max sub array sum of array nums can be divided into 3 parts:
     * 1. Only in left half.
     * 2. Only in right half.
     * 3. In both halves.
     * The result should be the largest of these 3.
     * Base case:
     * When there is only 1 element in the array, just return the element.
     */
    public int maxSubArray3(int[] nums) {
        return maxSubArray(nums, 0, nums.length - 1);
    }

    private int maxSubArray(int[] nums, int start, int end) {
        if (start == end) return nums[start];
        int mid = start + (end - start) / 2;
        int max1 = maxSubArray(nums, start, mid);
        int max2 = maxSubArray(nums, mid + 1, end);
        int max3 = crossMidMaxSum(nums, start, end, mid);
        return Math.max(max1, Math.max(max2, max3));
    }

    /**
     * The max subarray sum cross the mid point.
     * Must have nums[mid] since the array is continuous.
     * Find the maximum of left side, then right side.
     * The result is their sum.
     */
    private int crossMidMaxSum(int[] nums, int start, int end, int mid) {
        int leftMax = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= start; i--) {
            sum += nums[i];
            if (sum > leftMax) leftMax = sum;
        }

        int rightMax = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= end; i++) {
            sum += nums[i];
            if (sum > rightMax) rightMax = sum;
        }
        return leftMax + rightMax;
    }

}