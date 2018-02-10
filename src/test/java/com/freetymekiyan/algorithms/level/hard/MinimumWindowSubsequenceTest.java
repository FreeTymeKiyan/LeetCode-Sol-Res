package com.freetymekiyan.algorithms.level.hard;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MinimumWindowSubsequenceTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"abcdebdde", "bde", "bcde"}
        };
    }

    @Test(dataProvider = "examples")
    public void testMinWindow(String S, String T, String expected) {
        MinimumWindowSubsequence m = new MinimumWindowSubsequence();
        Assert.assertEquals(m.minWindow(S, T), expected);
    }

    @Test(dataProvider = "examples")
    public void testMinWindow2(String S, String T, String expected) {
        MinimumWindowSubsequence m = new MinimumWindowSubsequence();
        Assert.assertEquals(m.minWindow2(S, T), expected);
    }
}