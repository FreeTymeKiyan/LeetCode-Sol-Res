package com.freetymekiyan.algorithms.other;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddTwoDoublesTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"1.13", "0.9", "2.03"},
                new Object[]{"1.0", "1.0", "2.0"},
                new Object[]{"0.0", "5.0", "5.0"},
                new Object[]{"1.0", "0.0", "1.0"},
                new Object[]{"0.0", "0.0", "0.0"},
                new Object[]{"1.01", "1.0", "2.01"},
                new Object[]{"1.011111", "1.0", "2.011111"},
                new Object[]{"1.0", "1.011111", "2.011111"},
                new Object[]{"1.099999", "1.011111", "2.11111"}
        };
    }

    @Test(dataProvider = "examples")
    public void testAddDoubles(String s1, String s2, String expected) {
        AddTwoDoubles a = new AddTwoDoubles();
        Assert.assertEquals(a.addDoubles(s1, s2), expected);
    }
}