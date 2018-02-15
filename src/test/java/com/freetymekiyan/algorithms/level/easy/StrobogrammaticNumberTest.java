package com.freetymekiyan.algorithms.level.easy;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class StrobogrammaticNumberTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"68", false},
                new Object[]{"69", true},
                new Object[]{"80", true},
                new Object[]{"818", true}
        };
    }

    @Test(dataProvider = "examples")
    public void testIsStrobogrammatic(String num, boolean expected) {
        StrobogrammaticNumber s = new StrobogrammaticNumber();
        Assert.assertEquals(s.isStrobogrammatic(num), expected);
    }
}