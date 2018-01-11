package com.freetymekiyan.algorithms.level.easy;

/**
 * 67. Add Binary
 * <p>
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

    /**
     * Math. String.
     * Initialize two pointers i and j at the end of a and b.
     * Use one integer c for the carry.
     * While i >= 0 or j >= 0 or c == 1:
     * | Add char in a or 0 to carry c. Move i.
     * | Add char in b or 0 to carry c. Move j.
     * | c % 2 is the current digit.
     * | Insert current digit to the front of result.
     * | c / 2 is the next carry.
     * Return result string.
     */
    public String addBinary(String a, String b) {
        StringBuilder sum = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int c = 0;
        while (i >= 0 || j >= 0 || c == 1) {
            c += (i >= 0 ? a.charAt(i--) - '0' : 0);
            c += (j >= 0 ? b.charAt(j--) - '0' : 0);
            sum.insert(0, c % 2);
            c >>= 1;
        }
        return sum.toString();
    }

    /**
     * Math. String.
     * From end to start, do it digit-by-digit.
     * Get current digits of ab and b, add them up.
     * Also use an integer to store carry from the previous addition.
     * Store the sum to result and update carry for each round.
     * Stop when longest string is reached.
     * Remember to check carry before return, if carry is 1, it should still be inserted to the result.
     */
    public String addBinary2(String a, String b) {
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
}