package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NumberOfLongestIncreasingSubsequenceTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{1, 3, 5, 4, 7}, 2},
                new Object[]{new int[]{2, 2, 2, 2, 2}, 5},
                new Object[]{new int[]{3, 2, 1}, 3},
                new Object[]{new int[]{1, 2, 4, 3, 5, 4, 7, 2}, 3},
                new Object[]{new int[]{1, 2, 3, 1, 2, 3, 1, 2, 3}, 10}
        };
    }

    @Test(dataProvider = "examples")
    public void testFindNumberOfLISExamples(int[] nums, int expected) {
        NumberOfLongestIncreasingSubsequence n = new NumberOfLongestIncreasingSubsequence();
        Assert.assertEquals(n.findNumberOfLIS(nums), expected);
    }
}