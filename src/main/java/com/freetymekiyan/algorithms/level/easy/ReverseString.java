package com.freetymekiyan.algorithms.level.easy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Write a function that takes a string as input and returns the string reversed.
 * <p>
 * Example:
 * Given s = "hello", return "olleh".
 * <p>
 * Subscribe to see which companies asked this question
 * <p>
 * Tags: Two Pointers, String
 * Similar Problems: (E) Reverse Vowels of a String
 */
public class ReverseString {

    private ReverseString rs;

    /**
     * Two-pointer swapping
     */
    public String reverseString(String s) {
        if (s == null || s.length() < 2) return s;
        int i = 0;
        int j = s.length() - 1;
        char[] chars = s.toCharArray();
        while (i < j) {
            char c = chars[i];
            chars[i] = chars[j];
            chars[j] = c;
            i++;
            j--;
        }
        return new String(chars);
    }

    /**
     * Use StringBuilder::reverse
     */
    public String reverseStringB(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    /**
     * Use bit manipulation instead of temp variable
     */
    public String reverseStringC(String s) {
        byte[] bytes = s.getBytes();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            bytes[i] = (byte) (bytes[i] ^ bytes[j]);
            bytes[j] = (byte) (bytes[i] ^ bytes[j]); // bytes[i] ^ bytes[j] ^ bytes[j] = bytes[i]
            bytes[i] = (byte) (bytes[i] ^ bytes[j]); // bytes[i] ^ bytes[j] ^ bytes[i] = bytes[j]
            i++;
            j--;
        }
        return new String(bytes);
    }

    /**
     * Recursion (Divide and conquer)
     * Reverse a string can be done by reversing right substring and reversing left substring
     * Then concatenate them
     */
    public String reverseStringD(String s) {
        int length = s.length();
        if (length <= 1) return s;
        String leftStr = s.substring(0, length / 2);
        String rightStr = s.substring(length / 2, length);
        return reverseString(rightStr) + reverseString(leftStr);
    }


    @Before
    public void setUp() {
        rs = new ReverseString();
    }

    @Test
    public void testEdgeCases() {
        Assert.assertNull(rs.reverseString(null));
        Assert.assertEquals("", rs.reverseString(""));
        Assert.assertEquals("a", rs.reverseString("a"));
        Assert.assertEquals(" ", rs.reverseString(" "));
    }

    @Test
    public void testFunction() {
        Assert.assertEquals("olleh", rs.reverseString("hello"));
    }

    @After
    public void tearDown() {
        rs = null;
    }

}
