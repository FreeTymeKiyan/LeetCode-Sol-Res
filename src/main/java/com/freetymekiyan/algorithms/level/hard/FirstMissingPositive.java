package com.freetymekiyan.algorithms.level.hard;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 * <p>
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * <p>
 * Your algorithm should run in O(n) time and uses constant space.
 * <p>
 * Tags: Array
 */
class FirstMissingPositive {
  public static void main(String[] args) {
    int[] A = {1, 2, 0};
    System.out.println(new FirstMissingPositive().firstMissingPositive(A));
  }

  /**
   * Position of integer n should be n - 1 if sorted
   * Correct form [1, 2, 3, 4, ..., #, n]
   * If not in position swap it with nums[nums[p]-1]
   */
  public int firstMissingPositive(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 1;
    }
    int length = nums.length;
    for (int i = 0; i < length; i++) {
      int num = nums[i];
      while (nums[i] <= length && nums[i] > 0 && nums[num - 1] != num) {
        nums[i] = nums[num - 1];
        nums[num - 1] = num;
        num = nums[i];
      }
    }
    for (int i = 0; i < length; i++) {
      if (nums[i] != i + 1) {
        return i + 1;
      }
    }
    return length + 1; // Nothing in middle missing, return largest
  }
}