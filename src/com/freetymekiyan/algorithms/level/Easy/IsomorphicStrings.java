package com.freetymekiyan.algorithms.level.easy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * <p>
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * <p>
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No
 * two characters may map to the same character but a character may map to itself.
 * <p>
 * For example,
 * Given "egg", "add", return true.
 * <p>
 * Given "foo", "bar", return false.
 * <p>
 * Given "paper", "title", return true.
 * <p>
 * Note:
 * You may assume both s and t have the same length.
 * <p>
 * Company Tags: LinkedIn
 * Tags: Hash Table
 * Similar Problems: (E) Word Pattern
 */
public class IsomorphicStrings {

    private IsomorphicStrings is;

    /**
     * Hash Table.
     * Store the previous seen index of a character. 0 means not seen yet.
     * If the last seen indices are different, return false.
     * Update index to i + 1.
     * After the check, return true, since s and t are of same length.
     */
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            return false;
        }
        int[] m = new int[512];
        for (int i = 0; i < s.length(); i++) {
            if (m[s.charAt(i)] != m[t.charAt(i) + 256]) {
                return false;
            }
            m[s.charAt(i)] = m[t.charAt(i) + 256] = i + 1;
        }
        return true;
    }

    @Before
    public void setUp() {
        is = new IsomorphicStrings();
    }

    @Test
    public void testEdgeCases() {
        Assert.assertFalse(is.isIsomorphic(null, null));
        Assert.assertFalse(is.isIsomorphic(null, ""));
        Assert.assertFalse(is.isIsomorphic("", null));
        Assert.assertTrue(is.isIsomorphic("", ""));
    }

    @Test
    public void testExamples() {
        // "egg", "add", return true
        Assert.assertTrue(is.isIsomorphic("egg", "add"));
        // "foo", "bar", return false
        Assert.assertFalse(is.isIsomorphic("foo", "bar"));
        // "paper", "title", return true
        Assert.assertTrue(is.isIsomorphic("paper", "title"));
        // "papper", "tittle", return true
        Assert.assertTrue(is.isIsomorphic("papper", "tittle"));
        // "abba", "abab"
        Assert.assertFalse(is.isIsomorphic("abba", "abab"));
    }

    @After
    public void tearDown() {
        is = null;
    }

}
