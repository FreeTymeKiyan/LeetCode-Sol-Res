package com.freetymekiyan.algorithms.level.medium;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PermutationInStringTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"ab", "eidbaooo", true},
                new Object[]{"ab", "eidboaoo", false},
                new Object[]{"hello", "ooolleoooleh", false},
                new Object[]{"adc", "dcda", true}
        };
    }

    @Test(dataProvider = "examples")
    public void testCheckInclusion(String s1, String s2, boolean expected) {
        PermutationInString p = new PermutationInString();
        Assert.assertEquals(p.checkInclusion(s1, s2), expected);
    }

    @Test(dataProvider = "examples")
    public void testCheckInclusion2(String s1, String s2, boolean expected) {
        PermutationInString p = new PermutationInString();
        Assert.assertEquals(p.checkInclusion2(s1, s2), expected);
    }

    @Test(dataProvider = "examples")
    public void testCheckInclusion3(String s1, String s2, boolean expected) {
        PermutationInString p = new PermutationInString();
        Assert.assertEquals(p.checkInclusion3(s1, s2), expected);
    }
}