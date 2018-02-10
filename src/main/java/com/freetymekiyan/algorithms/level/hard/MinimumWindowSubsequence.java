package com.freetymekiyan.algorithms.level.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 727. Minimum Window Subsequence
 * <p>
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
 * <p>
 * If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple
 * such minimum-length windows, return the one with the left-most starting index.
 * <p>
 * Example 1:
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 * Note:
 * <p>
 * All the strings in the input will only contain lowercase letters.
 * The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].
 * <p>
 * Companies: Google, eBay
 * Related Topics: Dynamic Programming
 * Similar Questions: (H) Minimum Window Substring, (E) Longest Continuous Increasing Subsequence
 */
public class MinimumWindowSubsequence {

    /**
     * DP.
     * State:
     * The starting index (+1) of the valid window in S where T has length i and S has length j.
     * Base case:
     * T is empty, starting index is always j+1.
     * Recurrence Relation:
     * If T[i-1] == S[j-1], a new letter in T is found in S. Then the starting index of this window is the same as the
     * starting index of the previous window that covers T[0:i-2] in S[0:j-2], which is dp[i-1][j-1].
     * If T[i-1] != S[j-1], we can only update the starting index with S[0:j-1] that covers T[0:i-1].
     * <p>
     * Once all the starting indices are ready, check the last row of the dp array, which covers all letters in T.
     * Get the min length window and its starting index.
     * Return empty string if there is no min length. Otherwise return the substring.
     * <p>
     * Note that the integers in dp array is by default 0. So 0 is used to indicate that there is no valid window.
     */
    public String minWindow(String S, String T) {
        int m = T.length();
        int n = S.length();
        int[][] dp = new int[m + 1][n + 1];
        // 0 is used to represent the initial state that doesn't have a valid window.
        // So all starting index are starting from 1.
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        int start = 0;
        int min = n + 1;
        for (int j = 1; j <= n; j++) {
            if (dp[m][j] != 0) { // There is a window.
                if (j - dp[m][j] + 1 < min) { // Actually formula is: j - 1 - (dp[m][j] - 1) + 1 since j is the length.
                    start = dp[m][j] - 1; // -1 is the offset introduced in the beginning.
                    min = j - dp[m][j] + 1;
                }
            }
        }

        return min == n + 1 ? "" : S.substring(start, start + min);
    }

    /**
     * Hash Table. Pre-computing.
     * Find the index of the next character in T in S.
     * Instead of searching in S every time, we may iterate S once to pre-compute the next index of a letter in S[i:].
     * Then for each window that already has T's first character, expand them to cover T.
     * From T's 2nd character to the end, each window:
     * | If the window's ending index is not the end, and next character is not -1, update window's ending index.
     * | Otherwise, the window cannot cover T, set the window to [-1,-1].
     * | And all the following windows cannot cover T either since they are even smaller. Just break and skip them.
     * Finally, find a smallest window from the available windows.
     * The answer is initialized as [-1, N]. The is even larger than the largest possible window.
     * For each window:
     * | If it starts with -1, break and skip all the windows left.
     * | If not, compare with current minimum. If smaller, update minimum.
     * Return the string cut by the window if there is a smallest window. Otherwise return empty string.
     */
    public String minWindow2(String S, String T) {
        int N = S.length();
        int[] last = new int[26];
        Arrays.fill(last, -1); // Set all indices to -1 to indicate invalid state.

        // Iterate S backwards to get the closest letter in S after i.
        int[][] next = new int[N][26];
        for (int i = N - 1; i >= 0; i--) {
            last[S.charAt(i) - 'a'] = i;
            for (int j = 0; j < 26; j++) { // Update the next index for each 26 letters.
                next[i][j] = last[j];
            }
        }

        // Initialize all possible windows.
        List<int[]> windows = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (S.charAt(i) == T.charAt(0)) {
                windows.add(new int[]{i, i}); // First i is the starting index. Second i is the start of ending index.
            }
        }
        for (int i = 1; i < T.length(); i++) { // Build the final window from the 2nd char in T.
            for (int[] window : windows) {
                if (window[1] < N - 1 && next[window[1] + 1][T.charAt(i) - 'a'] != -1) {
                    window[1] = next[window[1] + 1][T.charAt(i) - 'a']; // Expand the ending index.
                } else { // window[1] == N - 1, ending index reaches the end. OR next == -1, no character in S. Can't cover T.
                    window[0] = window[1] = -1;
                    break;
                }
            }
        }

        int[] min = {-1, N}; // Longer than the largest possible window.
        for (int[] window : windows) {
            if (window[0] == -1) {
                // Break since all the following windows will have larger starting index.
                // It'd be impossible for them to cover T.
                break;
            }
            if (window[1] - window[0] < min[1] - min[0]) {
                min = window;
            }
        }
        return min[0] > 0 ? S.substring(min[0], min[1] + 1) : "";
    }
}