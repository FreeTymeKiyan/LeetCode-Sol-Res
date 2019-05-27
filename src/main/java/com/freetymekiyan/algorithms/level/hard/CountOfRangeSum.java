package com.freetymekiyan.algorithms.level.hard;

import java.util.Arrays;

/**
 * 327. Count of Range Sum
 * <p>
 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
 * <p>
 * Note:
 * A naive algorithm of O(n2) is trivial. You MUST do better than that.
 * <p>
 * Example:
 * <p>
 * Input: nums = [-2,5,-1], lower = -2, upper = 2,
 * Output: 3
 * Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
 * <p>
 * Companies: Google
 * <p>
 * Related Topics: Binary Search, Divide and Conquer, Sort, Binary Indexed Tree, Segment Tree
 * <p>
 * Similar Questions: (H) Count of Smaller Numbers After Self, (H) Reverse Pairs
 */
public class CountOfRangeSum {

  public int countRangeSum(int[] nums, int lower, int upper) {
    int n = nums.length;
    long[] sums = new long[n + 1];
    for (int i = 0; i < n; ++i)
      sums[i + 1] = sums[i] + nums[i];
    return countWhileMergeSort(sums, 0, n + 1, lower, upper);
  }

  private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
    if (end - start <= 1) return 0;
    int mid = (start + end) / 2;
    int count = countWhileMergeSort(sums, start, mid, lower, upper)
        + countWhileMergeSort(sums, mid, end, lower, upper);
    int j = mid, k = mid, t = mid;
    long[] cache = new long[end - start];
    for (int i = start, r = 0; i < mid; ++i, ++r) {
      while (k < end && sums[k] - sums[i] < lower) k++;
      while (j < end && sums[j] - sums[i] <= upper) j++;
      while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++];
      cache[r] = sums[i];
      count += j - k;
    }
    System.arraycopy(cache, 0, sums, start, t - start);
    return count;
  }

  private int countRangeSum2(int[] nums, int l, int r, int lower, int upper) {
    if (l == r) return nums[l] >= lower && nums[r] <= upper ? 1 : 0;  // base case

    int m = l + (r - l) / 2;
    long[] arr = new long[r - m];  // prefix array for the second subarray
    long sum = 0;
    int count = 0;

    for (int i = m + 1; i <= r; i++) {
      sum += nums[i];
      arr[i - (m + 1)] = sum;  // compute the prefix array
    }

    Arrays.sort(arr);  // sort the prefix array

    // Here we can compute the suffix array element by element.
    // For each element in the suffix array, we compute the corresponding
    // "insertion" indices of the modified bounds in the sorted prefix array
    // then the number of valid ranges sums will be given by the indices difference.
    // I modified the bounds to be "double" to avoid duplicate elements.
    sum = 0;
    for (int i = m; i >= l; i--) {
      sum += nums[i];
      count += findIndex(arr, upper - sum + 0.5) - findIndex(arr, lower - sum - 0.5);
    }

    return countRangeSum2(nums, l, m, lower, upper) + countRangeSum2(nums, m + 1, r, lower, upper) + count;
  }

  // binary search function
  private int findIndex(long[] arr, double val) {
    int l = 0, r = arr.length - 1, m = 0;

    while (l <= r) {
      m = l + (r - l) / 2;

      if (arr[m] <= val) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }

    return l;
  }
}