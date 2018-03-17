package com.freetymekiyan.algorithms.level.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. Letter Case Permutation
 * <p>
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create.
 * <p>
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 * <p>
 * Input: S = "12345"
 * Output: ["12345"]
 * Note:
 * <p>
 * S will be a string with length at most 12.
 * S will consist only of letters or digits.
 * <p>
 * Related Topics: Backtracking, Bit Manipulation
 * Similar Questions: (M) Subsets
 */
public class LetterCasePermutation {

    /**
     * Backtracking
     * Since S is alphanumerical, numbers can be skipped and only check letters.
     * To generate all possible strings, all possibilities must be searched.
     * How? By backtracking with the letter itself and its opposite case.
     */
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        dfs(S.toCharArray(), 0, res);
        return res;
    }

    private void dfs(char[] s, int pos, List<String> res) {
        if (pos == s.length) {
            res.add(new String(s));
            return;
        }
        dfs(s, pos + 1, res); // If it's a number or a letter, just recurse on.
        if (Character.isLetter(s[pos])) { // If it's a letter, recurse the other case.
            s[pos] ^= 1 << 5; // Only the 6th significant bit is different of different cases.
            dfs(s, pos + 1, res);
            s[pos] ^= 1 << 5;
        }
    }
}