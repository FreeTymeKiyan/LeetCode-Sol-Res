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
     */
    public boolean isPerfectSquare(int num) {
        if (num < 4) {
            return num == 1;
        }
        int l = 1;
        int r = num / 2;
        while (l <= r) {
            int m = l + (r - l) / 2;
            long t = m * m;
            if (t == num) {
                return true;
            } else if (t > num) {
                r = m - 1;
            } else {
                l = m + 1;
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
    }

    @After
    public void tearDown() {
        v = null;
    }

}
