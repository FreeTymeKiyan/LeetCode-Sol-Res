package com.freetymekiyan.algorithms.level.easy;

/**
 * 283. Move Zeroes
 * <p>
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the
 * non-zero elements.
 * <p>
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * <p>
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 * <p>
 * Company Tags: Bloomberg, Facebook
 * Tags: Array, Two Pointers
 * Similar Problems: (E) Remove Elements
 */
public class MoveZeroes {

    /**
     * Two Pointers. One-pass.
     * Track the end of non-zero elements with a pointer.
     * Swap non-zero elements to the front.
     * For each number n in the array:
     * | If n != 0:
     * |  Swap n nums[cur]
     * |  cur -> cur+1
     * If current number is not zero, swap it to the front.
     * Where we track the swap position with another pointer.
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int end = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                int temp = nums[end];
                nums[end++] = nums[i];
                nums[i] = temp;
            }
        }
    }

    /**
     * Two pointers.
     * One pointer goes through the array and find positive numbers.
     * The other tracks the next position to be filled up.
     * Move all positive numbers to the front of the array.
     * Then add trailing zeroes after.
     */
    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int count = 0;
        for (int n : nums) {
            if (n != 0) {
                nums[count] = n;
                count++;
            }
        }
        for (; count < nums.length; count++) {
            nums[count] = 0;
        }
    }
}