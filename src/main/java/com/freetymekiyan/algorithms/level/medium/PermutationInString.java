package com.freetymekiyan.algorithms.level.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 567. Permutation in String
 * <p>
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words,
 * one of the first string's permutations is the substring of the second string.
 * Example 1:
 * Input:s1 = "ab" s2 = "eidbaooo"
 * Output:True
 * Explanation: s2 contains one permutation of s1 ("ba").
 * Example 2:
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 * Note:
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 * <p>
 * Related Topics: Two Pointers
 * Similar Questions: (H) Minimum Window Substring, (E) Find All Anagrams in a String
 */
public class PermutationInString {

    /**
     * Sliding window. Optimized.
     * Generate count map for s1.
     * Generate count map for s2, length is the same as s1.
     * Maintain the second map as we traverse through s2.
     */
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) {
            return false;
        }

        int[] window = new int[26];
        char[] chs1 = s1.toCharArray();
        char[] chs2 = s2.toCharArray();
        for (int i = 0; i < len1; i++) {
            window[chs1[i] - 'a']++;
            window[chs2[i] - 'a']--;
        }
        int matches = 0; // Number of matches between the count of letter in s1 and the window of s2.
        for (int i = 0; i < 26; i++) {
            if (window[i] == 0)
                matches++;
        }
        for (int i = 0; i < len2 - len1; i++) {
            if (matches == 26) return true;
            int s = chs2[i] - 'a'; // First letter of the window, to be removed.
            int e = chs2[i + len1] - 'a'; // First letter after the window, to be added.
            // Add letter to window.
            window[e]--;
            if (window[e] == 0) {
                matches++;
            } else if (window[e] == -1) {
                matches--;
            }
            // Remove letter from window.
            window[s]++;
            if (window[s] == 0) {
                matches++;
            } else if (window[s] == 1) {
                matches--;
            }
        }
        return matches == 26;
    }

    /**
     * Two pointers.
     * Use integer array to record letter count instead of map.
     * Note that the letters not in S1 and letters drops to 0 must be distinguishable.
     * So set all letters not in S1's count to -1.
     */
    public boolean checkInclusion2(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        if (m > n) {
            return false;
        }

        int[] s1Map = new int[26];
        for (char c : s1.toCharArray()) { // Letters in S1 has count range [0, x].
            s1Map[c - 'a']++;
        }
        for (int i = 0; i < 26; i++) { // Letters not in S1 are set to -1.
            if (s1Map[i] == 0) s1Map[i] = -1;
        }

        int len = m;
        for (int s = 0, e = 0; e < n; e++) {
            char c = s2.charAt(e);
            if (s1Map[c - 'a'] != -1) { // c is in S1.
                while (s1Map[c - 'a'] == 0 && s <= e) { // If c has been used up.
                    char start = s2.charAt(s);
                    s1Map[start - 'a']++;
                    len++;
                    s++;
                }
                s1Map[c - 'a']--;
                len--;
                if (len == 0) return true; // All letters are covered.
            } else {
                while (s < e) {
                    char start = s2.charAt(s);
                    s1Map[start - 'a']++;
                    s++;
                }
                s = e + 1;
                len = s1.length();
            }
        }
        return false;
    }

    /**
     * Two pointers.
     * The two pointers are representing a window in S2, such that:
     * 1. The window contains only letters in S1
     * 2. The letter frequency is smaller than or equal to the letters in S1
     * <p>
     * Then maintain this window to see if the substring just covered all letters in S1, no more or no less.
     * When a new letter in S2 is added to this window, cases are:
     * 1. The letter c is in S1.
     * | 1.1) c's count hasn't exceeded S1. Then just add c to the window, decrement its count and covered length.
     * |    If covered length reaches 0, all letters in S1 are covered by the substring. Return true.
     * | 1.2) c's count has reached S1, must first move the starting index until c is moved out of the window once.
     * |    Now there is a c to decrement. Decrement its count and covered length.
     * 2. The letter c is not in S1.
     * | Then the window must skip c. Otherwise we can't find a valid substring.
     * | First add whatever letter in the window back to the count. Basically reset.
     * | Then move starting index to 1 step after current ending index that points to c to skip it.
     * | Make sure the ending index then starts from the updated starting index.
     * | Also reset the length to s1.
     * Return false if there is no valid window found.
     */
    public boolean checkInclusion3(String s1, String s2) {
        Map<Character, Integer> letterToCounts = new HashMap<>();
        for (char c : s1.toCharArray()) {
            letterToCounts.merge(c, 1, (v1, v2) -> v1 + 1);
        }
        int len = s1.length();
        for (int s = 0, e = 0; e < s2.length(); e++) {
            char c = s2.charAt(e);
            if (letterToCounts.containsKey(c)) {
                while (letterToCounts.get(c) == 0 && s <= e) {
                    char start = s2.charAt(s);
                    letterToCounts.put(start, letterToCounts.get(start) + 1);
                    len++;
                    s++;
                }
                letterToCounts.put(c, letterToCounts.get(c) - 1);
                len--;
                if (len == 0) return true;
            } else {
                // Move s to e-1
                while (s < e) {
                    char start = s2.charAt(s);
                    letterToCounts.put(start, letterToCounts.get(start) + 1);
                    s++;
                }
                s = e + 1;
                len = s1.length();
            }
        }
        return false;
    }
}