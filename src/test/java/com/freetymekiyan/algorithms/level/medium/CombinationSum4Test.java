package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CombinationSum4Test {

    @DataProvider(name = "examples")
    public Object[][] generateExamples() {
        int[] nums = {1, 2, 3};
        return new Object[][]{
                new Object[]{nums, 4, 7},
                new Object[]{nums, 6, 24}
        };
    }

    @Test(description = "Test examples on bottom-up implementation.", dataProvider = "examples")
    public void testCombinationSum4(int[] nums, int target, int output) {
        CombinationSum4 c = new CombinationSum4();
        Assert.assertEquals(c.combinationSum4(nums, target), output);
    }

    @Test(description = "Test examples on top-down implementation.", dataProvider = "examples")
    public void testCombinationSum4TopDown(int[] nums, int target, int output) {
        CombinationSum4 c = new CombinationSum4();
        Assert.assertEquals(c.combinationSum4TopDown(nums, target), output);
    }
}