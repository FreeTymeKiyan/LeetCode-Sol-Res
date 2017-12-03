package com.freetymekiyan.algorithms.level.medium;

/**
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * <p>
 * For example,
 * Given sorted array nums = [1,1,1,2,2,3],
 * <p>
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't
 * matter what you leave beyond the new length.
 * <p>
 * Company Tags: Facebook
 * Tags: Array, Two Pointers
 */
public class RemoveDuplicatesFromSortedArr2 {

    /**
     * Two Pointers.
     * Duplicates are allowed at most twice.
     * It means that the number can be the same as the last number.
     * Instead of comparing with the last number, compare with the second last.
     * Implementation:
     * Edge case: If nums.length <= 2, already true, return nums.length.
     * For each number n in nums:
     * | If len < 2 or n > nums[len - 2]:
     * |   Set nums[len] to n. Add len by 1.
     * Return len.
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < 2 || nums[i] != nums[len - 2]) {
                nums[len++] = nums[i];
            }
        }
        return len;
    }
}