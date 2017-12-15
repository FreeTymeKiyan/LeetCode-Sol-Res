package com.freetymekiyan.algorithms.level.hard;

import org.junit.Assert;
import org.junit.Test;

public class PrefixAndSuffixSearchTest {

    @Test
    public void testWordFilterExample() {
        String[] words = {"apple"};
        PrefixAndSuffixSearch.WordFilter w = new PrefixAndSuffixSearch.WordFilter(words);
        Assert.assertEquals(0, w.f("a", "e"));
        Assert.assertEquals(-1, w.f("b", ""));
        Assert.assertEquals(0, w.f("", ""));
        Assert.assertEquals(0, w.f("", "le"));

        words = new String[]{"abbbababbb", "baaabbabbb", "abababbaaa", "abbbbbbbba", "bbbaabbbaa", "ababbaabaa", "baaaaabbbb", "babbabbabb", "ababaababb", "bbabbababa"};
        String[][] inputs = {{"", "abaa"}, {"babbab", ""}, {"ab", "baaa"}, {"baaabba", "b"}, {"abab", "abbaabaa"}, {"", "aa"}, {"", "bba"}, {"", "baaaaabbbb"}, {"ba", "aabbbb"}, {"baaa", "aabbabbb"}};
        int[] expected = {5, 7, 2, 1, 5, 5, 3, 6, 6, 1};
        w = new PrefixAndSuffixSearch.WordFilter(words);
        for (int i = 0; i < inputs.length; i++) {
            Assert.assertEquals(expected[i], w.f(inputs[i][0], inputs[i][1]));
        }
    }

}