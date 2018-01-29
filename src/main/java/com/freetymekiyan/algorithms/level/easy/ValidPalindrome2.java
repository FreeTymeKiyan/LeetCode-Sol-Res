package com.freetymekiyan.algorithms.level.easy;

/**
 * 680. Valid Palindrome II
 * <p>
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 * <p>
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 * <p>
 * Related Topics: String
 * Similar Questions: (E) Valid Palindrome
 */
public class ValidPalindrome2 {

    /**
     * 3 cases:
     * 1. Remove leftmost character, check if the remaining string is a palindrome.
     * 2. Remove rightmost character, check if the remaining string is a palindrome.
     * 3. Keep both, remove a character somewhere in the middle. Then these characters should be the same.
     */
    public boolean validPalindrome(String s) {
        if (s == null) return false;
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return isPalindrome(s, l + 1, r) || isPalindrome(s, l, r - 1);
            }
            l++;
            r--;
        }
        return true;
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}