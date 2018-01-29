package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LongestIncreasingSubsequenceTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{10, 9, 2, 5, 3, 7, 101, 18}, 4},
                new Object[]{new int[]{1, 2, 3, 4, 5}, 5},
                new Object[]{new int[]{5, 4, 3, 2, 1}, 1},
                new Object[]{new int[]{}, 0}
        };
    }

    @Test(dataProvider = "examples")
    public void testLengthOfLIS(int[] nums, int expected) {
        LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
        Assert.assertEquals(l.lengthOfLIS(nums), expected);
    }

    @Test(dataProvider = "examples")
    public void testLengthOfLIS2(int[] nums, int expected) {
        LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();
        Assert.assertEquals(l.lengthOfLIS2(nums), expected);
    }
}