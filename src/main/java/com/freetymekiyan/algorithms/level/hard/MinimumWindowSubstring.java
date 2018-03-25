package com.freetymekiyan.algorithms.level.hard;

/**
 * 76. Minimum Window Substring
 * <p>
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
 * Maximum, (M) Permutation in String, (H) Smallest Range, (H) Minimum Window Subsequence
 */
public class MinimumWindowSubstring {

    /**
     * Two Pointers. Hash Table.
     * Two pointers to represent a moving window.
     * Hash table to record count of letters in S and T.
     * To know whether all T's letters are covered, use an int array to represent a ASC-II charset.
     * Letters in T has positive count, letters in S has negative count.
     * For each letter in S, decrement its count.
     * If the count is > 0, then it's a letter in T.
     * If the count < 0, then it's a letter in S.
     * If the count = 0, it can be a letter that is not used in S or T,
     * or a letter in T that is currently fully covered in the window.
     */
    public String minWindow(String s, String t) {
        int[] letters = new int[256]; // Contains negative count of letters in s, positive count of letters in t.
        for (char c : t.toCharArray()) {
            letters[c]++;
        }
        int covered = 0;
        String window = "";
        int length = s.length();
        for (int start = 0, end = 0; end < s.length(); end++) {
            if (letters[s.charAt(end)] > 0) { // Is a T character.
                covered++;
            }
            letters[s.charAt(end)]--;
            while (covered == t.length()) { // All characters covered.
                int currentLength = end - start + 1;
                if (currentLength <= length) { // Note the <= sign, as length is initialized as s.length().
                    length = currentLength;
                    window = s.substring(start, end + 1);
                }
                // Move start.
                letters[s.charAt(start)]++;
                if (letters[s.charAt(start)] > 0) { // Removed a character in T.
                    covered--;
                }
                start++;
            }
        }
        return window;
    }

    /**
     * Hash Table. Two Pointers.
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
    public String minWindow2(String s, String t) {
        String res = "";
        int[] tCnt = new int[256]; // Assuming charset size 256.
        // Initialize substring character count map.
        for (char c : t.toCharArray()) {
            tCnt[c]++;
        }
        int minLen = Integer.MAX_VALUE;
        int count = t.length(); // Counter for whether the window is valid.
        for (int start = 0, end = 0; end < s.length(); end++) {
            /*
             * If current character is in t, the value of it should be > 0.
             * If it is > 0, it is a match, decrement counter by 1.
             * Decrement the character count in map as well.
             */
            if (tCnt[s.charAt(end)]-- > 0) {
                count--;
            }

            while (count == 0) { // If count is 0, all characters in t are matched.
                // Update minimum length and string.
                if (end - start + 1 < minLen) {
                    minLen = end - start + 1;
                    res = s.substring(start, end + 1);
                }
                /*
                 * Move start.
                 * Remove the character at the start of the window from t's map by increasing it's value.
                 * If it's value becomes > 0, there is some character in t that is removed.
                 */
                if (++tCnt[s.charAt(start++)] > 0) {
                    count++;
                }
            }
        }
        return res;
    }

    /**
     * Two pointers.
     * Avoid substring when there is a new minimum.
     * Store the starting position with an integer instead.
     */
    public String minWindow3(String s, String t) {
        int[] letters = new int[256];
        for (char c : t.toCharArray()) letters[c]++;
        int minLen = Integer.MAX_VALUE;
        int covered = 0;
        int head = 0;
        char[] chs = s.toCharArray();
        for (int start = 0, end = 0; end < s.length(); end++) {
            if (letters[chs[end]] > 0) covered++;
            letters[chs[end]]--;
            while (covered == t.length()) {
                int len = end - start + 1;
                if (len < minLen) {
                    minLen = len;
                    head = start;
                }
                letters[chs[start]]++;
                if (letters[chs[start]] > 0) {
                    covered--;
                }
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(head, head + minLen);
    }

    /**
     * https://discuss.leetcode.com/topic/30941/here-is-a-10-line-template-that-can-solve-most-substring-problems
     * For most substring problem, we are given a string and need to find a substring of it which satisfy some
     * restrictions.
     * A general way is to use a map assisted with two pointers. The template is given below.
     */
    private int findSubstring(String s) {
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