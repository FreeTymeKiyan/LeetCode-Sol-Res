package com.freetymekiyan.algorithms.level.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * Implement int sqrt(int x).
 * <p>
 * Compute and return the square root of x.
 * <p>
 * Company Tags: Bloomberg, Apple, Facebook
 * Tags: Binary Search, Math
 * Similar Problems: (M) Pow(x, n) (M) Valid Perfect Square
 */
public class Sqrt {

    /**
     * Binary Search.
     * Validate input first.
     * If x < 0 , invalid.
     * Special cases:
     * If x = 0 or 1, return x.
     * <p>
     * Binary Search from 1 ~ x/2.
     * While lo < hi:
     * | Rounding mid up since if sqrt always return the ceiling.
     * | Compare mid with x / mid.
     * | If mid = x / mid, square root found, return mid.
     * | If mid > x / mid, mid shall not be square root, hi = mid-1.
     * | If mid < x / mid, mid can be square root, lo = mid.
     * Return hi.
     */
    public int mySqrt(int x) {
        if (x <= 1) {
            return x;
        }
        int lo = 1;
        int hi = x / 2;
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2; // Rounding up.
            int t = x / mid;
            if (mid == t) { // Square root found.
                return mid;
            } else if (mid > t) { // Square root cannot be mid. Must in mid's left.
                hi = mid - 1;
            } else { // Square root can be mid, since it can be the ceiling.
                lo = mid;
            }
        }
        return hi;
    }

    @Test
    public void testExamples() {
        int[] nums = {-1, 1, 2, 4, 9, 16, 25};
        int[] res = {-1, 1, 1, 2, 3, 4, 5};
        for (int i = 0; i < nums.length; i++) {
            Assert.assertEquals(res[i], mySqrt(nums[i]));
        }
    }
}