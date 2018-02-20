package com.freetymekiyan.algorithms.level.hard;

import com.freetymekiyan.algorithms.utils.Utils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class PalindromePairsTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        return new Object[][]{
                new Object[]{new String[]{"bat", "tab", "cat"}, List.of(List.of(0, 1), List.of(1, 0))},
                new Object[]{new String[]{"abcd", "dcba", "lls", "s", "sssll"}, List.of(List.of(0, 1), List.of(1, 0), List.of(3, 2), List.of(2, 4))},
        };
    }

    @Test(dataProvider = "examples")
    public void testPalindromePairs(String[] words, List<List<Integer>> expected) {
        PalindromePairs p = new PalindromePairs();
        Assert.assertEquals(p.palindromePairs(words), expected);
    }

    @Test(dataProvider = "examples")
    public void testPalindromePairs2(String[] words, List<List<Integer>> expected) {
        PalindromePairs p = new PalindromePairs();
        Assert.assertTrue(Utils.compareListsIgnoreOrder(p.palindromePairs2(words), expected));
    }
}