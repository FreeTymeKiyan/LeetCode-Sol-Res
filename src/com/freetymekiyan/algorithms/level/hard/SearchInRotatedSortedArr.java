package com.freetymekiyan.algorithms.level.hard;

import org.junit.Assert;
import org.junit.Test;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * <p>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Company Tags: LinkedIn, Bloomberg, Uber, Facebook, Microsoft
 * Tags: Binary Search, Array
 * Similar Problems: (M) Search in Rotated Sorted Array II, (M) Find Minimum in Rotated Sorted Array
 */
public class SearchRotatedSortedArr {

    /**
     * Binary Search.
     * Find minimum value's index first.
     * Then compare target with the ending value to know which half to search.
     * If target <= nums[length - 1], start from min index, end at last.
     * If target > nums[length - 1], start from 0, end at min index.
     * Then do a regular binary search.
     */
    public int search(int[] nums, int target) {
        int minIdx = findMinIdx(nums);
        if (target == nums[minIdx]) {
            return minIdx;
        }
        int len = nums.length;
        int start = target <= nums[len - 1] ? minIdx : 0;
        int end = target <= nums[len - 1] ? len - 1 : minIdx - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Binary Search.
     * Find the minimum value's index.
     * If the number in the middle > the end, right half.
     * Else left half.
     */
    private int findMinIdx(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > nums[end]) {
                start = mid + 1; // Minimum must be in right half.
            } else {
                end = mid; // Minimum can be mid or in left half.
            }
        }
        return start;
    }

    @Test
    public void testExamples() {
        int[] nums = {5, 1, 3};
        Assert.assertEquals(0, search(nums, 5));
    }
}