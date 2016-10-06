package com.freetymekiyan.algorithms.level.medium;

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
     * Hash Table. O(n) time, O(n) space.
     * This method is an optimization from the brute-force method,
     * which we search for the other target with a second loop.
     * We put number as key , index as value.
     * Search new target in map, and make sure it is not the same number.
     * So check the indices of current position and what we get from the map.
     * If there is no match, just return null.
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int newTarget = target - nums[i];
            if (map.containsKey(newTarget) && i != map.get(newTarget)) {
                return new int[]{i, map.get(newTarget)};
            }
        }
        return null;
    }

    /**
     * Hash Table. One loop.
     * Just put numbers into the map as looping through.
     * So we can search numbers already looped in O(1).
     * Note that if we find an answer, we current index will be larger than the index in map.
     * So put it behind.
     */
    public int[] twoSumB(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int newTarget = target - nums[i];
            if (map.containsKey(newTarget) && i != map.get(newTarget)) {
                return new int[]{map.get(newTarget), i};
            }
            map.put(nums[i], i);
        }
        return null;
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