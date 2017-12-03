package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of
 * all the elements of nums except nums[i].
 * <p>
 * Solve it without division and in O(n).
 * <p>
 * For example, given [1,2,3,4], return [24,12,8,6].
 * <p>
 * Follow up:
 * Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the
 * purpose of space complexity analysis.)
 * <p>
 * Company Tags: Amazon, LinkedIn, Apple, Facebook, Microsoft
 * Tags: Array
 * Similar Problems: (H) Trapping Rain Water, (M) Maximum Product Subarray, (H) Paint House II
 */
public class ProductofArrayExceptSelf {

    private ProductofArrayExceptSelf p;

    /**
     * Array. One-pass. O(1) Space.
     * The product is actually composed of two parts, the integers on the left, and integers on the right.
     * For a naive O(n) Time, O(n) Space solution.
     * You can use two arrays to store products from the beginning and from the end.
     * Then multiply each position in the two arrays to generate result.
     * If we want to reduce space usage to O(1), we can just replace the two arrays with two integers.
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);
        int left = 1; // Product of integers before i.
        int right = 1; // Product of integers after n-1-i.
        for (int i = 0; i < nums.length; i++) {
            res[i] *= left; // Update result in the front.
            left *= nums[i]; // Update left.
            res[n - 1 - i] *= right; // Update result at the end.
            right *= nums[n - 1 - i]; // Update right.
        }
        return res;
    }

    /**
     * Array. Two-pass. O(1) Space.
     * Scan from the beginning to store the result of products of integers on the left.
     * Scan from the end to start to generate final result.
     */
    public int[] productExceptSelfB(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    @Before
    public void setUp() {
        p = new ProductofArrayExceptSelf();
    }

    @Test
    public void testExamples() {
        int[] nums = {1, 2, 3, 4};
        int[] res = {24, 12, 8, 6};
        Assert.assertArrayEquals(res, p.productExceptSelf(nums));
        Assert.assertArrayEquals(res, p.productExceptSelfB(nums));
        nums = new int[]{1, 2, 3};
        res = new int[]{6, 3, 2};
        Assert.assertArrayEquals(res, p.productExceptSelf(nums));
        Assert.assertArrayEquals(res, p.productExceptSelfB(nums));
        nums = new int[]{1, 2};
        res = new int[]{2, 1};
        Assert.assertArrayEquals(res, p.productExceptSelf(nums));
        Assert.assertArrayEquals(res, p.productExceptSelfB(nums));
    }

    @After
    public void tearDown() {
        p = null;
    }

}
