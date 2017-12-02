package com.freetymekiyan.algorithms.level.hard;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and
 * only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 * <p>
 * Example:
 * Given "bcabc"
 * Return "abc"
 * <p>
 * Given "cbacdcbc"
 * Return "acdb"
 * <p>
 * Tags: Stack, Greedy
 */
public class RemoveDuplicateLetters {

    private RemoveDuplicateLetters r;

    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() <= 1) return s;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        int minPos = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(minPos)) minPos = i;
            if (--count[s.charAt(i) - 'a'] == 0) break;
        }
        return s.charAt(minPos) + removeDuplicateLetters(s.substring(minPos + 1).replace(String.valueOf(s.charAt(minPos)), ""));
    }

    @Before
    public void setUp() {
        r = new RemoveDuplicateLetters();
    }

    @Test
    public void testEdgeCases() {
        Assert.assertNull(r.removeDuplicateLetters(null));
        Assert.assertEquals("", r.removeDuplicateLetters(""));
        Assert.assertEquals("a", r.removeDuplicateLetters("a"));
    }

    @Test
    public void testExamples() {
        Assert.assertEquals("a", r.removeDuplicateLetters("aa"));
        Assert.assertEquals("abc", r.removeDuplicateLetters("bcabc"));
        Assert.assertEquals("acdb", r.removeDuplicateLetters("cbacdcbc"));
    }

    @After
    public void tearDown() {
        r = null;
    }
}
