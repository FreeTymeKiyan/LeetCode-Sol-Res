package com.freetymekiyan.algorithms.level.easy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * <p>
 * Example 1:
 * Given s = "hello", return "holle".
 * <p>
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 * <p>
 * Tags: Two Pointers, String
 * Similar Problems: (E) Reverse String
 */
public class ReverseVowelsOfAString {

    private ReverseVowelsOfAString rv;

    /**
     * Use two pointers, search for vowels from both ends
     */
    public String reverseVowels(String s) {
        if (s == null || s.isEmpty()) return s;
        StringBuilder sb = new StringBuilder(s);
        String vowels = "aeiouAEIOU";
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && vowels.indexOf(s.charAt(i)) == -1) { i++; }
            while (i < j && vowels.indexOf(s.charAt(j)) == -1) { j--; }
            char c = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, c);
            i++;
            j--;
        }
        return sb.toString();
    }

    @Before
    public void setUp() {
        rv = new ReverseVowelsOfAString();
    }

    @Test
    public void testNullInput() {
        Assert.assertNull(rv.reverseVowels(null));
    }

    @Test
    public void testEmptyInput() {
        Assert.assertEquals("", rv.reverseVowels(""));
    }

    @Test
    public void testWithExamples() {
        // hello -> holle
        String s = "hello";
        Assert.assertEquals("holle", rv.reverseVowels(s));
        // leetcode -> leotcede
        s = "leetcode";
        Assert.assertEquals("leotcede", rv.reverseVowels(s));
        // c -> c
        s = "c";
        Assert.assertEquals("c", rv.reverseVowels(s));
        // a -> a
        s = "a";
        Assert.assertEquals("a", rv.reverseVowels(s));
    }

    @After
    public void tearDown() {
        rv = null;
    }

}
