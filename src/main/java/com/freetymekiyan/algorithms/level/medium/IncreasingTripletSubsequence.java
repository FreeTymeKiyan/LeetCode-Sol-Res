package com.freetymekiyan.algorithms.level.medium;

/**
 * 334. Increasing Triplet Subsequence
 * <p>
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * <p>
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * <p>
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 * <p>
 * Given [5, 4, 3, 2, 1],
 * return false.
 * <p>
 * Company Tags: Facebook
 * Similar Problems: (M) Longest Increasing Subsequence
 */
public class IncreasingTripletSubsequence {

    /**
     * DP.
     * Similar to find two minimum values.
     * The only difference is we don't update second min until first min is found.
     * Otherwise sec min can be before first min, which is wrong.
     * So for each number n in nums:
     * | If n <= min, update min.
     * | Else if n <= second min, update second min, that is the minimum after min.
     * | If any number > second min, we know there is an increasing triplet.
     * Return false if no triplet is found.
     * Think it like we are filling three boxes.
     * The first box is the minimum.
     * When the first box is filled, try to fill the second box.
     * If both are filled, when third box is filled, return true.
     */
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE;
        int secMin = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= min) { // Why <= ? Make sure arr[i] < arr[j] < arr[k].
                min = n;
            } else if (n <= secMin) { // min < n <= secMin.
                secMin = n;
            } else { // secMin < n.
                return true;
            }
        }
        return false;
    }

    /**
     * DP.
     * An easier to understand version.
     * First quick verify the input array.
     * Then initialize according to the first 2 values.
     * Then start iterating from the 3rd value and on:
     * | If second minimum is not initialized, initialize it.
     * | If second minimum is already initialized:
     * |   If current number is > second minimum, we found the increasing triplet!
     * |   If not, then it is <= second minimum, if it's > min, update second minimum.
     * |   If not, then it is < min, update minimum.
     */
    public boolean increasingTriplet2(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        int min = Integer.MAX_VALUE;
        int secMin = Integer.MAX_VALUE;
        if (nums[0] < nums[1]) {
            min = nums[0];
            secMin = nums[1];
        } else {
            min = nums[1];
        }
        for (int i = 2; i < nums.length; i++) {
            if (secMin == Integer.MAX_VALUE) {
                if (nums[i] > min) secMin = nums[i];
                else min = nums[i];
            } else if (nums[i] > secMin) {
                return true;
            } else if (nums[i] > min) {
                secMin = nums[i];
            } else {
                min = nums[i];
            }
        }
        return false;
    }
}