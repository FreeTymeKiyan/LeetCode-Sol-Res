package com.freetymekiyan.algorithms.level.easy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * <p>
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * <p>
 * Note:
 * Have you consider that the string might be empty? This is a good question to ask during an interview.
 * <p>
 * For the purpose of this problem, we define empty string as valid palindrome.
 * <p>
 * Company Tags: Microsoft, Uber, Facebook, Zenefits
 * Tags: Two Pointers, String
 * Similar Problems: (E) Palindrome Linked List
 */
public class ValidPalindrome {

    private ValidPalindrome v;

    /**
     * Two pointers.
     * Ask for clarification:
     * What characters do we have for input? Space?
     * Case sensitive or not?
     * First convert the string to lowercase.
     * Then start from both ends and move pointers to the next alphanumeric character.
     * If these characters are not the same, return false.
     * Remember to move pointers one more step for the next round.
     */
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int i = 0;
        int j = s.length() - 1;
        s = s.toLowerCase();
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            if (i >= j) {
                return true;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    @Before
    public void setUp() {
        v = new ValidPalindrome();
    }

    @Test
    public void testExamples() {
        Assert.assertTrue(v.isPalindrome("A man, a plan, a canal: Panama"));
        Assert.assertFalse(v.isPalindrome("race a car"));
    }

    @After
    public void tearDown() {
        v = null;
    }

}
