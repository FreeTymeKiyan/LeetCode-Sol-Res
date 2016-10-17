package com.freetymekiyan.algorithms.level.easy;

import org.junit.Assert;
import org.junit.Test;

/**
 * Implement strStr().
 * <p>
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Company Tags: Pocket Gems, Microsoft, Apple, Facebook
 * Tags: Two Pointers, String
 * Similar Problems: (H) Shortest Palindrome
 */
public class ImplementStrStr {

    /**
     * Two pointers.
     * For each point in haystack, check there is needle.
     * Special case:
     * If needle is empty, return 0.
     */
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) {
            return 0;
        }
        int m = haystack.length();
        int n = needle.length();
        for (int i = 0; i <= m - n; i++) {
            for (int j = 0; j < n; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
                if (j == n - 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Test
    public void testExamples() {
        String str1 = "14531234";
        String str2 = "123";
        String str3 = "1123";
        String str4 = "1245";
        String str5 = "12121212123";
        Assert.assertEquals(4, strStr(str1, str2));
        Assert.assertEquals(1, strStr(str3, str2));
        Assert.assertEquals(-1, strStr(str4, str2));
        Assert.assertEquals(8, strStr(str5, str2));
    }
}
