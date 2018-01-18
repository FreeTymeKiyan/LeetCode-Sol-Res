package com.freetymekiyan.algorithms.level.hard;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 * <p>
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * <p>
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * Find the minimum element.
 * The array may contain duplicates.
 * <p>
 * Tags: Array, Binary Search
 */
class FindMinimumInRotatedSortedArray2 {

    /**
     * Binary Search.
     * Compare nums[mid] with nums[hi].
     * When nums[mid] = nums[hi], we are not sure which side contains the min.
     * So we just shrink the solution space by 1.
     * Since nums[mid] is still there, this is fine.
     * The complexity becomes O(n) in worst case.
     */
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else if (nums[mid] < nums[hi]) {
                hi = mid;
            } else {
                hi--;
            }
        }
        return nums[lo];
    }
}