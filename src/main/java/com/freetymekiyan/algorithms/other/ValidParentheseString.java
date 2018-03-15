package com.freetymekiyan.algorithms.other;

/**
 * Given a string that contains '(', ')' and other alpha numeric characters, return a string with valid parentheses.
 * <p>
 * Example
 * "(()das10())" -> "(()())"
 * "()()())" -> "()()()"
 * ")(" -> ""
 * "((((((" -> ""
 * "))))))" -> ""
 * <p>
 * Company: Facebook
 */
public class ValidParentheseString {

    /**
     * Remove invalid parentheses as we traverse through the string, character by character.
     * Def. of an invalid parentheses is that it makes the current string have unmatched pairs.
     * Maintain a counter of "(".
     * If there is a "(", count += 1.
     * If there is a ")", count -= 1.
     * A valid string should in the end have count == 0 and during traversal the count never goes below 0.
     * So if count < 0 after count -= 1, must skip ")" to keep the string valid.
     * If count > 0 in the end, must remove "(" from behind to make the string valid.
     * <p>
     * A harder version is LC 301 which asks for all possibilities.
     */
    public String getValidString(String s) {
        StringBuilder sb = new StringBuilder(s);
        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    sb.deleteCharAt(i);
                    i--; // Reset the index and count since after deletion the rest of the string move left by 1.
                    count = 0;
                }
            }
        }
        for (int i = sb.length() - 1; i >= 0 && count > 0; i--) {
            if (sb.charAt(i) == '(') {
                sb.deleteCharAt(i);
                count--;
            }
        }
        return sb.toString();
    }
}