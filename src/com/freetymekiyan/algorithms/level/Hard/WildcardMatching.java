package com.freetymekiyan.algorithms.level.hard;

import org.junit.Assert;
import org.junit.Test;

/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 * <p>
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * <p>
 * The matching should cover the entire input string (not partial).
 * <p>
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * <p>
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 * <p>
 * Company Tags: Google, Snapchat, Two Sigma, Facebook, Twitter
 * Tags: Dynamic Programming, Backtracking, Greedy, String
 * Similar Problems: (H) Regular Expression Matching
 */
public class WildcardMatching {

    /**
     * DP, two pointers.
     * Situations are:
     * 1) Most simple, there is no ? or * in pattern, each character needs to be matched.
     * 2) If we have ? in pattern, it can match anything in string.
     * 3) If we have * in pattern,
     * 3.1) It can match nothing in s
     * 3.2) It can match one or more same characters in s
     * <p>
     * After s is fully matched, we still need to check whether there are more asterisks.
     * <p>
     * Implementation:
     * Two pointers, i for s and j for p.
     * Traverse s with i, check the characters of s and p:
     * 1) If j is in p's range and s[i] and p[j] match, advance both pointers.
     * 2) If j is in p's range and p[j] is *, save the asterisk's index, save the position in s, move j forward.
     * This is because * can match one or more characters. We can retreat to * if there is a mismatch.
     * 3) If there a mismatch, and there is a *, skip the character in S.
     * Also move pointer in j to one step after last asterisk's index. Check the rest.
     * 4) Mismatch and no asterisk to fall back, return false.
     */
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) {
            return true;
        }
        if (s == null || p == null) {
            return false;
        }

        int i = 0, j = 0, match = 0, asterIdx = -1; // must be -1
        while (i < s.length()) {
            if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                // Same characters or '?', move both pointers.
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') { // Found '*' in p
                asterIdx = j; // Save its index.
                match = i; // Save current string index.
                j++; // Move pattern pointer forward.
            } else if (asterIdx != -1) {
                // Different characters, and there is asterisk.
                j = asterIdx + 1; // Reset p pointer to after *.
                match++; // Match the difference with *, so move match.
                i = match; // And set s pointer to match.
            } else {
                // Not ?, not same characters, not *, just don't match
                return false;
            }
        }
        // Check remaining characters in pattern, can only be asterisk
        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }
        return j == p.length(); // No other chars in pattern.
    }

    @Test
    public void testExamples() {
        // Input validation
        Assert.assertFalse(isMatch("", "a"));
        Assert.assertFalse(isMatch("a", ""));
        Assert.assertTrue(isMatch("", "")); // true
        Assert.assertTrue(isMatch(null, null)); // true
        Assert.assertFalse(isMatch("a", null)); // false
        Assert.assertFalse(isMatch(null, "null")); // false
        // Letters
        Assert.assertFalse(isMatch("a", "aa")); // false
        Assert.assertFalse(isMatch("aa", "a")); // false
        Assert.assertTrue(isMatch("aa", "aa")); // true
        Assert.assertFalse(isMatch("aa", "ab")); // false
        // *s
        Assert.assertTrue(isMatch("aa", "*")); // true
        // ?s
        Assert.assertFalse(isMatch("aa", "?")); // false
        Assert.assertTrue(isMatch("a", "?")); // true
        // Letters and *s
        Assert.assertTrue(isMatch("abc", "a*")); // true
        Assert.assertTrue(isMatch("ab", "a*")); // true
        Assert.assertFalse(isMatch("ab", "*a")); // false
        Assert.assertTrue(isMatch("a", "a*")); // true
        Assert.assertTrue(isMatch("bcsa", "*a")); // true
        Assert.assertFalse(isMatch("bcs", "*a")); // false
        Assert.assertTrue(isMatch("bbbbbbbbbb", "*bbbbb")); // true
        // * and ?
        Assert.assertFalse(isMatch("b", "*?*?")); // false
    }
}
