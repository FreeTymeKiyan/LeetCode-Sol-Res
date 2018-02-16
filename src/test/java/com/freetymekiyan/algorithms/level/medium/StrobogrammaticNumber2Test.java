package com.freetymekiyan.algorithms.level.medium;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StrobogrammaticNumber2Test {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{1, List.of("0", "1", "8")},
                new Object[]{2, List.of("11", "88", "69", "96")},
                new Object[]{3, List.of("101", "111", "181", "808", "818", "888", "609", "619", "689", "906", "916", "986")}
        };
    }

    @Test(dataProvider = "examples")
    public void testFindStrobogrammatic(int n, List<String> expected) {
        StrobogrammaticNumber2 s = new StrobogrammaticNumber2();
        List<String> actual = new ArrayList<>(s.findStrobogrammatic(n));
        List<String> sortedExpected = new ArrayList<>(expected);
        actual.sort(Comparator.comparingInt(Integer::parseInt));
        sortedExpected.sort(Comparator.comparingInt(Integer::parseInt));
        Assert.assertEquals(actual, sortedExpected);
    }

    @Test(dataProvider = "examples")
    public void testFindStrobogrammatic2(int n, List<String> expected) {
        StrobogrammaticNumber2 s = new StrobogrammaticNumber2();
        List<String> actual = new ArrayList<>(s.findStrobogrammatic2(n));
        List<String> sortedExpected = new ArrayList<>(expected);
        actual.sort(Comparator.comparingInt(Integer::parseInt));
        sortedExpected.sort(Comparator.comparingInt(Integer::parseInt));
        Assert.assertEquals(actual, sortedExpected);
    }
}