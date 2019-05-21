package com.freetymekiyan.algorithms.level.easy;

/**
 * 28. Implement strStr()
 * <p>
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
   * Two Pointers.
   * For i from 0 to m-n:
   * | For j from 0 to n-1:
   * |   If characters are not the same, break
   * |   If j reaches the end of needle, return i.
   * Return -1.
   * Special case:
   * If needle is empty, no need to check , just return 0.
   */
public int strStr(String haystack, String needle) {
  if (needle.isEmpty()) {
    return 0;
  }
  int m = haystack.length();
  int n = needle.length();
  // Recurse from 0 to m - n, inclusive
  // Since from m-n+1 to m-1, there are only m-1-(m-n+1)+1 = n-1 characters
  // Not enough to form a needle
  for (int i = 0; i <= m - n; i++) {
    for (int j = 0; j < n; j++) { // Compare each char with needle from the beginning
      if (haystack.charAt(i + j) != needle.charAt(j)) {
        break;
      }
      if (j == n - 1) { // Found all n characters
        return i;
      }
    }
  }
  return -1; // Not found
}
}