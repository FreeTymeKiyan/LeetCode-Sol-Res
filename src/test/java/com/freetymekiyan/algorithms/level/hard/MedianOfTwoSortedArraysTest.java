package com.freetymekiyan.algorithms.level.hard;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MedianOfTwoSortedArraysTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{}, new int[]{}, 0.0},
                new Object[]{new int[]{2}, new int[]{}, 2.0},
                new Object[]{new int[]{}, new int[]{2}, 2.0},
                new Object[]{new int[]{1, 2, 3, 4, 5}, new int[]{2, 4, 5, 6, 7}, 4.0},
                new Object[]{new int[]{1, 2, 3, 4, 5}, new int[]{2, 4, 5, 6, 7, 8}, 4.0},
        };
    }

    @Test(dataProvider = "examples")
    public void testExamples(int[] nums1, int[] nums2, double expected) {
        MedianOfTwoSortedArrays m = new MedianOfTwoSortedArrays();
        Assert.assertEquals(m.findMedianSortedArrays(nums1, nums2), expected);
    }
}