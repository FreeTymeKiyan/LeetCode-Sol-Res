package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ValidPalindrome2Test {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"aba", true},
                new Object[]{"abca", true},
                new Object[]{"acac", true},
                new Object[]{"aacc", false},
                new Object[]{"a", true},
                new Object[]{"ab", true},
                new Object[]{"abc", false}
        };
    }

    @Test(dataProvider = "examples")
    public void testValidPalindromeExamples(String s, boolean expected) {
        ValidPalindrome2 v = new ValidPalindrome2();
        Assert.assertEquals(v.validPalindrome(s), expected);
    }

    @Test
    public void testNullString() {
        ValidPalindrome2 v = new ValidPalindrome2();
        assertFalse(v.validPalindrome(null));
    }

    @Test
    public void testEmptyString() {
        ValidPalindrome2 v = new ValidPalindrome2();
        assertTrue(v.validPalindrome(""));
    }
}