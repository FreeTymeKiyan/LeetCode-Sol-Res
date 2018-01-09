package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SqrtTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        int[] nums = {0, 1, 2, 4, 9, 16, 25};
        int[] res = {0, 1, 1, 2, 3, 4, 5};
        Object[][] examples = new Object[nums.length][2];
        for (int i = 0; i < examples.length; i++) {
            examples[i][0] = nums[i];
            examples[i][1] = res[i];
        }
        return examples;
    }

    @Test(dataProvider = "examples")
    public void testMySqrt(int x, int r) {
        Sqrt s = new Sqrt();
        Assert.assertEquals(s.mySqrt(x), r);
    }

    @Test(dataProvider = "examples")
    public void testMySqrt2(int x, int r) {
        Sqrt s = new Sqrt();
        Assert.assertEquals(s.mySqrt2(x), r);
    }

    @Test(dataProvider = "examples")
    public void testMySqrt3(int x, int r) {
        Sqrt s = new Sqrt();
        Assert.assertEquals(s.mySqrt3(x), r);
    }
}