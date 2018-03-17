package com.freetymekiyan.algorithms.other;

/**
 * Find second largest element from a given array
 * <p>
 * Example:
 * Input: {5, 2, 3, 4}
 * Output: 4
 * <p>
 * Tags: Array
 */
class FindSecondLargest {

    /**
     * Second largest element is smaller than or equal to max.
     * When current number is >= max, update second max and max.
     * Note the equal sign since when another max is found, second max would be the same as max.
     * When current element is < max but > second max, update second max only.
     */
    public int findSecondLargest(int[] nums) {
        if (nums == null || nums.length < 2)
            throw new IllegalArgumentException("Input array must exist and have at least 2 numbers.");
        int max = Math.max(nums[0], nums[1]);
        int sec = Math.min(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] >= max) { // Update both max and second max.
                sec = max;
                max = nums[i];
            } else if (nums[i] > sec) {
                sec = nums[i];
            }
        }
        return sec;
    }
}