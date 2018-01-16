package com.freetymekiyan.algorithms.level.medium;

/**
 * 678. Valid Parenthesis String
 * <p>
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this
 * string is valid. We define the validity of a string by these rules:
 * <p>
 * 1. Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * 2. Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * 3. Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * 4. '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 * 5. An empty string is also valid.
 * <p>
 * Example 1:
 * Input: "()"
 * Output: True
 * Example 2:
 * Input: "(*)"
 * Output: True
 * Example 3:
 * Input: "(*))"
 * Output: True
 * Note:
 * The string size will be in the range [1, 100].
 * <p>
 * Companies: Alibaba
 * Related Topics: String
 */
public class ValidParenthesisString {

    /**
     * The range of left parentheses.
     * If we have a '(', lo++, otherwise it can be a ')', lo--.
     * If we have a ')', hi--, otherwise it can be a '(', hi++.
     * If hi < 0, then we have too many ')', return false.
     * If lo < 0, then there are too many ')' making the string invalid.
     * We should reset lo to 0.
     */
    public boolean checkValidString(String s) {
        int lo = 0, hi = 0;
        for (char c : s.toCharArray()) {
            lo += (c == '(' ? 1 : -1);
            hi += (c == ')' ? -1 : 1);
            if (hi < 0) return false;
            if (lo < 0) lo = 0;
        }
        return lo == 0;
    }

    /**
     * Recursive.
     * This is a search problem.
     * Recurrence relation:
     * The whole string is valid if:
     * Goes without * is valid or goes with left it's valid or goes with right it's valid.
     * The rest of the string must consume all existing left parentheses.
     * Base case:
     * 1. empty string is valid.
     */
    public boolean checkValidString2(String s) {
        if ((s == null || s.isEmpty())) return true;
        return checkValidString2(s, 0);
    }

    private boolean checkValidString2(String s, int numLeft) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                numLeft++;
            } else if (c == ')') {
                if (numLeft == 0) return false; // Too many right parentheses.
                numLeft--;
            } else { // c == '*'
                String skip = s.substring(i + 1);
                return checkValidString2(skip, numLeft)
                        /*
                         * TODO: 1/16/18 We already traversed with * as empty sequence.
                         * We should know the result of the substring already.
                         * And save it somewhere!
                         * Can develop a top-down or memoization approach to avoid duplicates.
                         */
                        || checkValidString2("(" + skip, numLeft)
                        || checkValidString2(")" + skip, numLeft);
            }
        }
        return numLeft == 0;
    }
}