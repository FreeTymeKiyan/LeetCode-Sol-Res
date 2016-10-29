package com.freetymekiyan.algorithms.level.medium;

import org.junit.Test;

/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * <p>
 * Company Tags: LinkedIn
 * Tags: Binary Search, Array
 * Similar Problems: (E) First Bad Version
 */
public class SearchForARange {

    /**
     * Binary Search.
     * If search for start, round middle value down:
     * | If nums[mid] < target, l = mid + 1.
     * | If nums[mid] = target, r = mid, since start can be mid or further left.
     * | If nums[mid] > target, r = mid.
     * If search for end, round middle value up:
     * | If nums[mid] < target, l = mid.
     * | If nums[mid] = target, l = mid, since end can be mid or further right.
     * | If nums[mid] > target, r = mid - 1.
     */
    public int[] searchRange(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2; // Round down.
            if (nums[mid] >= target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        if (nums[lo] != target) { // Failed search, no lower bound.
            return new int[]{-1, -1};
        }
        int start = lo; // Save the lower bound. Otherwise it would be changed later.
        hi = nums.length - 1; // Reset upper bound.
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2; // Round up instead. Otherwise will stuck in loop.
            if (nums[mid] > target) {
                hi = mid - 1;
            } else {
                lo = mid;
            }
        }
        if (nums[hi] != target) { // Failed search, no upper bound.
            return new int[]{-1, -1};
        }
        return new int[]{start, hi};
    }

    @Test
    public void testExamples() {
        SearchForARange s = new SearchForARange();
        int[] A = {1, 2, 3, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] range = s.searchRange(A, 3);
        System.out.println(range[0] + " ~ " + range[1]);
    }
}
