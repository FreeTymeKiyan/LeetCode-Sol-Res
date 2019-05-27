package com.freetymekiyan.algorithms.level.hard;

/**
 * 358. Rearrange String k Distance Apart
 * <p>
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance
 * k from each other.
 * <p>
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty
 * string "".
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aabbcc", k = 3
 * Output: "abcabc"
 * Explanation: The same letters are at least distance 3 from each other.
 * Example 2:
 * <p>
 * Input: s = "aaabc", k = 3
 * Output: ""
 * Explanation: It is not possible to rearrange the string.
 * Example 3:
 * <p>
 * Input: s = "aaadbbcc", k = 2
 * Output: "abacabcd"
 * Explanation: The same letters are at least distance 2 from each other.
 * <p>
 * Companies: Google
 * <p>
 * Related Topics: Hash Table, Heap, Greedy
 * <p>
 * Similar Questions: (M) Task Scheduler, (M) Reorganize String
 */
public class RearrangeStringKDistanceApart {

  public String rearrangeString(String str, int k) {
    int length = str.length();
    int[] count = new int[26];
    int[] valid = new int[26];
    for (int i = 0; i < length; i++) {
      count[str.charAt(i) - 'a']++;
    }
    StringBuilder sb = new StringBuilder();
    for (int index = 0; index < length; index++) {
      int candidatePos = findValidMax(count, valid, index);
      if (candidatePos == -1) return "";
      count[candidatePos]--;
      valid[candidatePos] = index + k;
      sb.append((char) ('a' + candidatePos));
    }
    return sb.toString();
  }

  private int findValidMax(int[] count, int[] valid, int index) {
    int max = Integer.MIN_VALUE;
    int candidatePos = -1;
    for (int i = 0; i < count.length; i++) {
      if (count[i] > 0 && count[i] > max && index >= valid[i]) {
        max = count[i];
        candidatePos = i;
      }
    }
    return candidatePos;
  }
}