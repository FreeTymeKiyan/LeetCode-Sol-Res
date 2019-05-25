package com.freetymekiyan.algorithms.level.medium;

/**
 * 260. Single Number III
 * <p>
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear
 * exactly twice. Find the two elements that appear only once.
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 * Note:
 * <p>
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 * <p>
 * Related Topics: Bit Manipulation
 * <p>
 * Similar Questions: Single Number (E), Single Number II (M)
 */
public class SingleNumber3 {

  public int[] singleNumber(int[] nums) {
    if (nums.length < 3) return nums;
    int xor = 0;
    for (int i = 0; i < nums.length; i++) {
      xor ^= nums[i];
    }

    xor &= -xor;

    int val1 = 0;
    int val2 = 0;
    for (int i = 0; i < nums.length; i++) {
      if ((nums[i] & xor) != 0) val1 ^= nums[i];
      else val2 ^= nums[i];
    }
    return new int[]{val1, val2};
  }
}