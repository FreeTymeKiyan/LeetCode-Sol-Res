package com.freetymekiyan.algorithms.level.medium;

import java.util.Random;

/**
 * 398. Random Pick Index
 * <p>
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
 * Company Tags: Facebook
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
         * Keep a count for the number of target number.
         * Replace index with (1/count) probability.
         * How to prove that the number is equal distributed?
         * Suppose target is 2 and we have 3 of them at [2,3,4].
         * Index 2 can be picked only if 2 is picked and not replaced by 3 and 4:
         * | 1 * (1-1/2) * (1-1/3) = 1/3
         * Index 3 can be picked only if 3 is picked to replace 2, and 4 is not picked:
         * | 1 * 1/2 * (1-1/3) = 1/3
         * Index 4 can be picked if 4 is picked, no matter what happened to 2 and 3:
         * | 1/3
         */
        public int pick(int target) {
            int count = 0;
            int index = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) {
                    count++;
                    if (r.nextInt(count) == 0) { // 0 ~ count-1, 1 / count
                        index = i;
                    }
                }
            }
            return index;
        }
    }
}