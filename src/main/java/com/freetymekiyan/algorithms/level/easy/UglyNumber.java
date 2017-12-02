package com.freetymekiyan.algorithms.level.easy;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Write a program to check whether a given number is an ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *
 * For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 *
 * Note that 1 is typically treated as an ugly number.
 *
 * Tags: Math
 *
 * Similar Problems: (E) Happy Number (E) Count Primes (M) Ugly Number II
 */
public class UglyNumber {

    public boolean isUgly(int num) {
        if (num <= 0) return false;

        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;

        return num == 1;
    }

    public static void main(String[] args) {
        UglyNumber u = new UglyNumber();
        // <=0, false
        assertFalse(u.isUgly(-1));
        assertFalse(u.isUgly(0));
        // 1, true, special case
        assertTrue(u.isUgly(1));
        // maximum, false
        assertFalse(u.isUgly(Integer.MAX_VALUE));
        // minimum, false
        assertFalse(u.isUgly(Integer.MIN_VALUE));
        // examples, 6 and 8 are ugly, 14 is not
        assertTrue(u.isUgly(6));
        assertTrue(u.isUgly(8));
        assertFalse(u.isUgly(14));
    }
}
