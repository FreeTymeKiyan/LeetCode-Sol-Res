package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubArraySumEqualsKTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{1, 1, 1}, 2, 2},
                new Object[]{new int[]{1, 1, 1, 1}, 2, 3},
                new Object[]{new int[]{-1, -1, -1, -1}, -2, 3},
        };
    }

    @Test(dataProvider = "examples")
    public void testSubarraySum(int[] nums, int k, int expected) {
        SubArraySumEqualsK s = new SubArraySumEqualsK();
        Assert.assertEquals(s.subarraySum(nums, k), expected);
    }
}