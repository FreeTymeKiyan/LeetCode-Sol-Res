package com.freetymekiyan.algorithms.other;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MedianOfNSortedArraysTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[][]{}, 0},
                new Object[]{new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 5},
                new Object[]{new int[][]{{1, 2, 3, 4, 5, 6, 7}, {1, 2, 3, 4}, {7, 8, 9, 10}}, 4},
                new Object[]{new int[][]{{1, 2, 3, 4, 5, 6, 7}, {1, 2, 3, 6, 7}, {7, 8, 9, 10}}, 5.5}
        };
    }

    @Test(dataProvider = "examples")
    public void testGetMedian(int[][] arrs, double median) {
        MedianOfNSortedArrays m = new MedianOfNSortedArrays();
        Assert.assertEquals(m.getMedian(arrs), median);
    }
}