package com.freetymekiyan.algorithms.level.medium;

/**
 * 494. Target Sum
 * <p>
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For
 * each integer, you should choose one from + and - as its new symbol.
 * <p>
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 * <p>
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * Note:
 * The length of the given array is positive and will not exceed 20.
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 * <p>
 * Related Topics: Dynamic Programming, Depth-first Search
 * Similar Questions: (H) Expression Add Operators
 */
public class TargetSum {

    private int count;

    /**
     * DFS.
     * One number can either be + / -.
     * Traverse all possibilities to the end of the array and get the sum.
     * Compare the sum with S. If sum == s, count++.
     * return count.
     * Can be optimized with memoization.
     */
    public int findTargetSumWays(int[] nums, int S) {
        dfs(nums, 0, S, 0);
        return count;
    }

    private void dfs(int[] nums, int i, int S, int sum) {
        if (i == nums.length) {
            if (sum == S) {
                count++;
            }
            return;
        }

        dfs(nums, i + 1, S, sum + nums[i]);
        dfs(nums, i + 1, S, sum - nums[i]);
    }


    /**
     * DP, Bottom up.
     * State:
     * Number of ways that can reach a sum j at index i, dp[i][j].
     * Base case:
     * Sum 0 has 1 way to reach, dp[0][0] = 1.
     * Recurrence Relation:
     * | dp[i][sum] = dp[i-1][sum+nums[i]] + dp[i-1][sum-nums[i]]
     * | Or:
     * | dp[i][sum+nums[i]] += dp[i-1][sum]
     * | dp[i][sum-nums[i]] += dp[i-1][sum]
     * Optimization:
     * To 1D array.
     */
    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0; // int sum = Arrays.stream(nums).sum();
        for (int num : nums) {
            sum += num;
        }
        if (S < -sum || S > sum) return 0; // S is out of range.
        int[] sumToCount = new int[2 * sum + 1];
        sumToCount[sum] = 1;
        for (int i = 0; i < nums.length; i++) {
            int[] next = new int[sumToCount.length];
            for (int j = 0; j < sumToCount.length; j++) {
                if (sumToCount[j] > 0) {
                    next[j + nums[i]] += sumToCount[j];
                    next[j - nums[i]] += sumToCount[j];
                }
            }
            sumToCount = next;
        }
        return sumToCount[S + sum];
    }
}
