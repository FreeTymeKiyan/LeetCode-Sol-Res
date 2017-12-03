package com.freetymekiyan.algorithms.level.easy;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new
 * length.
 * <p>
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * <p>
 * For example,
 * Given input array nums = [1,1,2],
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't
 * matter what you leave beyond the new length.
 * <p>
 * Company Tags: Microsoft, Bloomberg, Facebook
 * Tags: Array, Two Pointers
 * Similar Problems: (E) Remove Element
 */
public class RemoveDuplicatesFromSortedArray {

    /**
     * Two Pointers.
     * Use the length to be returned as a pointer to the next position to be filled.
     * For each number n in nums:
     * | If length is 0 or n > nums[length - 1]:
     * |   Update nums[length] to n. Increment length by 1.
     * Return length.
     */
    public static int removeDupB(int[] nums) {
        int len = 0;
        for (int n : nums) {
            if (len < 1 || n > nums[len - 1]) {
                nums[len++] = n;
            }
        }
        return len;
    }
}
