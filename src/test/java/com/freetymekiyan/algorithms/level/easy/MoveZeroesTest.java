package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class MoveZeroesTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{null, null},
                new Object[]{new int[]{}, new int[]{}},
                new Object[]{new int[]{1}, new int[]{1}},
                new Object[]{new int[]{1, 2, 3}, new int[]{1, 2, 3}},
                new Object[]{new int[]{0, 0, 0}, new int[]{0, 0, 0}},
                new Object[]{new int[]{0, 1, 0, 3, 12}, new int[]{1, 3, 12, 0, 0}},
        };
    }

    @Test(dataProvider = "examples")
    public void testMoveZeroes(int[] input, int[] output) {
        MoveZeroes m = new MoveZeroes();
        m.moveZeroes(input);
        Assert.assertTrue(Arrays.equals(input, output));
    }

    @Test(dataProvider = "examples")
    public void testMoveZeroesB(int[] input, int[] output) {
        MoveZeroes m = new MoveZeroes();
        m.moveZeroes2(input);
        Assert.assertTrue(Arrays.equals(input, output));
    }
}