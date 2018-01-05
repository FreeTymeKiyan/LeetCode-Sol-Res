package com.freetymekiyan.algorithms.level.easy;

/**
 * 125. Valid Palindrome
 * <p>
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
        if (s == null || s.isEmpty()) return true;
        for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
            while (i <= j && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while (i <= j && !Character.isLetterOrDigit(s.charAt(j))) j--;
            if (i > j) return true;
            if (!compareIgnoreCase(s.charAt(i), s.charAt(j))) return false;
        }
        return true;
    }

    private boolean compareIgnoreCase(char c1, char c2) {
        return Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }
}