package com.freetymekiyan.algorithms.level.medium;

/**
 * 81. Search in Rotated Sorted Array II
 * <p>
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * <p>
 * Would this affect the run-time complexity? How and why?
 * <p>
 * Write a function to determine if a given target is in the array.
 * <p>
 * Tags: Array, Binary Search
 */
class SearchInRotatedSortedArray2 {

    /**
     * Binary Search.
     * The cases are changed when there are duplicates.
     * Before: nums[l] <= nums[m] means [l, m] is increasing.
     * After: nums[l] = nums[m] means [l, m] can be the same element.
     * Before: nums[m] <= nums[r] means [m, r] is increasing.
     * After: nums[m] = nums[r] means [m, r] can be the same element.
     * <p>
     * And if nums[l] = nums[m] = nums[r], we won't be able to know which side are the same elements.
     * Or even both sides are.
     * In this case, we can only skip 1 element at each side.
     * <p>
     * The complexity ends up linear when all elements are the same.
     */
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int l = 0;
        int h = nums.length - 1;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (nums[m] == target) return true;
            // Skip duplicates.
            if (nums[l] == nums[m] && nums[m] == nums[h]) { // [1, 3, 1, 1, 1] OR [1, 1, 1, 3, 1]
                l++;
                h--;
            } else if (nums[l] == nums[m]) {
                l = m + 1;
            } else if (nums[m] == nums[h]) {
                h = m - 1;
            } else if (nums[l] < nums[m]) { // [l, m] sorted.
                if (nums[l] <= target && target < nums[m]) {
                    h = m - 1;
                } else {
                    l = m + 1;
                }
            } else if (nums[l] > nums[m]) { // [m, r] sorted.
                if (nums[m] < target && target <= nums[h]) {
                    l = m + 1;
                } else {
                    h = m - 1;
                }
            }
        }
        return false;
    }
}