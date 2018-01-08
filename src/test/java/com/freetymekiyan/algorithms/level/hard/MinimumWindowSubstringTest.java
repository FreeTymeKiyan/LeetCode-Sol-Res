package com.freetymekiyan.algorithms.level.hard;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MinimumWindowSubstringTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"ADOBECODEBANC", "ABC", "BANC"},
                new Object[]{"a", "a", "a"},
                new Object[]{"ab", "b", "b"}
        };
    }

    @Test(dataProvider = "examples")
    public void testMinWindow(String s, String t, String expected) {
        MinimumWindowSubstring m = new MinimumWindowSubstring();
        Assert.assertEquals(m.minWindow(s, t), expected);
    }

    @Test(dataProvider = "examples")
    public void testMinWindow2(String s, String t, String expected) {
        MinimumWindowSubstring m = new MinimumWindowSubstring();
        Assert.assertEquals(m.minWindow2(s, t), expected);
    }
}