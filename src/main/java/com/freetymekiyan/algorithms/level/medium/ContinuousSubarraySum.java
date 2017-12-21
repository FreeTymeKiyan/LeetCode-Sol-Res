package com.freetymekiyan.algorithms.level.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. Continuous Subarray Sum
 * <p>
 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has a continuous
 * subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.
 * <p>
 * Example 1:
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * Example 2:
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 * Note:
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 * <p>
 * Related Topics: Math, Dynamic Programming
 * Similar Questions: (M) Subarray Sum Equals K
 */
public class ContinuousSubarraySum {

    /**
     * Hash Table. O(n) Time, O(n) Space.
     * Continuous / Contiguous problem seems to always need to combine the array elements somehow.
     * Sum the values and mod by k.
     * Keep track of the previous mods with a map.
     * When current mods are found in the map, the numbers in between are divisible by k.
     * Just check if there are at least 2 numbers.
     * Note that mod, like division, is invalid when k is 0.
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> modToIndex = new HashMap<>();
        modToIndex.put(0, -1); // When there is no element in the array, the mod is 0.
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) sum %= k;
            if (modToIndex.containsKey(sum)) {
                if (i - modToIndex.get(sum) > 1) { // Not the same index.
                    return true;
                }
            } else {
                modToIndex.put(sum, i);
            }
        }
        return false;
    }

    /**
     * DP. O(n^2) Time, O(n) Space.
     * Keep the sum from each index to current index in an array.
     */
    public boolean checkSubarraySum2(int[] nums, int k) {
        if (nums == null || nums.length <= 1) return false;
        int[] sums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sums[i] += nums[i];
            for (int j = 0; j < i; j++) {
                sums[j] += nums[i];
                if (k == 0) {
                    if (sums[j] == 0) return true;
                } else if (sums[j] % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
