package com.freetymekiyan.algorithms.level.hard;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WildcardMatchingTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"", "a", false},
                new Object[]{"a", "", false},
                new Object[]{"", "", true},
                new Object[]{null, null, true},
                new Object[]{"a", null, false},
                new Object[]{null, "null", false},
                new Object[]{"a", "aa", false},
                new Object[]{"aa", "a", false},
                new Object[]{"aa", "aa", true},
                new Object[]{"aa", "ab", false},
                new Object[]{"aa", "a*", true},
                new Object[]{"aa", "*", true},
                new Object[]{"aa", "?", false},
                new Object[]{"a", "?", true},
                new Object[]{"abc", "a*", true},
                new Object[]{"ab", "a*", true},
                new Object[]{"ab", "*a", false},
                new Object[]{"a", "*a", true},
                new Object[]{"a", "a*", true},
                new Object[]{"bcsa", "*a", true},
                new Object[]{"bcs", "*a", false},
                new Object[]{"bbbbbbbbbb", "*bbbbb", true},
                new Object[]{"b", "*?*?", false}
        };
    }

    @Test(dataProvider = "examples")
    public void testIsMatch(String s, String p, boolean expected) {
        WildcardMatching w = new WildcardMatching();
        Assert.assertEquals(w.isMatch(s, p), expected);
    }

    @Test(dataProvider = "examples")
    public void testIsMatch2(String s, String p, boolean expected) {
        WildcardMatching w = new WildcardMatching();
        Assert.assertEquals(w.isMatch2(s, p), expected);
    }
}