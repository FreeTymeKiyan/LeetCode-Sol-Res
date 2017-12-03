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
 * Company Tags: Facebook
 * Similar Problems: (M) Longest Increasing Subsequence
 */
public class IncreasingTripletSubsequence {

    private IncreasingTripletSubsequence i;

    /**
     * DP.
     * Similar to find two minimum values.
     * The only difference is we don't update second min when first min is found.
     * Otherwise n2 can be before n1, which is wrong.
     * So for each number n in nums:
     * | If n <= n1, update n1, that is the minimum.
     * | Else if n <= n2, update n2, that is the minimum after n1.
     * | Else, we find the third value, return true.
     * Return false if third value is not found.
     * Think it like we are filling three spaces.
     * The first space is the minimum.
     * When the first space is found, try to fill the second space.
     * If both are filled, when third space is filled, return true.
     */
    public boolean increasingTriplet(int[] nums) {
        int n1 = Integer.MAX_VALUE;
        int n2 = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= n1) { // Why <= ? Make sure arr[i] < arr[j] < arr[k].
                n1 = n;
            } else if (n <= n2) { // n1 < n <= n2, since arr[i] < arr[j] < arr[k].
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
