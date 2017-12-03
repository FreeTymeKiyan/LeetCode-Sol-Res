package com.freetymekiyan.algorithms.level.easy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
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

    private TwoSum t;

    /**
     * Hash Table. One-pass. O(n) Time. O(n) Space.
     * Build a mapping between number and its index.
     * For each number n in the array:
     * | Check if target - n exists in map:
     * |   If exists, return the indices.
     * | Put n and its index in map.
     * | Return not found.
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> locs = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (locs.containsKey(target - nums[i])) {
                return new int[]{locs.get(target - nums[i]), i};
            }
            locs.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    @Before
    public void setUp() {
        t = new TwoSum();
    }

    @Test
    public void testExamples() {
        int[] numbers = {3, 2, 4}; // 6 = 3 + 3
        int target = 6;
        int[] res = t.twoSum(numbers, target);
        Assert.assertArrayEquals(new int[]{1, 2}, res);

        numbers = new int[]{2, 7, 11, 15};
        target = 9;
        res = t.twoSum(numbers, target);
        Assert.assertArrayEquals(new int[]{0, 1}, res);
    }

    @After
    public void tearDown() {
        t = null;
    }
}