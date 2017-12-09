package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NumberOfLongestIncreasingSubsequenceTest {

    @Test
    public void testFindNumberOfLISExamples() {
        NumberOfLongestIncreasingSubsequence n = new NumberOfLongestIncreasingSubsequence();
        int[] input1 = new int[]{1, 3, 5, 4, 7};
        int output1 = 2;
        Assert.assertEquals(n.findNumberOfLIS(input1), output1);
        int[] input2 = new int[]{2, 2, 2, 2, 2};
        int output2 = 5;
        Assert.assertEquals(n.findNumberOfLIS(input2), output2);
        int[] input3 = new int[]{3, 2, 1};
        int output3 = 3;
        Assert.assertEquals(n.findNumberOfLIS(input3), output3);
        int[] input4 = new int[]{1, 2, 4, 3, 5, 4, 7, 2}; // {2, 4, 5, 7} {2, 3, 5, 7} {2, 3, 4, 7}
        int output4 = 3;
        Assert.assertEquals(n.findNumberOfLIS(input4), output4);
    }
}