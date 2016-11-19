package com.freetymekiyan.algorithms.level.easy;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list.
 * <p>
 * Company Tags: Google
 * Tags: Array Math
 * Similar Problems: (M) Multiply Strings, (E) Add Binary, (M) Plus One Linked List
 */
public class PlusOne {

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i] = 1 + digits[i];
            if (digits[i] == 10) { // Carry.
                digits[i] = 0;
            } else { // No carry, just return.
                return digits;
            }
        }
        // Not returned, must have carry.
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        for (int i = 0; i < digits.length; i++) {
            ans[i + 1] = digits[i];
        }
        return ans;
    }

    public int[] plusOneB(int[] digits) {
        int count = digits.length;
        while (count > 0) {
            digits[count - 1] = digits[count - 1] + 1;
            if (digits[count - 1] > 9) {
                digits[count - 1] = 0;
            } else {
                return digits;
            }
            count--;
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        for (int i = 1; i < digits.length; i++) {
            result[i] = digits[i - 1];
        }
        return result;
    }
}
