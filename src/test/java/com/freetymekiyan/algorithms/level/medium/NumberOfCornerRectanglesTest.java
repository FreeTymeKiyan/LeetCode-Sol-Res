package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NumberOfCornerRectanglesTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[][]{new int[]{1, 0, 0, 1, 0}, new int[]{0, 0, 1, 0, 1}, new int[]{0, 0, 0, 1, 0}, new int[]{1, 0, 1, 0, 1}}, 1},
                new Object[]{new int[][]{new int[]{1, 1, 1}, new int[]{1, 1, 1}, new int[]{1, 1, 1}}, 9},
                new Object[]{new int[][]{new int[]{1, 1, 1, 1}}, 0}
        };
    }

    @Test(dataProvider = "examples")
    public void testCountCornerRectangles(int[][] grid, int expected) {
        NumberOfCornerRectangles n = new NumberOfCornerRectangles();
        Assert.assertEquals(n.countCornerRectangles(grid), expected);
    }

    @Test(dataProvider = "examples")
    public void testCountCornerRectangles2(int[][] grid, int expected) {
        NumberOfCornerRectangles n = new NumberOfCornerRectangles();
        Assert.assertEquals(n.countCornerRectangles2(grid), expected);
    }
}