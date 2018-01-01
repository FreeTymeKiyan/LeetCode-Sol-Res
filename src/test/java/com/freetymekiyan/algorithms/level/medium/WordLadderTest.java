package com.freetymekiyan.algorithms.level.medium;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WordLadderTest {

    @DataProvider(name = "examples")
    public Object[][] getExamples() {
        List<String> s = new ArrayList<>();
        s.add("hot");
        s.add("dog");
        s.add("dot");
        return new Object[][]{
                new Object[]{"hot", "dog", s, 3},
        };
    }

    @Test(dataProvider = "examples")
    public void testLadderLength(String begin, String end, List<String> words, int output) {
        WordLadder w = new WordLadder();
        Assert.assertEquals(w.ladderLength(begin, end, words), output);
    }

    @Test(dataProvider = "examples")
    public void testLadderLengthB(String begin, String end, List<String> words, int output) {
        WordLadder w = new WordLadder();
        Assert.assertEquals(w.ladderLengthB(begin, end, words), output);
    }
}