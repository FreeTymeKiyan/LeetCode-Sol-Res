package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * <p>
 * Formally the function should:
 * Return true if there exists i, j, k
 * such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * <p>
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 * <p>
 * Given [5, 4, 3, 2, 1],
 * return false.
 * <p>
 * Similar Problems: (M) Longest Increasing Subsequence
 */
public class IncreasingTripletSubsequence {

    private IncreasingTripletSubsequence i;

    /**
     * DP.
     * Find three numbers.
     * Find two numbers first, then find one that's larger than both.
     * n1 means the minimum of subsequences of length 1.
     * n2 means the minimum of subsequences of length 2.
     */
    public boolean increasingTriplet(int[] nums) {
        int n1 = Integer.MAX_VALUE;
        int n2 = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= n1) {
                n1 = n;
            } else if (n <= n2) {
                n2 = n;
            } else {
                return true;
            }
        }
        return false;
    }

    @Before
    public void setUp() {
        i = new IncreasingTripletSubsequence();
    }

    @Test
    public void testExamples() {
        int[] input = {1, 2, 3, 4, 5};
        Assert.assertTrue(i.increasingTriplet(input));
        input = new int[]{5, 4, 3, 2, 1};
        Assert.assertFalse(i.increasingTriplet(input));
    }

    @After
    public void tearDown() {
        i = null;
    }

}
