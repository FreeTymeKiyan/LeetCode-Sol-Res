package com.freetymekiyan.algorithms.level.medium;

import java.util.Arrays;

/**
 * 377. Combination Sum IV
 * <p>
 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add
 * up to a positive integer target.
 * <p>
 * Example:
 * <p>
 * nums = [1, 2, 3]
 * target = 4
 * <p>
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * <p>
 * Note that different sequences are counted as different combinations.
 * Therefore the output is 7.
 * <p>
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 * <p>
 * Company Tags: Google, Snapchat, Facebook
 * Tags: Dynamic Programming
 * Similar Problems: (M) Combination Sum
 * <p>
 * Answer to follow up:
 * 1. The recurrence relation still works. But base case breaks.
 * 2. Each number is only used one time. Think about [1, -1], 1.
 */
public class CombinationSum4 {

    /**
     * DP, Bottom-up. O(n^2) Time, O(n) Space.
     * State: S[i] means the # of combinations that can reach sum i.
     * Recurrent relation:
     * S[i] = sum(S[i - nums[j]]), where 0 <= j < nums.length, and target >= nums[j].
     * Base case:
     * S[0] = 1. Think about [1], 1, where S[1] = S[1 - 1] = S[0] = 1.
     */
    public int combinationSum4(int[] nums, int target) {
        int[] comb = new int[target + 1];
        comb[0] = 1;
        for (int i = 1; i < comb.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i - nums[j] >= 0) { // Array's not sorted. Need to check each number.
                    comb[i] += comb[i - nums[j]];
                }
            }
        }
        return comb[target];
    }

    /**
     * DP, Top-down, Memoization.
     */
    public int combinationSum4TopDown(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, -1);
        return helper(nums, target, dp);
    }

    private int helper(int[] nums, int target, int[] dp) {
        if (target == 0) {
            return 1;
        }
        if (dp[target] != -1) {
            return dp[target];
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i], dp);
            }
        }
        dp[target] = res;
        return res;
    }
}