package com.freetymekiyan.algorithms.level.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 325. Maximum Size Subarray Sum Equals k
 * <p>
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
public class MaximumSizeSubarraySumEqualsK {

    /**
     * Hash Table.
     * For 0 <= i < j < nums.length,
     * Find max(j - i) for sum[j] - sum[i-1] = k.
     * Except when i = 0, sum[j] = k.
     * The brute-force way is to loop through the array, keep updating total sum.
     * Then subtract with each previous total sum to see if there is k.
     * But previous total sums are already calculated.
     * Then we can use a map to get them in O(1).
     * Use a hash table to store sum and its EARLIEST index.
     * Note that we don't update the index of same sum because the length would definitely be smaller.
     */
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        int maxLen = 0;
        int sum = 0;
        sumToIndex.put(0, -1); // Sum is 0 for index -1. Make sure sum[j] - sum[i-1] always works.
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumToIndex.containsKey(sum - k)) { // Search previous sums.
                maxLen = Math.max(maxLen, i - sumToIndex.get(sum - k));
            }
            if (!sumToIndex.containsKey(sum)) { // Keep only the smallest i.
                sumToIndex.put(sum, i);
            }
        }
        return maxLen;
    }

}