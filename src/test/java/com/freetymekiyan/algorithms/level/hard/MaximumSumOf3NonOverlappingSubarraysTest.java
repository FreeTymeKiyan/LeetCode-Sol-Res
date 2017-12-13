package com.freetymekiyan.algorithms.level.hard;

import com.freetymekiyan.algorithms.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MaximumSumOf3NonOverlappingSubarraysTest {

    @Test
    public void testMaxSumOfThreeSubarrays() {
        int[] input = {1, 2, 1, 2, 6, 7, 6, 1};
        MaximumSumOf3NonOverlappingSubarrays m = new MaximumSumOf3NonOverlappingSubarrays();
        int[] output = m.maxSumOfThreeSubarrays(input, 2);
        Assert.assertTrue(Utils.compareArrays(output, new int[]{0, 3, 5}));
    }
}