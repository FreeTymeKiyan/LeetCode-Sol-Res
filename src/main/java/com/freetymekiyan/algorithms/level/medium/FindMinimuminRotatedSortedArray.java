package com.freetymekiyan.algorithms.level.medium;

/**
 * 153. Find Minimum in Rotated Sorted Array
 * <p>
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * <p>
 * Tags: Array, Binary Search
 */
class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) { // Stop when lo == hi.
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) { // Minimum must be in right half, excluding mid.
                lo = mid + 1;
            } else { // Minimum must be in left half, including mid.
                hi = mid;
            }
        }
        return nums[lo];
    }
}