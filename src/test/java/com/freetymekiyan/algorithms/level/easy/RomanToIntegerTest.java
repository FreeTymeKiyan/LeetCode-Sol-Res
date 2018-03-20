package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RomanToIntegerTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"DCXXI", 621},
                new Object[]{"DCXXIV", 624},
                new Object[]{"I", 1},
        };
    }

    @Test(dataProvider = "examples")
    public void testRomanToInt(String romans, int expected) {
        RomanToInteger r = new RomanToInteger();
        Assert.assertEquals(r.romanToInt(romans), expected);
    }
}