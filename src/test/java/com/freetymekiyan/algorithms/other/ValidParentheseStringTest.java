package com.freetymekiyan.algorithms.other;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ValidParentheseStringTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"", ""},
                new Object[]{"(()das10())", "(()das10())"},
                new Object[]{"()()())", "()()()"},
                new Object[]{")(", ""},
                new Object[]{"((((((", ""},
                new Object[]{"))))))", ""},
        };
    }

    @Test(dataProvider = "examples")
    public void testGetValidString(String s, String expected) {
        ValidParentheseString v = new ValidParentheseString();
        Assert.assertEquals(v.getValidString(s), expected);
    }
}