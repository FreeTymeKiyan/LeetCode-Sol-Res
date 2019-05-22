package com.freetymekiyan.algorithms.level.hard;

/**
 * 33. Search in Rotated Sorted Array
 * <p>
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
public class SearchInRotatedSortedArray {

  /**
   * Binary Search.
   * Compare nums[mid] with the start and end of the range so that we know which part is sorted.
   * If nums[mid] == target, return mid.
   * If nums[lo] <= nums[mid], lo to mid is increasing.
   * | If nums[lo] <= target < nums[mid], target won't be in [mid, hi].
   * |   hi = mid - 1
   * | else target won't be in [lo, mid].
   * |   lo = mid + 1
   * If nums[mid] <= nums[hi], mid to high is increasing.
   * | If nums[mid] < target <= nums[hi], target won't be in [lo, mid].
   * |   lo = mid + 1
   * | else target won't be in [mid, hi].
   * |   hi = mid - 1
   */
public int search(int[] nums, int target) {
  int lo = 0;
  int hi = nums.length - 1;
  while (lo <= hi) {
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] == target) {
      return mid;
    }

    if (nums[lo] <= nums[mid]) {
      if (target < nums[mid] && target >= nums[lo]) {
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }

    if (nums[mid] <= nums[hi]) {
      if (target > nums[mid] && target <= nums[hi]) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
  }
  return -1;
}

  /**
   * Binary Search.
   * Find minimum value's index first.
   * Then compare target with the ending value to know which half to search.
   * If target <= nums[length - 1], start from min index, end at last.
   * If target > nums[length - 1], start from 0, end at min index.
   * Then do a regular binary search.
   */
public int search2(int[] nums, int target) {
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
   * Compare the number in the middle with the number at the end.
   * If nums[mid] > nums[end], minimum in right half, excluding mid, search in [mid + 1, end].
   * If nums[mid] = nums[end], no dup, means mid = end, minimum is mid.
   * If nums[mid] < nums[end], mid to end is increasing, minimum in left half, search in [start, mid], end = mid.
   * If the number in the middle > the end, right half.
   * If the number in the middle < the end, can be middle or left half.
   */
private int findMinIdx(int[] nums) {
  int lo = 0, hi = nums.length - 1;
  while (lo < hi) { // Stop when lo == hi.
    int mid = lo + (hi - lo) / 2;
    if (nums[mid] > nums[hi]) { // Minimum must be in right half, excluding mid.
      lo = mid + 1;
    } else { // Minimum must be in left half, including mid.
      hi = mid;
    }
  }
  return lo;
}
}