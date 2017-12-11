package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ContinuousSubarraySumTest {

    @Test
    public void testCheckSubarraySum() throws Exception {
        int[] input = {23, 2, 6, 4, 7};
        int k = 6;
        ContinuousSubarraySum c = new ContinuousSubarraySum();
        Assert.assertTrue(c.checkSubarraySum(input, k));
        Assert.assertTrue(c.checkSubarraySum2(input, k));
        k = 0;
        Assert.assertFalse(c.checkSubarraySum(input, k));
        Assert.assertFalse(c.checkSubarraySum2(input, k));
        input = new int[]{0, 1, 0};
        k = 0;
        Assert.assertFalse(c.checkSubarraySum(input, k));
        Assert.assertFalse(c.checkSubarraySum2(input, k));
    }
}