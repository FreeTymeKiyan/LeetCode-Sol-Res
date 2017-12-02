package com.freetymekiyan.algorithms.level.medium;

import java.util.Random;

/**
 * Shuffle a set of numbers without duplicates.
 * <p>
 * Example:
 * <p>
 * // Init an array with set 1, 2, and 3.
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * <p>
 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 * solution.shuffle();
 * <p>
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 * <p>
 * // Returns the r shuffling of array [1,2,3].
 * solution.shuffle();
 */
public class ShuffleAnArray {

    class Solution {

        private final Random r;
        private int[] nums;

        public Solution(int[] nums) {
            this.nums = nums;
            r = new Random();
        }

        /**
         * Resets the array to its original configuration and return it.
         */
        public int[] reset() {
            return nums;
        }

        /**
         * Returns a r shuffling of the array.
         * Proof of probability:
         * Assume that all numbers from 0 to i have same probability of 1 / (1 + i).
         * If j = i, numbers won't change, so the probability  remains the same.
         * If j != i, a[j] and a[i] will swap.
         * Suppose x is the number in [0, i-1] that is going to swap with i.
         * The probability of this number shows at i is p(x at [0, i-1]) * p(i not in i)
         * (1 / i) * (1 - 1 / (i + 1)).
         */
        public int[] shuffle() {
            int[] a = nums.clone();
            for (int i = 1; i < a.length; i++) {
                int j = r.nextInt(i + 1);
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
            return a;
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
}
