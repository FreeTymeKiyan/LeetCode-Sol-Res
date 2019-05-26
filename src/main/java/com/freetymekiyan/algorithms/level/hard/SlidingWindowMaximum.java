package com.freetymekiyan.algorithms.level.hard;

/**
 * 239. Sliding Window Maximum
 * <p>
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very
 * right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Return
 * the max sliding window.
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * <p>
 * | Window position                Max
 * | ---------------               -----
 * | [1  3  -1] -3  5  3  6  7       3
 * |  1 [3  -1  -3] 5  3  6  7       3
 * |  1  3 [-1  -3  5] 3  6  7       5
 * |  1  3  -1 [-3  5  3] 6  7       5
 * |  1  3  -1  -3 [5  3  6] 7       6
 * |  1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
 * <p>
 * Follow up:
 * Could you solve it in linear time?
 * <p>
 * Companies: Amazon, Uber, Microsoft, Google, Facebook, Bloomberg, Pinterest, Indeed, Cousera, Yelp, Alibaba, Apple,
 * Adobe, Citadel, Nutanix, Lyft, eBay, ServiceNow, Hulu, Snapchat, Rubrik, Zenefits
 * <p>
 * Related Topics: Heap, Sliding Window
 * <p>
 * Similar Questions: (H) Minimum Window Substring, (E) Min Stack, (H) Longest Substring with At Most Two Distinct
 * Characters, (H) Paint House II
 */
public class SlidingWindowMaximum {

  public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    if (n * k == 0) return new int[0];
    if (k == 1) return nums;

    int[] left = new int[n];
    left[0] = nums[0];
    int[] right = new int[n];
    right[n - 1] = nums[n - 1];
    for (int i = 1; i < n; i++) {
      // from left to right
      if (i % k == 0) left[i] = nums[i];  // block_start
      else left[i] = Math.max(left[i - 1], nums[i]);

      // from right to left
      int j = n - i - 1;
      if ((j + 1) % k == 0) right[j] = nums[j];  // block_end
      else right[j] = Math.max(right[j + 1], nums[j]);
    }

    int[] output = new int[n - k + 1];
    for (int i = 0; i < n - k + 1; i++)
      output[i] = Math.max(left[i + k - 1], right[i]);

    return output;
  }

  public int[] maxSlidingWindow2(int[] nums, int k) {
    int n = nums.length;
    if (n * k == 0) return new int[0];

    int[] output = new int[n - k + 1];
    for (int i = 0; i < n - k + 1; i++) {
      int max = Integer.MIN_VALUE;
      for (int j = i; j < i + k; j++)
        max = Math.max(max, nums[j]);
      output[i] = max;
    }
    return output;
  }
}