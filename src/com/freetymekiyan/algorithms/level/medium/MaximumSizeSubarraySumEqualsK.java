package com.freetymekiyan.algorithms.level.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one,
 * return 0 instead.
 * <p>
 * Note:
 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
 * <p>
 * Example 1:
 * Given nums = [1, -1, 5, -2, 3], k = 3,
 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
 * <p>
 * Example 2:
 * Given nums = [-2, -1, 2, 1], k = 1,
 * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
 * <p>
 * Follow Up:
 * Can you do it in O(n) time?
 * <p>
 * Company Tags: Palantir, Facebook
 * Tags: Hash Table
 * Similar Problems: (M) Minimum Size Subarray Sum, (E) Range Sum Query - Immutable
 */
public class MaximumSizeSubarraySum {

    /**
     * For 0 <= i < j < nums.length,
     * Find max(i, j) for sum[j] - sum[i-1] = k.
     * Except when i = 0.
     * The brute-force way is we loop through the array, keep updating sum.
     * Then subtract with each previous sum to see if it's k.
     * But previous sum is already calculated, and we can use a map to get it in O(1).
     */
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int sum = 0;
        map.put(0, -1); // Sum is 0 for index -1. Make sure sum[j] - sum[i-1] always works.
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res = Math.max(res, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) { // Keep only the smallest i
                map.put(sum, i);
            }
        }
        return res;
    }

}
