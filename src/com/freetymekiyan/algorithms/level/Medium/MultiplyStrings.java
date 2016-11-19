package com.freetymekiyan.algorithms.level.medium;

/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * <p>
 * Note:
 * The numbers can be arbitrarily large and are non-negative.
 * Converting the input string to integer is NOT allowed.
 * You should NOT use internal library such as BigInteger.
 * <p>
 * Company Tags: Facebook, Twitter
 * Tags: Math, String
 * Similar Problems: (M) Add Two Numbers, (E) Plus One, (E) Add Binary, (E) Add Strings
 */
public class MultiplyStrings {

    /**
     * Math. String.
     * How to do multiplication?
     * Start from right to left, multiply each pair of digits, and add them together.
     * Result num1[i] * num2[j] will be placed at i + j and i + j + 1.
     * Mimic this process.
     * Special cases:
     * 1) If one of the strings is null, return empty.
     * 2) If one of the strings is zero, return zero.
     */
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "";
        }
        if ("0".equals(num1) || "0".equals(num2)) { // If one number is 0.
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] product = new int[m + n]; // Length is at most m + n.
        // Pick one digit from one number, multiply with each digit in the other number.
        for (int i = m - 1; i >= 0; i--) { // From right to left, from lower significant digit to higher.
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1; // p1 is a more significant digit than p2.
                int sum = mul + product[p2];
                product[p1] += sum / 10; // Carry.
                product[p2] = sum % 10;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int p : product) {
            if (p != 0 || ans.length() != 0) { // Not zero, or not empty.
                ans.append(p);
            }
        }
        return ans.toString();
    }
}
