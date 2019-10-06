package com.freetymekiyan.algorithms.level.easy;

/**
 * 408. Valid Word Abbreviation
 * <p>
 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
 * <p>
 * A string such as "word" contains only the following valid abbreviations:
 * <p>
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * <p>
 * Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a
 * valid abbreviation of "word".
 * <p>
 * Note:
 * Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
 * <p>
 * Example 1:
 * Given s = "internationalization", abbr = "i12iz4n":
 * Return true.
 * <p>
 * Example 2:
 * Given s = "apple", abbr = "a2e":
 * Return false.
 * <p>
 * Related Topics: String
 * <p>
 * Companies: Google
 * <p>
 * Similar Problems: Minimum Unique Word Abbreviation (H), Word Abbreviation (H)
 */
public class ValidWordAbbreviation {

  /**
   * Word and abbreviation don't match if:
   * <ol>
   *   <li>The lengths are different</li>
   *   <li>The actual characters at specific positions are different</li>
   * </ol>
   * So iterate through abbreviation, track the position and compare the characters.
   * If there is a number, find the full number first then update indices.
   * The number abbreviates that many characters.
   * <p>
   * If there's no number, just compare the characters.
   * <p>
   * Both indices should be at the ends if it's a perfect match.
   */
  public boolean validWordAbbreviation(String word, String abbr) {
    if (word == null && abbr == null) {
      return true;
    }
    if (word == null || abbr == null) {
      return false;
    }
    int pWord = 0;
    int pAbbr = 0;
    while (pWord < word.length() && pAbbr < abbr.length()) {
      int end = pAbbr;
      while (end < abbr.length() && '0' <= abbr.charAt(end) && abbr.charAt(end) <= '9') {
        end++;
      }
      if (end == pAbbr) { // No number, compare characters
        if (abbr.charAt(pAbbr) != word.charAt(pWord)) {
          return false;
        }
        pAbbr++;
        pWord++;
      } else {
        if (abbr.charAt(pAbbr) == '0') { // Edge case: should not have leading zero
          return false;
        }
        final int abbreviated = Integer.parseInt(abbr.substring(pAbbr, end));
        pWord += abbreviated;
        pAbbr = end;
      }
    }
    return pWord == word.length() && pAbbr == abbr.length();
  }
}