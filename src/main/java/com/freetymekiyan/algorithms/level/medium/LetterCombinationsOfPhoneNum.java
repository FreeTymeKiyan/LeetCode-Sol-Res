package com.freetymekiyan.algorithms.level.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 17. Letter Combinations of a Phone Number
 * <p>
 * Given a digit string, return all possible letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * <p>
 * http://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png
 * <p>
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * <p>
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 * <p>
 * Company Tags: Amazon, Dropbox, Google, Uber, Facebook
 * Tags: Backtracking, String
 * Similar Problems: (M) Generate Parentheses, (M) Combination Sum, (E) Binary Watch
 */
public class LetterCombinationsOfPhoneNum {

  /** Characters to number mappings of a phone */
  private static final String[] LETTERS = {
      " ",    // 0
      "",     // 1
      "abc",  // 2
      "def",  // 3
      "ghi",  // 4
      "jkl",  // 5
      "mno",  // 6
      "pqrs", // 7
      "tuv",  // 8
      "wxyz"  // 9
  };

  /**
   * Backtracking. DFS.
   * The backtrack is like a DFS function that generates all possible combinations by:
   * 1) Assign one letter at current level
   * 2) Call the backtrack recursively to generate the rest
   * Stop when we reach the end of the digits string, and add the combination to result.
   */
  public List<String> letterCombinations(String digits) {
    if (digits == null || digits.isEmpty()) { // When digits is "", should return empty.
      return Collections.emptyList();
    }
    final List<String> res = new ArrayList<>();
    backtrack(digits, 0, "", res);
    return res;
  }

  /**
   * Backtracking.
   * Generate combinations position by position.
   * Get current position's possible letters.
   * Append to the combination so far.
   * Pass the subset and generated combination to the next call.
   * When all digits are used, add combination to the result collection.
   */
  private void backtrack(String digits, int start, String letters, List<String> res) {
    if (start == digits.length()) {
      res.add(letters);
      return;
    }

    String current = LETTERS[digits.charAt(start) - '0'];
    for (char c : current.toCharArray()) {
      backtrack(digits, start + 1, letters + c, res);
    }
  }

  /**
   * BFS.
   * Build combination level by level.
   * The length of the combination is the same as the level.
   * Add all possible letters to each of the result in previous level.
   */
  public List<String> letterCombinations2(String digits) {
    if (digits == null || digits.length() == 0) {
      return Collections.emptyList();
    }
    final List<String> combs = new LinkedList<>();
    combs.add("");
    for (char c : digits.toCharArray()) {
      char[] letters = LETTERS[c - '0'].toCharArray();
      for (int i = combs.size(); i > 0; i--) {
        String s = combs.remove(0);
        for (char l : letters) {
          combs.add(s + l);
        }
      }
    }
    return combs;
  }
}