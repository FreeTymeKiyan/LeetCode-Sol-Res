package com.freetymekiyan.algorithms.level.hard;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
 * complexity O(n).
 * <p>
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * <p>
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * <p>
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in
 * S.
 * <p>
 * Company Tags: LinkedIn, Uber, Facebook
 * Tags: Hash Table, Two Pointers, String
 * Similar Problems: (H) Substring with Concatenation of All Words, (M) Minimum Size Subarray Sum, (H) Sliding Window
 * Maximum
 */
public class MinWindowSubstring {

    /**
     * Hash Table, two pointers.
     * <p>
     * 1. Use two pointers: start and end to represent a window.
     * 2. Move end to find a valid window.
     * 3. When a valid window is found, move start to find a smaller window.
     * <p>
     * Use map to store count of each character in a String.
     * One for T and the other for the window in S.
     * Use two pointers to traverse S, the String in between is the window.
     * The pointer in the front moves when the window doesn't cover all characters in T.
     * The pointer in the back moves when the window covers all characters, to get as smaller window as possible.
     * Stop when the front pointer reaches the end.
     * We need a variable to remember the minimum window length during this procedure.
     * How to know whether T is covered by the window with two maps? Comparing them would be costly.
     * Use a variable called letterCnt to keep track of the letters covered.
     * When the character is in T, and the count in window is less than or equal to the count in T, increase letterCnt.
     * When letterCnt >= length of T, we know that T is fully covered.
     */
    public String minWindow(String s, String t) {
        String res = "";
        int[] tCnt = new int[256]; // Assuming charset size 256
        // Initialize substring map
        for (char c : t.toCharArray()) {
            tCnt[c]++;
        }
        int minLen = Integer.MAX_VALUE;
        int count = t.length(); // Counter for whether the window is valid
        for (int start = 0, end = 0; end < s.length(); end++) {
            /*
             * If current character is in t, the value of it should be > 0.
             * If it is > 0, it is a match, decrement count by 1.
             * Decrement the map for t as well.
             */
            if (tCnt[s.charAt(end)]-- > 0) {
                count--;
            }

            while (count == 0) { // If count is 0, all characters in t are matched.
                /*
                 * Update minimum length and result
                 */
                if (end - start + 1 < minLen) {
                    minLen = end - start + 1;
                    res = s.substring(start, end + 1);
                }
                /*
                 * Remove the character at the start of the window from t's map
                 * by increasing it's value.
                 * If it's value is > 0, there is some character in t that is removed.
                 */
                if (++tCnt[s.charAt(start++)] > 0) {
                    count++;
                }
            }
        }
        return res;
    }

    /**
     * https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems
     * For most substring problem, we are given a string and need to find a substring of it which satisfy some
     * restrictions.
     * A general way is to use a map assisted with two pointers. The template is given below.
     */
    int findSubstring(String s) {
        int[] map = new int[128];
        int counter; // Check whether the substring is valid
        int begin = 0, end = 0; // Two pointers, one point to tail and one head
        int d = Integer.MAX_VALUE; // The length of substring

        // for () { /* Initialize the hash map here */ }

        while (end < s.length()) { // Traverse s

            // if (map[s[end++]]-- ?) {  /* modify counter here */ }

            // while (/* counter condition */) {

                 /* update d here if finding minimum*/

            //increase begin to make it invalid/valid again

            // if (map[s[begin++]]++ ?) { /*modify counter here*/ }
            //}

            /* update d here if finding maximum*/
        }
        return d;
    }
}
