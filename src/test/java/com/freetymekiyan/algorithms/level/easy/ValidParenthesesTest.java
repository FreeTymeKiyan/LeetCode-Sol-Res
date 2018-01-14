package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ValidParenthesesTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"()", true},
                new Object[]{"()[]{}", true},
                new Object[]{"([)]", false},
                new Object[]{"[({(())}[()])]", true},
                new Object[]{"a[a(a{a(a(.)a)a}x[a(a)v]w)q]z", false},
        };
    }

    @Test(dataProvider = "examples")
    public void testIsValid(String s, boolean expected) {
        ValidParentheses v = new ValidParentheses();
        Assert.assertEquals(v.isValid(s), expected);
    }

    @Test(dataProvider = "examples")
    public void testIsValid2(String s, boolean expected) {
        ValidParentheses v = new ValidParentheses();
        Assert.assertEquals(v.isValid2(s), expected);
    }
}