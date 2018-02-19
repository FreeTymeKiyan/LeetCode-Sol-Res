package com.freetymekiyan.algorithms.other;

import java.util.Objects;

/**
 * Given two double values as strings, return the sum of them as a string.
 * You may not convert the string to a double.
 * <p>
 * E.g. "1.13" + "0.9" => "2.03"
 * Company Tags: Facebook
 */
public class AddTwoDoubles {

    public static final String DOT_SEPARATOR = "\\.";
    public static final String DOT = ".";

    /**
     * Math.
     * We may separate the string with '.'.
     * Then we will have the left and right parts.
     * First deal with right part, adding from right to left.
     * The shorter decimal should be padded by some 0.
     * Then we will have the result of right parts and a carry.
     * The carry should be added to left part.
     * We add the left part and concatenate left and right parts with dot.
     * Return the concatenated result.
     * <p>
     * Do I have to trim the trailing 0's? I think so.
     */
    public String addDoubles(String s1, String s2) {
        Objects.requireNonNull(s1);
        Objects.requireNonNull(s2);
        // Assuming that s1 and s2 are both valid numbers. No check.
        String s1Left = s1;
        String s1Right = "";
        if (s1.contains(DOT)) {
            String[] s1Splits = s1.split(DOT_SEPARATOR);
            s1Left = s1Splits[0];
            s1Right = s1Splits[1];
        }
        String s2Left = s2;
        String s2Right = "";
        if (s2.contains(DOT)) {
            String[] s2Splits = s2.split(DOT_SEPARATOR);
            s2Left = s2Splits[0];
            s2Right = s2Splits[1];
        }

        // Add decimals. Keep result and carry.
        StringBuilder res = new StringBuilder();
        int carry = 0;
        for (int i = Math.max(s1Right.length(), s2Right.length()) - 1; i >= 0; i--) {
            int i1 = i >= s1Right.length() ? 0 : s1Right.charAt(i) - '0';
            int i2 = i >= s2Right.length() ? 0 : s2Right.charAt(i) - '0';
            int sum = i1 + i2 + carry;
            carry = sum / 10;
            if (i == Math.max(s1Right.length(), s2Right.length()) - 1 && sum % 10 == 0) {
                // Don't insert trailing zeroes.
                continue;
            }
            res.insert(0, sum % 10);
        }
        if (res.length() == 0) res.insert(0, 0);
        res.insert(0, DOT);
        // Add integrals. Add carry and two string.
        for (int i = s1Left.length() - 1, j = s2Left.length() - 1; i >= 0 || j >= 0 || carry != 0; i--, j--) {
            int i1 = i >= 0 ? s1Left.charAt(i) - '0' : 0;
            int i2 = j >= 0 ? s2Left.charAt(j) - '0' : 0;
            int sum = i1 + i2 + carry;
            res.insert(0, sum % 10);
            carry = sum / 10;
        }
        return res.toString();
    }
}