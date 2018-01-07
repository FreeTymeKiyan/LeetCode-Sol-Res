package com.freetymekiyan.algorithms.level.medium;

/**
 * 80. Remove Duplicates from Sorted Array II
 * <p>
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * <p>
 * For example,
 * Given sorted array A = [1,1,1,2,2,3],
 * <p>
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 * <p>
 * Tags: Array, Two pointers
 */
class RemoveDuplicatesFromSortedArray2 {

    /**
     * Two Pointers.
     * When there are less than 2 elements, there cannot be more than 2 duplicates. So just return length.
     * Compare current element with second last element of the new array.
     * If they are the same, there will be more than 2 duplicates, skip.
     * If they are different, copy the element to the front and increment the new array length by 1.
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        int n = nums.length;
        if (n <= 2)
            return n;
        int length = 2;
        for (int i = 2; i < n; i++) {
            if (nums[i] != nums[length - 2]) { // Compare with the second last element of the new array.
                nums[length] = nums[i];
                length++;
            }
        }
        return length;
    }
}