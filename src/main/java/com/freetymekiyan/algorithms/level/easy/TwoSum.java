package com.freetymekiyan.algorithms.level.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two Sum
 * <p>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution.
 * <p>
 * Example:
 * Given nums = [2, 7, 11, 15], target = 9,
 * <p>
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 * <p>
 * UPDATE (2016/2/13):
 * The return format had been changed to zero-based indices. Please read the above updated description carefully.
 * <p>
 * Company Tags: LinkedIn, Uber, Airbnb, Facebook, Amazon, Microsoft, Apple, Yahoo, Dropbox, Bloomberg, Yelp, Adobe
 * Tags: Array, Hash Table
 * Similar Problems: (M) 3Sum, (M) 4Sum, (M) Two Sum II - Input array is sorted, (E) Two Sum III - Data structure
 * design
 */
public class TwoSum {

    /**
     * Hash Table. One-pass. O(n) Time. O(n) Space.
     * As we traverse through the array, we may save what we've traversed to check if there is a number that can add
     * with current number to the target.
     * Then we just need to know the number's index.
     * To make the search fast (not linear), we may use a map.
     * So build a mapping between number and its index.
     * For each number n in the array:
     * | Check if target - n exists in map:
     * |   If exists, return the indices.
     * | Put n and its index in map.
     * | Return not found.
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int n = target - nums[i];
            if (indices.containsKey(n)) {
                return new int[]{indices.get(n), i};
            }
            indices.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}