package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MinimumSwapsToMakeSequencesIncreasingTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{1, 3, 5, 4}, new int[]{1, 2, 3, 7}, 1},
                new Object[]{new int[]{2, 1, 6, 9, 10, 13, 13, 16, 19, 26, 23, 24, 25, 27, 32, 31, 35, 36, 37, 39}, new int[]{0, 5, 8, 8, 10, 12, 14, 15, 22, 22, 28, 29, 30, 31, 30, 33, 33, 36, 37, 38}, 6}
        };
    }

    @Test(dataProvider = "examples")
    public void testMinSwap(int[] A, int[] B, int expected) {
        MinimumSwapsToMakeSequencesIncreasing m = new MinimumSwapsToMakeSequencesIncreasing();
        Assert.assertEquals(m.minSwap(A, B), expected);
    }
}