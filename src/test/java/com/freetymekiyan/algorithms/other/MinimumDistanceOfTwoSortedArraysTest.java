package com.freetymekiyan.algorithms.other;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MinimumDistanceOfTwoSortedArraysTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{1, 4, 7}, new int[]{0, 2, 10}, 1},
                new Object[]{new int[]{1, 2, 3}, new int[]{4, 5, 6}, 1},
                new Object[]{new int[]{1, 2, 3, 4}, new int[]{4, 5, 6}, 0},
                new Object[]{new int[]{2, 2, 2}, new int[]{1, 1, 1, 1, 1}, 1},
                new Object[]{new int[]{}, new int[]{}, Integer.MAX_VALUE},
                new Object[]{new int[]{1}, new int[]{}, Integer.MAX_VALUE},
                new Object[]{new int[]{}, new int[]{2}, Integer.MAX_VALUE}
        };
    }

    @Test(dataProvider = "examples")
    public void testGetMinDistance(int[] nums1, int[] nums2, int expected) {
        MinimumDistanceOfTwoSortedArrays m = new MinimumDistanceOfTwoSortedArrays();
        Assert.assertEquals(m.getMinDistance(nums1, nums2), expected);
    }
}