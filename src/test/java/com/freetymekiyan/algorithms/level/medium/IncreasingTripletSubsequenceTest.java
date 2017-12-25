package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IncreasingTripletSubsequenceTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new int[]{1, 2, 3, 4, 5}, true},
                new Object[]{new int[]{5, 4, 3, 2, 1}, false}
        };
    }

    @Test
    public void testIncreasingTriplet(int[] input, boolean output) {
        IncreasingTripletSubsequence i = new IncreasingTripletSubsequence();
        Assert.assertEquals(i.increasingTriplet(input), output);
    }

    @Test
    public void testIncreasingTriplet2(int[] input, boolean output) {
        IncreasingTripletSubsequence i = new IncreasingTripletSubsequence();
        Assert.assertEquals(i.increasingTriplet2(input), output);
    }
}