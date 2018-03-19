package com.freetymekiyan.algorithms.level.hard;

/**
 * 65. Valid Number
 * <p>
 * Validate if a given string is numeric.
 * <p>
 * Some examples:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before
 * implementing one.
 * <p>
 * Update (2015-02-10):
 * The signature of the C++ function had been updated. If you still see your function signature accepts a const char *
 * argument, please click the reload button to reset your code definition.
 * <p>
 * Company Tags: LinkedIn
 * Tags: Math, String
 * Similar Problems: (E) String to Integer (atoi)
 */
public class ValidNumber {

    /**
     * Trim heading and trailing whitespaces.
     * Use 3 booleans to remember whether dot, exp, and number have shown.
     * Then check violations.
     * Situations are:
     * 1) Number: No violations. Just set hasNum to true.
     * 2) Dot: Cannot appear after previous dot or 'e'. Then set hasDot to true.
     * 3) Exp: Cannot appear after previous 'e' or when there is no number yet.
     * |       Then set hasExp to true. IMPORTANT!!! Set hasNum to false.
     * 4) Signs: Can only appear at first or right after e. Otherwise return false.
     * 5) All other characters, like whitespace, or abc: Return false.
     */
    public boolean isNumber(String s) {
        s = s.trim(); // Remove whitespaces first.
        boolean hasDot = false;
        boolean hasExp = false;
        boolean hasNum = false;
        char[] num = s.toCharArray();
        for (int i = 0; i < num.length; i++) {
            if ('0' <= num[i] && num[i] <= '9') { // Is digit.
                hasNum = true;
            } else if (num[i] == '.') { // Is dot.
                if (hasExp || hasDot) { // Cannot appear after exp or dot.
                    return false;
                }
                hasDot = true;
            } else if (num[i] == 'e') { // Is exp.
                if (hasExp || !hasNum) { // Cannot appear after exp or before number.
                    return false;
                }
                hasNum = false; // NOTE here reset the hasNum flag. Avoid 1e.
                hasExp = true;
            } else if (num[i] == '-' || num[i] == '+') { // Is sign.
                if (i != 0 && num[i - 1] != 'e') { // Must be first or after 'e'.
                    return false;
                }
            } else { // All other characters are not allowed.
                return false;
            }
        }
        return hasNum;
    }

    /**
     * Two Pointers.
     * Whitespace, +, -, digit, ., e.
     * First skip whitespaces at the two ends with two pointers i and e.
     * If out of bound, return false.
     * Then skip possible leading signs like + or -.
     * These signs can still appear later after "e", though.
     * Use three booleans to indicate isDigit, isDot, and isExp.
     * For each char c from i to e:
     * | If c is a digit, set isDigit to true.
     * | Else if c is a dot, check if 'e' or '.' already appeared
     * |   If appeared, return false. '.' cannot appear after 'e' or previous '.'.
     * |   Else set isDot to true, means dot appeared.
     * | Else if c is an 'e':
     * |   If 'e' already appeared or there is no number before 'e', return false.
     * |   Else set isExp to true. And set isDigit to false.
     * | Else if c is '+' or '-', check if it is after e.
     * |   If previous char is not 'e', return false.
     * | All other cases, return false.
     * | Move i forward.
     * After the check return whether it's a number.
     */
    public boolean isNumber2(String s) {
        int len = s.length();
        int i = 0, e = len - 1;
        // Skip whitespaces
        while (i <= e && Character.isWhitespace(s.charAt(i))) {
            i++;
        }
        if (i > len - 1) {
            return false;
        }
        while (e >= i && Character.isWhitespace(s.charAt(e))) {
            e--;
        }
        // Skip leading +/-.
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            i++;
        }
        // Check remain.
        boolean hasNum = false; // is a digit
        boolean hasDot = false; // is a '.'
        boolean hasExp = false; // is a 'e'
        while (i <= e) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                hasNum = true;
            } else if (c == '.') { // '.' appear
                if (hasExp || hasDot) {
                    return false; // exp can't have '.' or dots
                }
                hasDot = true;
            } else if (c == 'e') { // e appear
                if (hasExp || hasNum == false) {
                    return false; // already e but not num
                }
                hasExp = true; // is exp, see whether is num from now
                hasNum = false;
            } else if (c == '+' || c == '-') { // +, - must appear after e
                if (s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
            i++;
        }
        return hasNum; // whether is num or not
    }

    /**
     * Trim and convert trimmed string to char array
     * Deal with sign
     * Deal with point
     * Deal with digit after point
     * Deal with no point or e
     * Deal with digit after e
     * Deal with sign, no digit and other character cases
     */
    public boolean isNumber3(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        char[] c = s.trim().toCharArray();
        if (c.length == 0) {
            return false; // all whitespaces
        }
        int i = 0;
        int num = 0;
        if (c[0] == '+' || c[0] == '-') {
            i++; // skip sign
        }
        for (; i < c.length && (c[i] >= '0' && c[i] <= '9'); i++) {
            num++;
        }
        if (i < c.length && c[i] == '.') {
            i++; // skip point
        }

        for (; i < c.length && (c[i] >= '0' && c[i] <= '9'); i++) {
            num++; // !
        }
        if (num == 0) {
            return false; // no digit before or after point
        }

        if (i == c.length) {
            return true; // no point or e
        } else if (i < c.length && c[i] != 'e') {
            return false; // last letter not e
        } else {
            i++; // skip e
        }

        num = 0; // reset num and check numbers after e
        if (i < c.length && (c[i] == '+' || c[i] == '-')) {
            i++;
        }
        for (; i < c.length && (c[i] >= '0' && c[i] <= '9'); i++) {
            num++;
        }
        if (num == 0) {
            return false; // no more numbers after e
        }
        if (i == c.length) {
            return true; // no other letter except e
        } else {
            return false; // other letter shows up
        }
    }
}