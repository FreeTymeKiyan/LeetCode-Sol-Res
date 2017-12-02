package com.freetymekiyan.algorithms.level.medium;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * <p>
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * <p>
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 * <p>
 * <p>
 * Tags: Array, Math, Bit Manipulation
 * Similar Problems: (H) First Missing Positive, (M) Single Number, (H) Find the Duplicate Number
 */
public class MissingNumber {

    /**
     * Math
     * Considering that the n numbers are distinct, we can get the sum of the array
     * Then substract it from the sum of 0 ~ n
     */
    public int missingNumber1(int[] nums) {
        int res = nums.length * (nums.length + 1) / 2; // may overflow
        for (int n : nums) res -= n;
        return res;
    }

    /**
     * avoid overflow
     */
    public int missingNumber2(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += i + 1 - nums[i];
        }
        return res;
    }

    /**
     * Bit Manipulation
     * xor all numbers in the array and from 0 to n, the result is the missing number
     */
    public int missingNumber3(int[] nums) {
        int xor = 0;
        for (int i = 0; i < nums.length; i++) {
            xor = xor ^ nums[i] ^ (i + 1);
        }
        return xor;
    }
}
