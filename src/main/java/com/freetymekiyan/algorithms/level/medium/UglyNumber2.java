package com.freetymekiyan.algorithms.level.medium;

/**
 * Write a program to find the n-th ugly number.
 * <p>
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10,
 * 12 is the sequence of the first 10 ugly numbers.
 * <p>
 * Note that 1 is typically treated as an ugly number.
 * <p>
 * Hint:
 * <p>
 * The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly. Try to
 * focus your effort on generating only the ugly ones.
 * <p>
 * An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
 * <p>
 * The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists:
 * L1, L2, and L3.
 * <p>
 * Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
 * <p>
 * Tags: Dynamic Programming, Heap, Math
 * <p>
 * Similar Problems: (H) Merge k Sorted Lists, (E) Count Primes, (E) Ugly Number, (M) Perfect Squares, (M) Super Ugly
 * Number
 */
public class UglyNumber2 {

    public int nthUglyNumber(int n) {
        if (n <= 0) return -1;
        if (n == 1) return 1;

        // pointers for 2, 3, 5
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;

        // generate ugly numbers array
        int[] uglyNums = new int[n];
        uglyNums[0] = 1;
        for (int i = 1; i < n; i++) {
            uglyNums[i] = Math.min(uglyNums[p2] * 2, Math.min(uglyNums[p3] * 3, uglyNums[p5] * 5));
            // update indices
            if (uglyNums[i] == uglyNums[p2] * 2) p2++;
            if (uglyNums[i] == uglyNums[p3] * 3) p3++;
            if (uglyNums[i] == uglyNums[p5] * 5) p5++;
        }
        return uglyNums[n - 1];
    }
}
