package com.freetymekiyan.algorithms.level.medium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * <p>
 * Note: Do not use any built-in library function such as sqrt.
 * <p>
 * Example 1:
 * <p>
 * Input: 16
 * Returns: True
 * Example 2:
 * <p>
 * Input: 14
 * Returns: False
 * <p>
 * Company Tags: LinkedIn
 * Tags: Binary Search, Math
 * Similar Problems: (M) Sqrt(x)
 */
public class ValidPerfectSquare {

    private ValidPerfectSquare v;

    /**
     * Binary Search.
     * The original thought is binary search from 1 to sqrt(n).
     * Since we cannot use built-in library, we use n / 2 to replace it.
     * Note that n / 2 > sqrt(n) when n > 4.
     * The initial range is from 1 to num / 2.
     * For each middle value m, compare t = m * m and num.
     * | If t == num, return true.
     * | If t > num, m is too big, reduce upper bound r = m - 1.
     * | If t < num, m is too small, increase lower bound l = m + 1.
     * Return false if search failed.
     */
    public boolean isPerfectSquare(int num) {
        if (num < 4) {
            return num == 1;
        }
        int lo = 1;
        int hi = num / 2;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            long t = mid * mid; // Might overflow. num = 808021.
            if (t == num) {
                return true;
            } else if (t > num) {
                hi = (int) mid - 1;
            } else {
                lo = (int) mid + 1;
            }
        }
        return false;
    }

    /**
     * Math.
     * A perfect square is the sum of a consecutive odd number sequence.
     * 1 + 3 + 5 + 7 + 9 ...
     * For example: 1 = 1, 4 = 1 + 3, 9 = 1 + 3 + 5, 16 = 1 + 3 + 5 + 7
     */
    public boolean isPerfectSquareB(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }

    /**
     * Math, Newton's Method.
     * x(n+1) = x(n) - f(x(n)) / f'(x(n))
     * Find the root for y = num - t^2.
     * t - (num - t^2) / -2t = t + num / 2t - t / 2 = (t + num / t) / 2
     * https://en.wikipedia.org/wiki/Newton%27s_method
     */
    public boolean isPerfectSquareC(int num) {
        long t = num;
        while (t * t > num) {
            t = (t + num / t) / 2;
        }
        return t * t == num;
    }

    @Before
    public void setUp() {
        v = new ValidPerfectSquare();
    }

    @Test
    public void testExamples() {
        int num = 16;
        Assert.assertTrue(v.isPerfectSquare(num));
        Assert.assertTrue(v.isPerfectSquareB(num));
        Assert.assertTrue(v.isPerfectSquareC(num));
        num = 14;
        Assert.assertFalse(v.isPerfectSquare(num));
        Assert.assertFalse(v.isPerfectSquareB(num));
        Assert.assertFalse(v.isPerfectSquareC(num));
        num = 1;
        Assert.assertTrue(v.isPerfectSquare(num));
        Assert.assertTrue(v.isPerfectSquareB(num));
        Assert.assertTrue(v.isPerfectSquareC(num));
        num = 2;
        Assert.assertFalse(v.isPerfectSquare(num));
        Assert.assertFalse(v.isPerfectSquareB(num));
        Assert.assertFalse(v.isPerfectSquareC(num));
        num = 3;
        Assert.assertFalse(v.isPerfectSquare(num));
        Assert.assertFalse(v.isPerfectSquareB(num));
        Assert.assertFalse(v.isPerfectSquareC(num));
        num = 4;
        Assert.assertTrue(v.isPerfectSquare(num));
        Assert.assertTrue(v.isPerfectSquareB(num));
        Assert.assertTrue(v.isPerfectSquareC(num));
        num = 5;
        Assert.assertFalse(v.isPerfectSquare(num));
        Assert.assertFalse(v.isPerfectSquareB(num));
        Assert.assertFalse(v.isPerfectSquareC(num));
        num = 808201;
        Assert.assertTrue(v.isPerfectSquare(num));
        Assert.assertTrue(v.isPerfectSquareB(num));
        Assert.assertTrue(v.isPerfectSquareC(num));
    }

    @After
    public void tearDown() {
        v = null;
    }

}
