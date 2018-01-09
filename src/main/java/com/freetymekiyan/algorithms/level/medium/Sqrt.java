package com.freetymekiyan.algorithms.level.medium;

/**
 * 69. Sqrt(x)
 * <p>
 * Implement int sqrt(int x).
 * <p>
 * Compute and return the square root of x.
 * <p>
 * x is guaranteed to be a non-negative integer.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 4
 * Output: 2
 * Example 2:
 * <p>
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since we want to return an integer, the decimal part will be
 * truncated.
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

    /**
     * Why return hi not lo in the end?
     * The loop breaks when lo > hi.
     * So the previous loop lo = hi = mid.
     * Now, mid != t, because it didn't return.
     * If mid < t, lo = mid + 1, lo*lo > t, lo is not the answer, hi is.
     * If mid > t, hi = mid - 1, hi*hi < t, hi is.
     */
    public int mySqrt2(int x) {
        if (x <= 1) return x;
        int lo = 1; // x > 1, lowest possible square root is 1.
        int hi = x / 2;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int t = x / mid;
            if (mid == t) return mid;
            if (mid > t) hi = mid - 1;
            else lo = mid + 1;
        }
        return hi; // hi < mid / hi holds always.
    }

    public int mySqrt3(int x) {
        long ans = 0; // Must use long. Otherwise ans*ans can overflow when ans is 2^16.
        long bit = 1L << 16; // 2^16 is the largest possible square root for all ints up to 2^31 - 1.
        while (bit > 0) {
            ans |= bit; // Set 1.
            if (ans * ans > x) {
                ans ^= bit; // Set 0.
            }
            bit >>= 1; // Onto next bit.
        }
        return (int) ans;
    }
}