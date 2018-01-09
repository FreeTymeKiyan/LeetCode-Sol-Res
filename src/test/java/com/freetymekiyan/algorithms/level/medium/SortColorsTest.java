package com.freetymekiyan.algorithms.level.medium;

import com.freetymekiyan.algorithms.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SortColorsTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{0, 1, 0, 1, 2, 1, 0}, new int[]{0, 0, 0, 1, 1, 1, 2}},
                new Object[]{new int[]{1, 2, 0}, new int[]{0, 1, 2}},
                new Object[]{new int[]{2}, new int[]{2}},
                new Object[]{new int[]{2, 2}, new int[]{2, 2}},
                new Object[]{new int[]{0}, new int[]{0}}
        };
    }

    @Test(dataProvider = "examples")
    public void testSortColors(int[] input, int[] output) {
        SortColors s = new SortColors();
        s.sortColors(input);
        Assert.assertTrue(Utils.compareArrays(input, output));
    }

    @Test(dataProvider = "examples")
    public void testSortColors2(int[] input, int[] output) {
        SortColors s = new SortColors();
        s.sortColors2(input);
        Assert.assertTrue(Utils.compareArrays(input, output));
    }

    @Test(dataProvider = "examples")
    public void testSortColors3(int[] input, int[] output) {
        SortColors s = new SortColors();
        s.sortColors3(input);
        Assert.assertTrue(Utils.compareArrays(input, output));
    }

    @Test(dataProvider = "examples")
    public void testSortColors4(int[] input, int[] output) {
        SortColors s = new SortColors();
        s.sortColors4(input);
        Assert.assertTrue(Utils.compareArrays(input, output));
    }
}