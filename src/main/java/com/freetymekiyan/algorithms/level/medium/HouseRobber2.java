package com.freetymekiyan.algorithms.level.medium;

/**
 * 213. House Robber II
 * <p>
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
 * stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last
 * one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two
 * adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
 * money you can rob tonight without alerting the police.
 * <p>
 * Example 1:
 * <p>
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
 * because they are adjacent houses.
 * Example 2:
 * <p>
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * <p>
 * Companies: Google, Salesforce, Adobe, Microsoft
 * <p>
 * Related Topics: Dynamic Programming
 * <p>
 * Similar Questions: (E) House Robber, (E) Paint House, (E) Paint Fence, (M) House Robber III, (H) Non-negative
 * Integers without Consecutive Ones, (H) Coin Path
 */
public class HouseRobber2 {

  public int rob(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];
    if (nums.length == 2) return Math.max(nums[0], nums[1]);
    return Math.max(robRange(nums, 0, nums.length - 2), robRange(nums, 1, nums.length - 1));
  }

  private int robRange(int[] nums, int start, int end) {
    int include = 0, exclude = 0;
    for (int j = start; j <= end; j++) {
      int i = include, e = exclude;
      include = e + nums[j];
      exclude = Math.max(e, i);
    }
    return Math.max(include, exclude);
  }
}