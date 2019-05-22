package com.freetymekiyan.algorithms.level.hard;

/**
 * 44. Wildcard Matching
 * <p>
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
   * DP. O(mn) Time. O(mn) Space. Can optimize to 1d array.
   * '*' can match empty or any sequence.
   * Recurrence Relation:
   * If p[j-1] != '*':
   * | s[i-1] and p[j-1] must match or p[j-1] is '?'. And s[0..i-2] matches p[0..j-2].
   * If p[j-1] == '*':
   * | If '*' means empty, s[0..i-1] must match p[0..j-2].
   * | If '*' means anything, s[0..i-2] must match p[0..j-1].
   * <p>
   * Base case:
   * When s and p are both empty, match.
   * When s is empty, p[i-1] must be '*' and p[0..i-2] match.
   */
  public boolean isMatch(String s, String p) {
    if (s == null && p == null) { // Two null's match with each other
      return true;
    }
    if (s == null || p == null) { // Only one null, don't match
      return false;
    }
    int m = s.length();
    int n = p.length();
    boolean[][] match = new boolean[m + 1][n + 1]; // Match result for different lengths
    match[0][0] = true;
    for (int i = 1; i <= n; i++) {
      if (p.charAt(i - 1) == '*') {
        match[0][i] = true;
      } else { // Pruning. If found 1 mismatch, the following won't match
        break;
      }
    }
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (p.charAt(j - 1) != '*') { // p[j-1] is not '*', last chars must match
          match[i][j] = match[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
        } else { // p[j-1] is '*'
          match[i][j] = match[i][j - 1] || match[i - 1][j]; // '*' is empty or '*' matches s[i-1]
        }
      }
    }
    return match[m][n];
  }

  /**
   * Two Pointers. Greedy. Backtracking.
   * Different from regex matching since '*' cannot shrink the preceding character.
   * '*' can match any sequence. So it there is a mismatch, fall back to previous * and start to match again.
   * Cases are:
   * 1) Most simple, there is no ? or * in pattern, each character needs to be matched.
   * 2) If we have ? in pattern, it can match any character.
   * 3) If we have * in pattern,
   * 3.1) It can match empty string.
   * 3.2) It can match anything sequence. (aaa and * match)
   * <p>
   * Implementation:
   * Two pointers, i for s and j for p.
   * Traverse s with i, check the characters of s and p:
   * 1) If j is in p's range and s[i] and p[j] match, advance both pointers.
   * 2) If j is in p's range and p[j] is *, save the asterisk's index, save the position in s, move j forward.
   * | This is because * can match one or more characters. We can retreat to * if there is a mismatch.
   * 3) If there a mismatch, and there is a *, skip the character in S.
   * | Also move pointer in j to one step after last asterisk's index. Check the rest.
   * 4) Mismatch and no asterisk to fall back, return false.
   * After s is fully matched, we still need to check whether there are more asterisks.
   */
  public boolean isMatch2(String s, String p) {
    if (s == null && p == null) {
      return true;
    }
    if (s == null || p == null) {
      return false;
    }
    int i = 0, j = 0;
    int prevMatch = 0, prevAster = -1; // Previous matched point and asterisk index
    while (i < s.length()) {
      if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
        // Same characters or '?', move both pointers
        i++;
        j++;
      } else if (j < p.length() && p.charAt(j) == '*') { // Found '*' in p
        prevMatch = i; // Save current matched index of S in string
        prevAster = j; // Save asterisk index. Later we can retreat if there is one
        j++; // Move pattern pointer forward. Suppose * matches with an empty sequence
      } else if (prevAster != -1) { // Mismatch, fallback to previous '*'
        prevMatch++; // Match the difference with *, so move match forward
        i = prevMatch; // Reset i to start matching again
        j = prevAster + 1; // Reset j to after *
      } else { // Not ?, not same characters, no *, just don't match
        return false;
      }
    }
    // Check remaining characters in pattern, can only be asterisk
    while (j < p.length() && p.charAt(j) == '*') {
      j++;
    }
    return j == p.length(); // No other chars in pattern
  }
}