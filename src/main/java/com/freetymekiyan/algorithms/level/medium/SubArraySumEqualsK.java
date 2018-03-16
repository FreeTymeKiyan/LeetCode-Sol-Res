package com.freetymekiyan.algorithms.level.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 * <p>
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum
 * equals to k.
 * <p>
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 * <p>
 * Related Topics: Array, Hash Table
 * Similar Questions: (E) Two Sum, (M) Continuous Subarray Sum, (M) Subarray Product Less Than K, (E) Find Pivot Index
 */
public class SubArraySumEqualsK {

    /**
     * sum[0, j] - sum[0, i] = sum[i + 1, j], 0 <= i <= j < nums.length.
     * If all sums from 0 to i, 0 <= i < j, are saved, check if sum - k has shown before.
     * Store all the sums starting from 0, ending at each position in a hash table.
     * And their frequencies, since there might be duplicates.
     * Now at nums[i], first update sum to sum + nums[i].
     * Then check if k - nums[i] has been seen.
     * If so, add that many times to result.
     * If not, do nothing.
     * Update the hash table afterwards.
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> sumToCount = new HashMap<>();
        sumToCount.put(0, 1); // sum = 0 by default
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumToCount.containsKey(sum - k)) {
                count += sumToCount.get(sum - k);
            }
            sumToCount.merge(sum, 1, (oldVal, newVal) -> oldVal + newVal);
        }
        return count;
    }
}