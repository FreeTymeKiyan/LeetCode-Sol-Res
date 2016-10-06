package com.freetymekiyan.algorithms.level.easy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Given two binary strings, return their sum (also ab binary string).
 * <p>
 * For example,
 * ab = "11"
 * b = "1"
 * Return "100".
 * <p>
 * Company Tags: Facebook
 * Tags: Math, String
 * Similar Problems: (M) Add Two Numbers, (M) Multiply Strings, (E) Plus One
 */
class AddBinary {

    private AddBinary ab;

    /**
     * Math, String.
     * From right to left, do it digit-by-digit.
     * Get current digits of ab and b, add them up.
     * Also use an integer to store carry from the previous addition.
     * Store the sum to result and update carry for each round.
     * Stop when longest string is reached.
     * Remember to check carry before return, if carry is 1, it should still be inserted to the result.
     */
    public String addBinary(String a, String b) {
        int m = a.length();
        int n = b.length();
        int carry = 0;
        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < m || i < n) { // The longer one of ab and b
            int p = i < m ? a.charAt(m - 1 - i) - '0' : 0;
            int q = i < n ? b.charAt(n - 1 - i) - '0' : 0;
            int temp = p + q + carry; // Sum of current digits and previous carry
            carry = temp / 2; // temp can be 0, 1, 2, 3. When temp >= 2, carry = 1; otherwise, carry = 0
            res.insert(0, temp % 2); // When temp is odd, result is 1; otherwise, result is 0
            i++;
        }
        return carry == 0 ? res.toString() : "1" + res.toString(); // Don't forget the carry at last.
    }

    // add 0 and calculate one by one
    public String addBinaryOwn(String a, String b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        StringBuilder result = new StringBuilder();
        int lenA = a.length();
        int lenB = b.length();
        int i = lenA - 1;
        int j = lenB - 1;

        boolean carry = false;
        while (i > -1 || j > -1) {
            char c1 = i > -1 ? a.charAt(i) : '0';
            char c2 = j > -1 ? b.charAt(j) : '0';
            if (c1 == '1' && c2 == '1') {
                if (carry) {
                    result.append(1);
                } else {
                    result.append(0);
                }
                carry = true; // set carry for next digit
            } else if (c1 == '1' || c2 == '1') {
                if (carry) {
                    result.append(0);
                    carry = true; // set carry for next digit
                } else {
                    result.append(1);
                    carry = false;
                }
            } else {
                if (carry) {
                    result.append(1);
                } else {
                    result.append(0);
                }
                carry = false;
            }
            i--;
            j--;
        }
        if (carry) {
            result.append('1');
        }
        return result.reverse().toString();
    }

    @Before
    public void setUp() {
        ab = new AddBinary();
    }

    @Test
    public void testExamples() {
        String a = "11110011001011110111110001010000111110011110101100011111010010001000001101111001000111";
        String b = "111001011011111010001001111001100000101010000101100010101100010010010011011000";
        // String a = "1010";
        // String b = "1011";
        System.out.println(ab.addBinary(a, b));
        System.out.println(ab.addBinaryOwn(a, b));
    }

    @After
    public void tearDown() {
        ab = null;
    }
}
