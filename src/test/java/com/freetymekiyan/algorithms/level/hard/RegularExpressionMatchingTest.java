package com.freetymekiyan.algorithms.level.hard;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RegularExpressionMatchingTest {

    @Test
    public void testIsMatch() {
        RegularExpressionMatching r = new RegularExpressionMatching();
        Assert.assertFalse(r.isMatch("aa", "a"));
        Assert.assertTrue(r.isMatch("aa", "aa"));
        Assert.assertFalse(r.isMatch("aaa", "aa"));
        Assert.assertTrue(r.isMatch("aa", "a*"));
        Assert.assertTrue(r.isMatch("aa", ".*"));
        Assert.assertTrue(r.isMatch("ab", ".*"));
        Assert.assertTrue(r.isMatch("aab", "c*a*b"));
    }
}