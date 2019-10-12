package com.freetymekiyan.algorithms.level.hard;

/**
 * 410. Split Array Largest Sum
 * <p>
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty
 * continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
 * <p>
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 * <p>
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 * Examples:
 * <p>
 * Input:
 * nums = [7,2,5,10,8]
 * m = 2
 * <p>
 * Output:
 * 18
 * <p>
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 * <p>
 * Company Tags: Baidu, Facebook
 * <p>
 * Related Topics: Binary Search, Dynamic Programming
 */
public class SplitArrayLargestSum {

/**
 * Binary Search.
 * This idea starts from the result.
 * Notice that the output can only be in a range:
 * | The max is the sum of all the numbers in the input array.
 * | The min is the maximum of the input numbers.
 * Now, if we propose an output, and try to divide the array, it may or may not fit into m subarrays.
 * If it fits, we further propose a smaller output.
 * If it doesn't, we further propose a bigger output.
 * In the end, we will get to an output that can generate m arrays and cannot be smaller.
 */
public int splitArray(int[] nums, int m) {
  // Generate possible output range
  long sum = 0; // Handle possible overflow of integer
  int max = 0;
  for (int n : nums) {
    sum += n;
    max = Math.max(n, max);
  }
  // Binary search the minimum output
  long lo = max;
  long hi = sum;
  while (lo < hi) {
    long mid = lo + (hi - lo) / 2;
    if (validate(nums, m, mid)) {
      hi = mid;
    } else {
      lo = mid + 1;
    }
  }
  return (int) lo; // Convert long to int.
}

/**
 * Validate given the max sum whether we can generate <= m subarrays.
 * If it needs more than m, return false. Otherwise return true.
 * <p>
 * Use a long sum for current subarray sum to avoid overflow.
 * Use an integer for the # of subarrays.
 * Initialized as 1 since there's at least 1 subarray.
 * For each number n in nums:
 * | Add n to current sum.
 * | If sum > max sum, current subarray sum exceeds the limit:
 * |   reset sum to n as the subarray sum of the next subarray.
 * |   subarray count increments by 1.
 * |   If count > m, we need more than m subarrays, return false.
 * Return true.
 */
private boolean validate(int[] nums, int m, long maxSum) {
  long subarraySum = 0; // Current subarray sum
  int numSubarray = 1; // IMPORTANT! Already have 1 array even without cutting
  for (int n : nums) {
    subarraySum += n;
    if (subarraySum > maxSum) { // Cannot be >=. Equal is still valid and does NOT need one more cut
      subarraySum = n; // Number n is the first element of the next subarray
      numSubarray++;
      // Early exit since we got more than m subarrays
      // Or after count is fully generated,
      // check at return if count <= m
      if (numSubarray > m) {
        return false;
      }
    }
  }
  return true;
}

}