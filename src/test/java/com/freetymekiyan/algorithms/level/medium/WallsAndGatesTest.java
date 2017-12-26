package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WallsAndGatesTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        int[][] input = {
                {2147483647, -1, 0, 2147483647},
                {2147483647, 2147483647, 2147483647, -1},
                {2147483647, -1, 2147483647, -1},
                {0, -1, 2147483647, 2147483647}
        };
        int[][] output = {
                {3, -1, 0, 1},
                {2, 2, 1, -1},
                {1, -1, 2, -1},
                {0, -1, 3, 4}
        };
        return new Object[][]{
                new Object[]{input, output}
        };
    }

    @Test(dataProvider = "examples")
    public void testWallsAndGates(int[][] input, int[][] output) {
        WallsAndGates w = new WallsAndGates();
        w.wallsAndGates(input);
        Assert.assertTrue(Utils.compareMatrices(input, output));
    }

    @Test(dataProvider = "examples")
    public void testWallsAndGates2(int[][] input, int[][] output) {
        WallsAndGates w = new WallsAndGates();
        w.wallsAndGates2(input);
        Assert.assertTrue(Utils.compareMatrices(input, output));
    }
}