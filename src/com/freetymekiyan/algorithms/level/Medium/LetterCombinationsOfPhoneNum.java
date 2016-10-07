package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
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

    private static final String[] LETTERS = {
        "",     // 0
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
    private LetterCombinationsOfPhoneNum l;

    /**
     * Backtracking. DFS.
     * Create a result collection and pass it into a backtracking helper.
     * The helper is like a DFS function that generates all possible combinations by:
     * 1) Assign one letter at current level
     * 2) Call the helper recursively to generate the rest
     * Stop when we reach the end of the digits string, and add the combination to result.
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        helper(digits, 0, "", res);
        return res;
    }

    private void helper(String digits, int s, String comb, List<String> res) {
        if (s == digits.length()) { // all digits done, stop
            res.add(comb);
            return;
        }
        String c = LETTERS[digits.charAt(s) - '0']; // note how to get int index
        for (int i = 0; i < c.length(); i++) { // note its i starts from 0
            helper(digits, s + 1, comb + c.charAt(i), res); // backtracking
        }
    }

    /**
     * BFS.
     * Build combination level by level.
     * The length of the combination is the same as the level.
     * Add all possible letters to each of the result in previous level.
     */
    public List<String> letterCombinationsBFS(String digits) {
        LinkedList<String> queue = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return queue;
        }
        queue.add("");
        for (int i = 0; i < digits.length(); i++) {
            char[] letters = LETTERS[digits.charAt(i) - '0'].toCharArray();
            while (queue.peek().length() == i) { // Get all at this level
                String s = queue.poll();
                for (char l : letters) {
                    queue.offer(s + l);
                }
            }
        }
        return queue;
    }

    @Before
    public void setUp() {
        l = new LetterCombinationsOfPhoneNum();
    }

    @Test
    public void testExamples() {
        l.letterCombinations("22");
    }

    @After
    public void tearDown() {
        l = null;
    }
}
