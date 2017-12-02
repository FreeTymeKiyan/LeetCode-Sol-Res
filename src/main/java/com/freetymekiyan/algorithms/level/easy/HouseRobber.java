package com.freetymekiyan.algorithms.level.easy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
 * stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 * <p>
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
 * money you can rob tonight without alerting the police.
 * <p>
 * Company Tags: LinkedIn, Airbnb
 * Tags: Dynamic Programming
 * Similar Problems: (M) Maximum Product Subarray, (M) House Robber II, (M) Paint House, (E) Paint Fence, (M) House
 * Robber III
 */
public class HouseRobber {

    private HouseRobber hr;

    /**
     * DP. Space Optimized.
     * Max amount of house n is either rob the previous house or rob this house.
     * Recurrence relation:
     * max[n] = max(max[n - 2] + nums[n], max[n - 1])
     * Optimization:
     * Use constant variables instead of an array.
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int preMax = nums[0];
        int max = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = preMax;
            preMax = max;
            max = Math.max(temp + nums[i], preMax);
        }
        return max;
    }

    /**
     * DP. More compact code.
     */
    public int robB(int[] nums) {
        int preMax = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int curMax = Math.max(preMax + nums[i], max);
            preMax = max;
            max = curMax;
        }
        return max;
    }

    @Before
    public void setUp() {
        hr = new HouseRobber();
    }

    @Test
    public void testInvalidInput() {
        Assert.assertEquals(0, hr.rob(null));
    }

    @Test
    public void testEdgeCases() {
        int[] input = {};
        Assert.assertEquals(0, hr.rob(input));
        input = new int[]{1};
        Assert.assertEquals(1, hr.rob(input));
        // 10 -> 10
        input = new int[]{10};
        Assert.assertEquals(10, hr.rob(input));
        // 1, 2 -> 2
        input = new int[]{1, 2};
        Assert.assertEquals(2, hr.rob(input));
        // 2, 1 -> 2
        input = new int[]{2, 1};
        Assert.assertEquals(2, hr.rob(input));
    }

    @Test
    public void testWithExamples() {
        // 1, 2, 3 -> 4
        int[] input = {1, 2, 3};
        Assert.assertEquals(4, hr.rob(input));
        // 1, 2, 3, 4 -> 6
        input = new int[]{1, 2, 3, 4};
        Assert.assertEquals(6, hr.rob(input));
        // 2, 8, 3, 6, 5 -> 14
        input = new int[]{2, 8, 3, 6, 5};
        Assert.assertEquals(14, hr.rob(input));
    }

    @After
    public void tearDown() {
        hr = null;
    }
}
