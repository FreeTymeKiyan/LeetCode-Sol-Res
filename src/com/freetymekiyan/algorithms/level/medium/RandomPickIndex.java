package com.freetymekiyan.algorithms.level.medium;

import java.util.Random;

/**
 * Given an array of integers with possible duplicates, randomly output the index of a given target number. You can
 * assume that the given target number must exist in the array.
 * <p>
 * Note:
 * The array size can be very large. Solution that uses too much extra space will not pass the judge.
 * <p>
 * Example:
 * <p>
 * int[] nums = new int[] {1,2,3,3,3};
 * Solution solution = new Solution(nums);
 * <p>
 * // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 * solution.pick(3);
 * <p>
 * // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 * solution.pick(1);
 * <p>
 * Tags: Reservoir Sampling
 * Similar Problems: (M) Linked List Random Node
 */
public class RandomPickIndex {

    /**
     * Your Solution object will be instantiated and called as such:
     * Solution obj = new Solution(nums);
     * int param_1 = obj.pick(target);
     */
    class Solution {

        private final int[] nums;
        private final Random r;

        public Solution(int[] nums) {
            this.nums = nums;
            r = new Random();
        }

        /**
         * Reservoir Sampling.
         * Keep a count for the target number.
         * Replace index with 1 / count probability.
         */
        public int pick(int target) {
            int count = 0;
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    count++;
                    if (r.nextInt(count) == 0) {
                        index = i;
                    }
                }
            }
            return index;
        }
    }
}

