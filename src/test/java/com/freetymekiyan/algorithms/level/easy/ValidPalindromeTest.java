package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ValidPalindromeTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"A man, a plan, a canal: Panama", true},
                new Object[]{"race a car", false},
                new Object[]{"a", true},
                new Object[]{"0P", false}
        };
    }

    @Test(dataProvider = "examples")
    public void testIsPalindrome(String input, boolean expected) {
        ValidPalindrome v = new ValidPalindrome();
        Assert.assertEquals(v.isPalindrome(input), expected);
    }
}