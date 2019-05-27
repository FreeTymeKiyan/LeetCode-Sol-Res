package com.freetymekiyan.algorithms.level.hard;

/**
 * 312. Burst Balloons
 * <p>
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You
 * are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right]
 * coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * <p>
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * <p>
 * Note:
 * <p>
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 * <p>
 * Input: [3,1,5,8]
 * Output: 167
 * | Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * |              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * <p>
 * Companies: Samsung, Amazon, Google, Alibaba, Snapchat
 * <p>
 * Related Topics: Divide and Conquer, Dynamic Programming
 * <p>
 * Similar Questions: (H) Minimum Cost to Merge Stones
 */
public class BurstBalloons {

  public int maxCoins(int[] iNums) {
    int[] nums = new int[iNums.length + 2];
    int n = 1;
    for (int x : iNums) if (x > 0) nums[n++] = x;
    nums[0] = nums[n++] = 1;


    int[][] memo = new int[n][n];
    return burst(memo, nums, 0, n - 1);
  }

  public int burst(int[][] memo, int[] nums, int left, int right) {
    if (left + 1 == right) return 0;
    if (memo[left][right] > 0) return memo[left][right];
    int ans = 0;
    for (int i = left + 1; i < right; ++i)
      ans = Math.max(ans, nums[left] * nums[i] * nums[right]
          + burst(memo, nums, left, i) + burst(memo, nums, i, right));
    memo[left][right] = ans;
    return ans;
  }

  public int maxCoins2(int[] iNums) {
    int[] nums = new int[iNums.length + 2];
    int n = 1;
    for (int x : iNums) if (x > 0) nums[n++] = x;
    nums[0] = nums[n++] = 1;


    int[][] dp = new int[n][n];
    for (int k = 2; k < n; ++k)
      for (int left = 0; left < n - k; ++left) {
        int right = left + k;
        for (int i = left + 1; i < right; ++i)
          dp[left][right] = Math.max(dp[left][right],
              nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
      }

    return dp[0][n - 1];
  }
}