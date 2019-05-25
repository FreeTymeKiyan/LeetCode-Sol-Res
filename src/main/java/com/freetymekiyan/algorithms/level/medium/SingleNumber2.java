package com.freetymekiyan.algorithms.level.medium;

/**
 * 137. Single Number II
 * <p>
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once.
 * Find that single one.
 * <p>
 * Note:
 * <p>
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * <p>
 * Example 1:
 * <p>
 * Input: [2,2,3,2]
 * Output: 3
 * Example 2:
 * <p>
 * Input: [0,1,0,1,0,1,99]
 * Output: 99
 * <p>
 * Related Topics: Bit Manipulation
 * <p>
 * Similar Questions: Single Number (E), Single Number III (M)
 */
public class SingleNumber2 {

  public int singleNumber(int[] nums) {
    int ones = 0, twos = 0;
    for (int i = 0; i < nums.length; i++) {
      ones = (ones ^ nums[i]) & ~twos;
      twos = (twos ^ nums[i]) & ~ones;
    }
    return ones;
  }

}