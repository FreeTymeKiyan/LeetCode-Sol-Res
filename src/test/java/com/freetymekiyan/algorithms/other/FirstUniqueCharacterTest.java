package com.freetymekiyan.algorithms.other;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstUniqueCharacterTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{"aabnc", 'b'},
                new Object[]{"abc", 'a'},
                new Object[]{"ab", 'a'},
                new Object[]{"aab", 'b'}
        };
    }

    @DataProvider(name = "blankInput")
    public Object[][] getBlankInput() {
        return new Object[][]{
                new Object[]{null},
                new Object[]{""}
        };
    }

    @DataProvider(name = "invalidInput")
    public Object[][] getInvalidInput() {
        return new Object[][]{
                new Object[]{"aa"},
                new Object[]{"aaa"}
        };
    }


    @Test(dataProvider = "examples")
    public void testFindFirstUnique(String s, char expected) {
        FirstUniqueCharacter f = new FirstUniqueCharacter();
        Assert.assertEquals(f.findFirstUnique(s), expected);
    }

    @Test(dataProvider = "blankInput", expectedExceptions = IllegalArgumentException.class)
    public void testBlankInput(String s) {
        FirstUniqueCharacter f = new FirstUniqueCharacter();
        f.findFirstUnique(s);
    }

    @Test(dataProvider = "invalidInput", expectedExceptions = IllegalStateException.class)
    public void testNullInput(String s) {
        FirstUniqueCharacter f = new FirstUniqueCharacter();
        f.findFirstUnique(s);
    }
}