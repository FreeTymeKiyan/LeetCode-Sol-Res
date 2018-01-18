package com.freetymekiyan.algorithms.level.medium;

/**
 * 153. Find Minimum in Rotated Sorted Array
 * <p>
 * Find Minimum in Rotated Sorted Array
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * Tags: Array, Binary Search
 * Similar Problems: Search in Rotated Sorted Array ,Find Minimum in Rotated Sorted Array II
 * <p>
 * Note : Recursive, Binary Search
 *
 * @author chenshuna
 */
public class FindMinimuminRotatedSortedArray {

    public static int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return binarySearch(0, nums.length - 1, nums);
    }

    public static int binarySearch(int left, int right, int[] nums) {
        if (left >= right) {
            return nums[left];
        }
        int mid = (left + right) / 2;
        if (mid >= 1 && nums[mid] < nums[mid - 1]) {
            return nums[(left + right) / 2];
        } else if (nums[mid] >= nums[left] && nums[mid] >= nums[right]) {
            return binarySearch(mid + 1, right, nums);
        } else
            return binarySearch(left, mid - 1, nums);
    }

    public static void main(String[] args) {
        int[] nums = {5, 1, 2, 3, 4};
        System.out.print(findMin(nums));
    }
}