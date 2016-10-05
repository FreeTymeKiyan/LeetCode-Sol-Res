package com.freetymekiyan.algorithms.level.easy;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
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
 * Similar Problems: (E) Remove Element
 */
public class MoveZeroes {

    /**
     * Two pointers.
     * One pointer goes through the array and find positive numbers.
     * The other tracks the next position to be filled up.
     * Move all positive numbers to the front of the array.
     * Then add trailing zeroes after.
     */
    public void moveZeroes(int[] nums) {
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

    /**
     * One loop.
     * Go through the array.
     * If current number is not zero, swap it to the front.
     * Where we track the swap position with another pointer.
     */
    public void moveZeroesB(int[] nums) {
        int cur = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                int temp = nums[cur];
                nums[cur++] = nums[i];
                nums[i] = temp;
            }
        }
    }

    @Test
    public void tests() {
        MoveZeroes mz = new MoveZeroes();
        int[] nums = null;
        // null -> null
        mz.moveZeroes(nums);
        assertNull(nums);
        // empty -> empty
        nums = new int[]{};
        mz.moveZeroes(nums);
        assertEquals(nums.length, 0);
        assertNotNull(nums);
        // [1] -> [1]
        nums = new int[]{1};
        mz.moveZeroes(nums);
        assertArrayEquals(nums, new int[]{1});
        // no zero, [1, 2, 3] -> [1, 2, 3]
        nums = new int[]{1, 2, 3};
        mz.moveZeroes(nums);
        assertArrayEquals(nums, new int[]{1, 2, 3});
        // all zeroes, [0, 0, 0] -> [0, 0, 0]
        nums = new int[]{0, 0, 0};
        mz.moveZeroes(nums);
        assertArrayEquals(nums, new int[]{0, 0, 0});
        // normal case, [0, 1, 0, 3, 12] -> [1, 3, 12, 0, 0]
        nums = new int[]{0, 1, 0, 3, 12};
        mz.moveZeroes(nums);
        assertArrayEquals(nums, new int[]{1, 3, 12, 0, 0});
    }
}
