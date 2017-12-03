package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is
 * the smallest possible.
 * <p>
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 * <p>
 * Example 1:
 * <p>
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * <p>
 * Example 2:
 * <p>
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 * <p>
 * Example 3:
 * <p>
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 * <p>
 * Tags: Stack, Greedy
 * Similar Problems: (H) Create Maximum Number
 */
public class RemoveKDigits {

    private RemoveKDigits r;

    /**
     * Stack, Greedy.
     * Find the last digit of the increasing sequence and the first digit of the decreasing sequence.
     * Remove it and decrement k by 1.
     * Why is it greedy? Since operation like this will generate a smallest number for each removal.
     * Stop till k is zero.
     * Convert remaining string to a valid number format.
     */
    public String removeKdigits(String num, int k) {
        // Remove all
        if (num.length() == k) {
            return "0";
        }
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < num.length(); i++) {
            // Remove digits that are larger than current one in stack
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
        }
        // If there are too few decreasing sequences
        while (k > 0) {
            stack.pop();
            k--;
        }
        // Convert characters in stack to a string
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        // Trim leading zeros
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    /**
     * Stack, Greedy.
     * The only difference is that stack is imitated with an Object(Character) array and a pointer to its top.
     * Because we know the largest possible size of a stack.
     */
    public String removeKdigitsB(String num, int k) {
        int len = num.length() - k;
        char[] stk = new char[num.length()];
        int top = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (k > 0 && top > 0 && stk[top - 1] > c) {
                top--; // Pop
                k--;
            }
            stk[top++] = c; // Push
        }
        int offset = 0;
        while (offset < len && stk[offset] == '0') {
            offset++;
        }
        return offset == len ? "0" // All zeros
                                : new String(stk, offset, len - offset);
    }


    @Before
    public void setUp() {
        r = new RemoveKDigits();
    }

    @Test
    public void testExamples() {
        Assert.assertEquals("1219", r.removeKdigits("1432219", 3));
        Assert.assertEquals("200", r.removeKdigits("10200", 1));
        Assert.assertEquals("0", r.removeKdigits("10", 2));
        Assert.assertEquals("1234", r.removeKdigits("123456", 2));
        Assert.assertEquals("5432", r.removeKdigits("765432", 2));
        // Test B
        Assert.assertEquals("1219", r.removeKdigitsB("1432219", 3));
        Assert.assertEquals("200", r.removeKdigitsB("10200", 1));
        Assert.assertEquals("0", r.removeKdigitsB("10", 2));
        Assert.assertEquals("1234", r.removeKdigitsB("123456", 2));
        Assert.assertEquals("5432", r.removeKdigitsB("765432", 2));
    }

    @After
    public void tearDown() {
        r = null;
    }

}
