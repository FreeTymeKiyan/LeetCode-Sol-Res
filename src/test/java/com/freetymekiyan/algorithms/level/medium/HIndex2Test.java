package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HIndex2Test {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[0], 0},
                new Object[]{new int[]{1}, 1},
                new Object[]{new int[]{1, 2, 3, 4, 5}, 3},
                new Object[]{new int[]{5, 6, 7, 8, 9}, 5},
                new Object[]{new int[]{0, 0, 0, 0, 0}, 0}
        };
    }

    @Test(dataProvider = "examples")
    public void testHIndex(int[] input, int output) {
        HIndex2 h = new HIndex2();
        Assert.assertEquals(h.hIndex(input), output);
    }

    @Test(dataProvider = "examples")
    public void testHIndex2(int[] input, int output) {
        HIndex2 h = new HIndex2();
        Assert.assertEquals(h.hIndex2(input), output);
    }
}